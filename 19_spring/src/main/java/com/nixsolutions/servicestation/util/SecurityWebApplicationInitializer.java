package com.nixsolutions.servicestation.util;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

/**
 * Created by rybkinrolla on 02.02.2016.
 */
@Component
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
    }
}
