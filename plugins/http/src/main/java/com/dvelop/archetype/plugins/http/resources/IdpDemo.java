package com.dvelop.archetype.plugins.http.resources;

import com.dvelop.archetype.plugins.context.IdentityHolder;
import com.dvelop.archetype.plugins.http.MediaType;
import com.dvelop.archetype.plugins.http.dto.IdpDemoDto;
import com.dvelop.sdk.idp.filter.IDPRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path(IdpDemo.PATH)
public class IdpDemo {
    public static final String PATH = "/idpdemo";

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    IdentityHolder currentUser;

    @GET
    @Produces({MediaType.TEXT_HTML, MediaType.APPLICATION_JSON, MediaType.APPLICATION_HAL_JSON})
    @IDPRole(IDPRole.IDPRoles.USER_INTERNAL)
    public Response getCurrentUser(){

        log.info("Identity: " + currentUser);

        IdpDemoDto demoDto = new IdpDemoDto();
        demoDto.setTitle("IDP Demo");
        demoDto.setIdentity(currentUser.getIdentity());

        return Response.ok(demoDto).build();
    }
}
