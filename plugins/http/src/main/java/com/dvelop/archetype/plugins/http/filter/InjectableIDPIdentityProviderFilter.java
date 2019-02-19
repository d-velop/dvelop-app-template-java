package com.dvelop.archetype.plugins.http.filter;

import com.dvelop.archetype.plugins.context.IdentityHolder;
import com.dvelop.archetype.plugins.context.TenantHolder;
import com.dvelop.sdk.idp.dto.IDPUser;
import com.dvelop.sdk.idp.filter.IDPIdentityProviderFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
@RequestScoped
@Priority(Priorities.AUTHENTICATION + 1)
public class InjectableIDPIdentityProviderFilter extends IDPIdentityProviderFilter {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    IdentityHolder identityHolder;

    @Inject
    TenantHolder tenantHolder;

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        log.info("Using baseuri " + tenantHolder.getBaseUri() + " for idp filtering");
        setBaseUri(tenantHolder.getBaseUri());
        super.filter(request);
    }

    @Override
    public void setIDPIdentity(IDPUser idpIdentity) {
        log.info("Identity: " + idpIdentity);
        identityHolder.setIdentity(idpIdentity);
    }

    @Override
    public void setIDPAuthsessionId(String s) {

    }
}
