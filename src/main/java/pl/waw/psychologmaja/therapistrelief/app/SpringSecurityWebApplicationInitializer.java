package pl.waw.psychologmaja.therapistrelief.app;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SpringSecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SpringSecurityWebApplicationInitializer(){
        super(SpringSecurityConfiguration.class);
    }
}
