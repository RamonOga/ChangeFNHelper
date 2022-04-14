package ru.core.model;

import java.util.Map;

public class FiscalAccumulatorVersion {
    public static final long THIRTY_SIX_MONTHS = 94_608_000_000L;
    public static final long FIFTEEN_MONTHS = 39_528_000_000L;

    private FiscalAccumulatorVersion() {
        // pom-pom
    }

    public static Map<String, Long> getValues() {
        return Map.of("THIRTY_SIX_MONTHS", 39_528_000_000L, "FIFTEEN_MONTHS", 39_528_000_000L);
    }

}
