package ru.core.services;

import ru.core.model.CashRegister;
import ru.core.model.FiscalAccumulator;

public class CashRegisterService {
    public static void registration(CashRegister register, FiscalAccumulator fiscal) {
        register.setFiscalAccumulator(fiscal);
        fiscal.registerFN();
    }
}
