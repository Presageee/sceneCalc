package com.server.scenecalc.core;

import com.server.scenecalc.SceneCalcApplication;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by linjuntan on 2017/10/22.
 * email: ljt1343@gmail.com
 */
@Configuration
@PropertySources({ @PropertySource(value = "classpath:server.properties") })
@ComponentScan(basePackageClasses = SceneCalcApplication.class)
public class BaseConfiguration {
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
