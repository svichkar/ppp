package com.nixsolutions.servicestation.util;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.ws.transport.http.MessageDispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Created by rybkinrolla on 10.02.2016.
 */
@EnableAutoConfiguration
@Order(1)
public class MyWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        servletContext.setInitParameter("contextConfigLocation", "");
        ctx.register(AppConfig.class);
        ctx.register(WebServiceConfig.class);
        servletContext.addListener(new ContextLoaderListener(ctx));
        FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain", DelegatingFilterProxy.class);
        securityFilter.addMappingForUrlPatterns(null, false, "/*");
        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        MessageDispatcherServlet servletMess = new MessageDispatcherServlet();
        servletMess.setApplicationContext(ctx);
        servletMess.setTransformWsdlLocations(true);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcherMess", servletMess);
        dynamic.addMapping("/soap/*");
        dynamic.setLoadOnStartup(1);
    }
}
