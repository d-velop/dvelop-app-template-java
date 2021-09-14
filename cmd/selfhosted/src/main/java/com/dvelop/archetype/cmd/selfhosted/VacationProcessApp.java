package com.dvelop.archetype.cmd.selfhosted;


import com.dvelop.archetype.ApplicationName;
import org.apache.tomee.embedded.Configuration;
import org.apache.tomee.embedded.Container;

import java.io.File;

public class VacationProcessApp {

    public static void main(String[] args) throws Exception {

        Configuration cfg = new Configuration();
        cfg.setHttpPort(5000);
        cfg.setSsl(false);

        Container container = new Container();
        container.setup(cfg);
        container.start();

        File here = new File("ui");
        System.out.println(here.getAbsoluteFile());
        container.deployClasspathAsWebApp(ApplicationName.APP_NAME, here);

        container.await();
    }

}
