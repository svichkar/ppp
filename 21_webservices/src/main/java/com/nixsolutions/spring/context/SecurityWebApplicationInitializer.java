package com.nixsolutions.spring.context;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

/**
 * Created by kozlovskij on 2/3/2016.
 */
@Component
public class SecurityWebApplicationInitializer
        extends AbstractSecurityWebApplicationInitializer {
    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
}
