package com.dvelop.archetype.plugins.http.template;

import com.dvelop.archetype.ApplicationName;
import com.dvelop.archetype.plugins.config.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AppInfo {

    @Inject
    @ConfigProperty(value = "app.name", fallback = ApplicationName.APP_NAME)
    String name;

    @Inject
    @ConfigProperty(value = "BUILD_VERSION", fallback = "??")
    String version;

    @Inject
    @ConfigProperty(value = "ASSET_BASE_PATH", fallback = "/" + ApplicationName.APP_NAME)
    String assetBasePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAssetBasePath() {
        return assetBasePath;
    }

    public void setAssetBasePath(String assetBasePath) {
        this.assetBasePath = assetBasePath;
    }
}
