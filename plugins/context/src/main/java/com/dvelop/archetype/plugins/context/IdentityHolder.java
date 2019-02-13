package com.dvelop.archetype.plugins.context;

import com.dvelop.sdk.idp.dto.IDPUser;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class IdentityHolder {
    IDPUser identity;

    public IDPUser getIdentity() {
        return identity;
    }

    public void setIdentity(IDPUser identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "" + identity;
    }
}
