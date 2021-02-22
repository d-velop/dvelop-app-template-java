package com.dvelop.archetype.cmd.awslambda;

import com.jrestless.aws.gateway.handler.GatewayRequestObjectHandler;
import org.jboss.weld.environment.se.Weld;

import javax.ws.rs.core.Application;

public class VacationProcessLambdaApp extends GatewayRequestObjectHandler {

    public VacationProcessLambdaApp() {

        Weld weld = new Weld();
        weld.initialize();

        init(new VacationProcessLambdaResourceConfig());

        start();

    }
}
