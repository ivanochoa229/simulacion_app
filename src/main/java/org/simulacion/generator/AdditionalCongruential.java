package org.simulacion.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdditionalCongruential<T extends Number> implements Generator<T> {
    List<Double> numbers;

    public AdditionalCongruential() {
        numbers = new ArrayList<>();

    }

    @Override
    public List<Double> getNumbers(List<T> conditions) {
        // Validar entrada básica
        if (conditions == null || conditions.size() < 3) {
            throw new IllegalArgumentException("La lista debe contener al menos k, módulo y cantidad");
        }

        // Extraer parámetros básicos
        int k = conditions.get(0).intValue();
        int module = conditions.get(1).intValue();
        int quantity = conditions.get(2).intValue();

        // Extraer semillas (necesitamos k+1 valores iniciales)
        List<Integer> seeds = new ArrayList<>();
        for (int i = 3; i < 3 + k + 1; i++) {
            seeds.add(conditions.get(i).intValue());
        }
        Collections.reverse(seeds);
        return generateNumbers(k, module, quantity, seeds);
    }

    private List<Double> generateNumbers(int k, int module, int quantity, List<Integer> seeds) {
        List<Integer> values = new ArrayList<>(seeds); // n₀, n₋1, n₋2, ..., n₋k
        List<Double> results = new ArrayList<>();

        for (int i = seeds.size(); i < seeds.size() + quantity; i++) {
            int ni = (values.get(i - 1) + values.get(i - k - 1)) % module;
            values.add(ni);
            double number = ni / (double) module;
            // Redondear a 3 decimales
            number = Math.round(number * 1000.0) / 1000.0;
            results.add(number);
        }
        results.forEach(u -> System.out.printf( u.toString()));
        return results;
    }
}

