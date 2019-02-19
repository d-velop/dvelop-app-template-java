APP_NAME=acme-apptemplatejava
DOMAIN_SUFFIX=.service.d-velop.cloud
BUILD_VERSION=rev.$(shell git rev-parse --short HEAD).date.$(shell date '+%d-%m-%Y-%H.%M.%S')

all: build

build: clean build-all

clean:
	mvn clean

test:
	mvn test

build-all:
	mvn clean package

tf-init:
	cd ./terraform && \
	terraform init -input=false -plugin-dir=/usr/local/lib/custom-terraform-plugins


plan: build-all tf-init asset_hash
	$(eval PLAN=$(shell mktemp))
	cd ./terraform && \
	terraform plan -input=false \
	-var "signature_secret=$(SIGNATURE_SECRET)" \
	-var "build_version=$(BUILD_VERSION)" \
	-var "appname=$(APP_NAME)" \
	-var "domainsuffix=$(DOMAIN_SUFFIX)" \
	-var "asset_hash=$(ASSET_HASH)" \
	-out=$(PLAN)


apply: plan
	cd ./terraform && \
	terraform apply -input=false -auto-approve=true $(PLAN)

deploy-assets: asset_hash apply
	# best practice for immutable content: cache 1 year (vgl https://jakearchibald.com/2016/caching-best-practices/)
	aws s3 sync ./ui s3://assets.$(APP_NAME)$(DOMAIN_SUFFIX)/$(ASSET_HASH) --exclude "*.html" --exclude "pom.xml" --cache-control max-age=31536000

asset_hash:
	$(eval ASSET_HASH=$(shell find ui -type f ! -path "*.html" -exec md5sum {} \; | sort -k 2 | md5sum | tr -d " -"))

deploy: apply deploy-assets


show: tf-init
	cd ./terraform && \
	terraform show

destroy: tf-init
	echo "destroy is disabled. Uncomment in Makefile to enable destroy."
	#cd ./terraform && \
	#terraform destroy -var "signature_secret=$SIGNATURE_SECRET" -var "build_version=$build_version" -var "appname=$(APP_NAME)" -var "domainsuffix=$(DOMAIN_SUFFIX)" -input=false -force

dns:
	mkdir -p dist
	cd ./terraform && terraform output -json | jq "{Domain: .domain.value, Nameserver: .nameserver.value}" > ../dist/dns-entry.json