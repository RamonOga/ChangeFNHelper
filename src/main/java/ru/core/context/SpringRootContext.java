package ru.core.context;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@PropertySource("application.properties")
@ComponentScan("ru")
public class SpringRootContext {
}
