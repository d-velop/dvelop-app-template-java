package com.dvelop.archetype.plugins.http.filter;

import com.dvelop.archetype.plugins.config.ConfigProperty;
import com.dvelop.archetype.plugins.context.TenantHolder;
import com.dvelop.sdk.tenant.filter.TenantFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.util.Base64;

@Provider
@RequestScoped
@PreMatching
public class InjectableTenantFilter extends TenantFilter {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    @ConfigProperty(value = "SIGNATURE_SECRET", mandatory = false)
    String signatureSecretString;

    @Inject
    TenantHolder tenantHolder;

    byte[] signatureBytes = null;

    public InjectableTenantFilter() {
        super(new byte[]{}, "", "0");
    }

    @Override
    public void filter(ContainerRequestContext request) {
        log.info("Processing tenant headers ");

        if( signatureBytes == null) {
            log.info("First request, setup signature secret");

            signatureBytes = Base64.getDecoder().decode(signatureSecretString);
            setSignatureSecret(signatureBytes);
        }

        super.filter(request);
    }

    @Override
    public void setTenantId(String s) {
        log.info("Found tenant id "+s);
        tenantHolder.setTenantId(s);
    }

    @Override
    public void setBaseUri(String s) {
        log.info("Found baseuri "+s);
        tenantHolder.setBaseUri(s);
    }
}

