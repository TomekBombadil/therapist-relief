package pl.waw.psychologmaja.therapistrelief.app;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;

import javax.servlet.ServletContext;

public class SpringSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SpringSecurityWebApplicationInitializer(){
        super(SpringSecurityConfiguration.class);
    }

}
