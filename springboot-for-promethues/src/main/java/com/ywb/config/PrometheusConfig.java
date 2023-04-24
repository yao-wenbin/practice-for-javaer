package com.ywb.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author yaowenbin
 * @Date 2022/10/10
 */

@Configuration
public class PrometheusConfig {

    // @Bean
    // MeterRegistryCustomizer<MeterRegistry> configurer(@Value("${spring.application.name") String applicationName) {
    //     return (registry) ->  registry.config().commonTags("application", applicationName);
    // }

}
