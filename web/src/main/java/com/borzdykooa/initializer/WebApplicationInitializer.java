package com.borzdykooa.initializer;

import com.borzdykooa.config.DaoConfiguration;
import com.borzdykooa.config.ServiceConfiguration;
import com.borzdykooa.config.WebConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Класс, ответственный за инициализацию web-приложения
 */
public class WebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private static final String SERVLET_MAPPING = "/";

    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{DaoConfiguration.class, ServiceConfiguration.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfiguration.class};
    }

    protected String[] getServletMappings() {
        return new String[]{SERVLET_MAPPING};
    }
}
