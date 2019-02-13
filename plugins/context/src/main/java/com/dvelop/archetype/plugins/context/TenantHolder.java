package com.dvelop.archetype.plugins.context;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class TenantHolder {

    String tenantId;

    String baseUri;

    public TenantHolder() {}

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }
}
