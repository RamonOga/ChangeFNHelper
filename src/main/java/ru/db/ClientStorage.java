package ru.db;

import ru.model.Client;

import java.time.LocalDateTime;
import java.util.List;

public interface ClientStorage {
    int add(Client client);

    Client findById(int id);

    List<Client> findAll();

    List<Client> findByName(String name);

    List<Client> findBeforeDate(LocalDateTime localDateTime);

    List<Client> findAfterDate(LocalDateTime localDateTime);

    Client update(int id, Client client);

    Client delete(int id);
}
