package ru.services;

import ru.model.CashRegister;
import ru.model.FiscalAccumulator;

public class CashRegisterService {
    public static void registration(CashRegister register, FiscalAccumulator fiscal) {
        register.setFiscalAccumulator(fiscal);
        fiscal.registerFN();
    }
}
