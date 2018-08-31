package com.borzdykooa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Класс, ответственный за конфигурацию тестовой части service-модуля
 */
@Configuration
@ComponentScan(basePackages = "com.borzdykooa.util")
@Import(ServiceConfiguration.class)
public class TestServiceConfiguration {
}
