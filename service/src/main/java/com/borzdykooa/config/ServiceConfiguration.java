package com.borzdykooa.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Класс, ответственный за конфигурацию service-модуля
 */
@Configuration
@ComponentScan(basePackages = "com.borzdykooa.service")
@EnableTransactionManagement
@Import(DaoConfiguration.class)
public class ServiceConfiguration {
}
