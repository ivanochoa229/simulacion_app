package org.simulacion.repository;

import java.util.ArrayList;
import java.util.List;

public class GlobalRepository {
    private static List<Double> sharedNumbers;

    public static List<Double> getSharedNumbers() {
        return sharedNumbers;
    }

    public static void setSharedNumbers(List<Double> sharedNumbers) {
        GlobalRepository.sharedNumbers = (sharedNumbers != null) ?  new ArrayList<>(sharedNumbers) : null;
    }
}
