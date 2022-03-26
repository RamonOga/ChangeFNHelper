package ru.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@Scope("prototype")
public class CashRegister {
    @Id
    private int id;
    private String name;
    private String serialNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FiscalAccumulator_id")
    private FiscalAccumulator fiscalAccumulator;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "client_id")
    private Client client;
    private String manufacturer;

    public CashRegister() {
    }

    public CashRegister(String serialNumber) {
        this.serialNumber = serialNumber;
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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public FiscalAccumulator getFiscalAccumulator() {
        return fiscalAccumulator;
    }

    public void setFiscalAccumulator(FiscalAccumulator fiscalAccumulator) {
        this.fiscalAccumulator = fiscalAccumulator;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
