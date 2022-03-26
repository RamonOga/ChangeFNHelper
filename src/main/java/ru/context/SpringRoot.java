package ru.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import ru.db.ClientStorage;
import ru.model.CashRegister;
import ru.model.Client;
import ru.model.FiscalAccumulator;
import ru.model.FiscalAccumulatorVersion;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


public class SpringRoot {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringRootContext.class);
        ClientStorage storage = context.getBean(ClientStorage.class);
        System.out.println("123321");
        Client client1 = new Client("Fucking Shit", List.of(getCashRegister()));
        Client client2  = new Client("Shiting Fuck", List.of(getCashRegister()));
        Client clientForUpdate = new Client("Update Client",List.of(getCashRegister()));
        System.out.println(storage.add(client1));
        System.out.println(storage.findAll());
        System.out.println(storage.findById(client1.getId()));
        System.out.println(storage.findByName("Shit"));
        System.out.println(storage.add(client2));
        System.out.println(storage.findByName("Shit"));
        System.out.println(storage.update(client2.getId(), clientForUpdate));
        System.out.println(storage.findAll());
    }

    private static CashRegister getCashRegister() {
        return new CashRegister("sn_cash_0001");
    }

    private static FiscalAccumulator getFN() {
        return new FiscalAccumulator("sn_fn_0001", FiscalAccumulatorVersion.FIFTEEN_MONTHS);
    }
}
