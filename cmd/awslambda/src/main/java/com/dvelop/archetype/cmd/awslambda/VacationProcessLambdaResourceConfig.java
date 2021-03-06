package com.dvelop.archetype.cmd.awslambda;

import com.dvelop.archetype.ApplicationName;
import com.dvelop.archetype.plugins.http.filter.InjectableIDPIdentityProviderFilter;
import com.dvelop.archetype.plugins.http.filter.InjectableTenantFilter;
import com.dvelop.archetype.plugins.http.resources.Root;
import com.dvelop.archetype.plugins.http.template.ThymeleafRenderer;
import com.jrestless.aws.gateway.GatewayFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath(ApplicationName.APP_NAME)
public class VacationProcessLambdaResourceConfig extends ResourceConfig {

    public VacationProcessLambdaResourceConfig() {

        register(GatewayFeature.class);
        register(ThymeleafRenderer.class);

        register(JacksonObjectMapperProvider.class);

        register(InjectableTenantFilter.class);
        register(InjectableIDPIdentityProviderFilter.class);

        packages(Root.class.getPackage().getName());
    }

}
