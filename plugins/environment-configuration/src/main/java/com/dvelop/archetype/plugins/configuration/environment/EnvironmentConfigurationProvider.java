package com.dvelop.archetype.plugins.configuration.environment;

import com.dvelop.archetype.plugins.config.ConfigurationRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnvironmentConfigurationProvider implements ConfigurationRepository {

    @Override
    public String getValue(String key) {
        return System.getenv(key);
    }

}
