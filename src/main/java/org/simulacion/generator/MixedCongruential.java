
package org.simulacion.generator;

import java.util.ArrayList;
import java.util.List;

public class MixedCongruential<T extends Number> implements Generator<T>  {

    private long a;     // Multiplicador
    private long c;     // Incremento
    private long m;     // Módulo
    private long seed;  // Semilla inicial

    public MixedCongruential() {
    }

    public MixedCongruential(long a, long c, long m, long seed) {
        this.a = a;
        this.c = c;
        this.m = m;
        this.seed = seed;
    }

    @Override
    public List<Double> getNumbers(List<T> conditions) {
        int a = conditions.get(0).intValue(), c = conditions.get(1).intValue(),
            m = conditions.get(2).intValue(), seed = conditions.get(3).intValue(),
            quantity = conditions.get(4).intValue();
        List<Double> randomNumbers = new ArrayList<>();
        long xi = seed;

        for (int i = 0; i < quantity; i++) {
            xi = (a * xi + c) % m;
            double ri = (double) xi / m;
            randomNumbers.add(ri);
        }

        return randomNumbers;
    }
}
