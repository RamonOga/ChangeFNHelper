package ru.core.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.core.model.CashRegister;
import ru.core.model.FiscalAccumulator;
import ru.core.model.FiscalAccumulatorVersion;

import java.util.Date;

class CashRegisterServiceTest {

    @Test
    void registrationTest() {
        CashRegister cashRegister = new CashRegister("cash_sn_1");
        FiscalAccumulator fn = new FiscalAccumulator("fn_sn_1", FiscalAccumulatorVersion.FIFTEEN_MONTHS);
        long now = System.currentTimeMillis();
        long fourteenMonths = 36816402000L;
        long sixteenMonths = 42075888000L;
        Assertions.assertFalse(fn.isActive());
        Assertions.assertNull(fn.getRegistrationDate());

        CashRegisterService.registration(cashRegister, fn);

        Assertions.assertEquals(fn.getSerialNumber(), cashRegister.getFiscalAccumulator().getSerialNumber());
        Assertions.assertTrue(fn.isActive());
        Assertions.assertNotNull(fn.getRegistrationDate());
        Assertions.assertTrue(fn.getPizdecFN().before(new Date(now + sixteenMonths)));
        Assertions.assertTrue(fn.getPizdecFN().after(new Date(now + fourteenMonths)));
    }

}