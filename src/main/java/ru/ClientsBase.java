package ru;

import ru.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientsBase {
    List<Client> clients = new ArrayList();

    public boolean add(Client client) {
        return this.clients.add(client);
    }

    public Client findById(int id) {
        Client rsl = null;
        for (Client client : clients) {
            if (client.getId() == id) {
                rsl = client;
            }
        }
        return rsl;
    }

    public List <Client> findByName(String name) {
        List <Client> rsl = new ArrayList();
        for (Client client : clients) {
            if ( client.getName().equals(name) ) {
                rsl.add(client);
            }
        }
        return rsl;
    }

    public boolean delete(int id) {
        boolean rsl = indexOf(id) != -1;
        int index = indexOf(id);
        if (rsl) {
            this.clients.remove(index);
        }
        return rsl;
    }

    private int indexOf(int id) {
        int rsl = -1;
        for (Client client : clients) {
            if ( client.getId() == id ) {
                rsl = clients.indexOf(client);
            }
        }
        return rsl;
    }

}
