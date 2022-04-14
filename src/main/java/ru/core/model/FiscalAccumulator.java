package ru.core.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
@Component
@Scope("prototype")
public class FiscalAccumulator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String serialNumber;
    private String manufacturer;
    private boolean active;
    private Date registrationDate;
    private long validity;
    private Date endStaticDateFN;

    public FiscalAccumulator() {
    }

    public FiscalAccumulator(String serialNumber, long validity) {
        this.serialNumber = serialNumber;
        this.validity = validity;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public long getValidity() {
        return validity;
    }

    public void setValidity(long validity) {
        this.validity = validity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Метод для регистрации ФН
     */
    public void registerFN() {
        if (!isActive() && registrationDate == null) {
            setRegistrationDate(new Date());
            setActive(Boolean.TRUE);
            endStaticDateFN = new Date(registrationDate.getTime() + validity);
        }
    }

    public Date getPizdecFN() {
        return new Date(registrationDate.getTime() + validity);
    }
}
