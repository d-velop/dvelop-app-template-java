package com.dvelop.archetype.plugins.config;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

public class ConfigProvider {

    private ConfigurationRepository configurationRepository;

    @Inject
    public ConfigProvider(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    @Produces
    @ConfigProperty("")
    String getConfigProperty(InjectionPoint ip) {
        ConfigProperty annotation = ip.getAnnotated().getAnnotation(ConfigProperty.class);
        String propertyName = annotation.value();
        boolean mandatory = annotation.mandatory();
        String fallback = annotation.fallback();
        String value = configurationRepository.getValue(propertyName);

        if (value == null || "".equals(value)){
            value = fallback;
        }

        if (mandatory && "".equals(value)){
            throw new RuntimeException("Property "+propertyName+" must not be empty");
        }

        return value;
    }
}
