package ru.core.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Component
@Scope("prototype")
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    private List<CashRegister> cashRegisters;
    private String address;

    public Client() {
    }

    public Client(int id, String name, List<CashRegister> cashRegisters) {
        this.id = id;
        this.name = name;
        this.cashRegisters = cashRegisters;
    }

    public Client(String name, List<CashRegister> cashRegisters) {
        this.name = name;
        this.cashRegisters = cashRegisters;
    }

    public void copyAllFields(Client client) {
        this.setName(client.getName());
        this.setCashRegisters(client.getCashRegisters());
        this.setAddress(client.getAddress());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CashRegister> getCashRegisters() {
        return cashRegisters;
    }

    public void setCashRegisters(List<CashRegister> cashRegisters) {
        this.cashRegisters = cashRegisters;
    }

    public void addCashRegister(CashRegister cashRegister) {
        cashRegisters.add(cashRegister);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
