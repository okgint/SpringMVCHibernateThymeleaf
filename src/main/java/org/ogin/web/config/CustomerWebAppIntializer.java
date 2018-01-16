package org.ogin.web.config;

import org.ogin.app.config.DatabaseConfig;
import org.ogin.security.SecurityConfiguration;
import org.ogin.security.SecurityWebApplicationInitializer;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author ogin
 */
public class CustomerWebAppIntializer extends AbstractAnnotationConfigDispatcherServletInitializer {
   /* public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(DatabaseConfig.class);
        webContext.register(SecurityConfiguration.class);
        webContext.register(CustomerWebMVCConfig.class);

        webContext.setServletContext(servletContext);
        ServletRegistration.Dynamic reg= servletContext.addServlet("dispatcherServlet", new DispatcherServlet(webContext));
        reg.setLoadOnStartup(1);
        reg.addMapping("/");
    }*/

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {SecurityConfiguration.class, DatabaseConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{CustomerWebMVCConfig.class};
    }
}
