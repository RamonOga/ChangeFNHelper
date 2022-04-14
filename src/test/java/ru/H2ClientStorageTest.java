package ru;

import org.junit.jupiter.api.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.core.db.ClientStorage;
import ru.core.db.H2ClientStorage;
import ru.core.model.CashRegister;
import ru.core.model.Client;
import ru.core.model.FiscalAccumulator;
import ru.core.model.FiscalAccumulatorVersion;

import java.util.List;

//@ContextConfiguration(classes = H2ClientStorageTestContext.class)
public class H2ClientStorageTest {

    private final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(H2ClientStorageTestContext.class);
    private final ClientStorage storage = context.getBean(H2ClientStorage.class);

    @AfterEach
    public void setUp() {
        H2ClientStorage h2 = (H2ClientStorage) storage;
        h2.deleteAll();
    }

    @Test
    public void h2ClientDeleteTest() {
        Client client = new Client("testEntity", List.of(getCashRegister()));
        storage.add(client);
        Assertions.assertNotNull(storage.findById(client.getId()));
        storage.delete(client.getId());
        Assertions.assertNull(storage.findById(client.getId()));
    }

    @Test
    public void h2ClientUpdateTest() {
        Client client = new Client("testEntity", List.of(getCashRegister()));
        storage.add(client);
        int id = client.getId();
        Assertions.assertEquals(client.getName(), storage.findById(id).getName());
        Client updateClient = new Client("updateEntity", List.of(getCashRegister()));
        storage.update(id, updateClient);
        Assertions.assertEquals(updateClient.getName(), storage.findById(id).getName());
    }

    @Test
    public void h2ClientFindByIdTest() {
        Client client = new Client("testEntity", List.of(getCashRegister()));
        storage.add(client);
        Client rsl = storage.findById(client.getId());
        Assertions.assertEquals(client.getName(), rsl.getName());
    }

    @Test
    public void h2ClientFindByNameTest() {
        storage.add(new Client("testEntity1", List.of(getCashRegister())));
        storage.add(new Client("testEntity1", List.of(getCashRegister())));
        storage.add(new Client("testEntity2", List.of(getCashRegister())));
        Assertions.assertEquals(1, storage.findByName("testEntity2").size());
        Assertions.assertEquals(2, storage.findByName("testEntity1").size());
    }

    @Test
    public void h2FindAllTest() {
        storage.add(new Client("testEntity", List.of(getCashRegister())));
        Assertions.assertEquals(1, storage.findAll().size());
        storage.add(new Client("testEntity", List.of(getCashRegister())));
        Assertions.assertEquals(2, storage.findAll().size());
    }

    private CashRegister getCashRegister() {
        return new CashRegister("sn_cash_0001");
    }

    private FiscalAccumulator getFN() {
        return new FiscalAccumulator("sn_fn_0001", FiscalAccumulatorVersion.FIFTEEN_MONTHS);
    }
}