package ru.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;
import ru.model.Client;

@Service
public class H2ConfigurationFactory implements AutoCloseable {
    private static final SessionFactory ourSessionFactory;

    static  {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Client.class);
        ourSessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return ourSessionFactory.openSession();
    }

    @Override
    public void close() throws Exception {
        ourSessionFactory.close();
    }
}
