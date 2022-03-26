package ru;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.db.ClientStorage;
import ru.db.H2ClientStorage;
import ru.db.H2ConfigurationFactory;

@Configuration
@PropertySource("application.properties")
public class H2ClientStorageTestContext {

    @Bean
    public H2ConfigurationFactory getH2ConfigurationFactory() {
        return new H2ConfigurationFactory();
    }

    @Bean
    public ClientStorage getH2ClientStorage(H2ConfigurationFactory factory) {
        return new H2ClientStorage(factory);
    }
}
