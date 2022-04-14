package ru.core.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.core.model.Client;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;

@Component
public class H2ClientStorage implements ClientStorage {

    private final H2ConfigurationFactory h2Configuration;
    private final Logger LOG = LogManager.getLogger();

    @Autowired
    public H2ClientStorage(H2ConfigurationFactory h2ConfigurationFactory) {
        this.h2Configuration = h2ConfigurationFactory;
    }


    @Override
    public int add(Client client) {
        return executeTransactionOperation(session -> {
            session.save(client);
            return client.getId();
        }, "Добавиления клиента : '" + client + "'");
    }

    @Override
    public List<Client> findAll() {
        return executeTransactionOperation(session ->
                        session.createQuery("from ru.model.Client", Client.class).list(),
                "Общий поиск по базе");
    }

    @Override
    public Client findById(int id) {
        return executeTransactionOperation(session ->
                session.get(Client.class, id), "Поиск клиента по id : " + id);
    }

    @Override
    public List<Client> findByName(String name) {
        return executeTransactionOperation((session) ->
                session.createQuery("from ru.model.Client where name like :param", Client.class)
                        .setParameter("param", "%" + name + "%").list(), "Поиск клиента по имени : " + name);
    }

    @Override
    public List<Client> findBeforeDate(LocalDateTime localDateTime) {
        return null;
    }

    @Override
    public List<Client> findAfterDate(LocalDateTime localDateTime) {
        return null;
    }

    @Override
    public Client update(int id, Client newClient) {
        String message = String.format("Обновление клиента с id %s. Новый клиент = %s", id, newClient.toString());
        return executeTransactionOperation(session -> {
            Client client = session.get(Client.class, id);
            if (client == null) {
                String error = String.format("Похоже, что клиент с id %s не найден", id);
                LOG.error(error);
                throw new NoSuchElementException(error);
            } else {
                client.copyAllFields(newClient);
                session.update(client);
                return client;
            }

        }, message);
    }

    @Override
    public Client delete(int id) {
        return executeTransactionOperation((session) -> {
            Client client = session.get(Client.class, id);
            session.delete(client);
            return client;
        }, "Удаления клиента c id = " + id);
    }

    public void deleteAll() {
        executeTransactionOperation(session -> {
            return session.createQuery("delete from Client").executeUpdate();
        }, "Удаление всех клиентов");
        /*LOG.info("Начали удаление всех клиентов");
        List<Client> allClients = findAll();
        allClients.forEach(client -> delete(client.getId()));
        LOG.info("Закончали удаление всех клиентов");*/
    }

    private <T> T executeTransactionOperation(final Function<Session, T> command, String message) {
        Transaction transaction = null;
        try (Session session = h2Configuration.getSession()) {
            transaction = session.beginTransaction();
            T rsl = command.apply(session);
            LOG.info("\nОперация {} выполнена успешно в классе {} \n", message, this.getClass().getSimpleName());
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            if (transaction != null) {
                transaction.rollback();
                LOG.info("\n.......Ой. Что-то не то. Транзакция откатилась.......\n");
            }
            LOG.error(e.getMessage());
            throw e;
        }
    }
}
