
package org.simulacion.generator;

import java.util.ArrayList;
import java.util.List;

public class MixedCongruential {

    private long a;     // Multiplicador
    private long c;     // Incremento
    private long m;     // Módulo
    private long seed;  // Semilla inicial

    public MixedCongruential(long a, long c, long m, long seed) {
        this.a = a;
        this.c = c;
        this.m = m;
        this.seed = seed;
    }

    public List<Double> generate(int count) {
        List<Double> randomNumbers = new ArrayList<>();
        long xi = seed;

        for (int i = 0; i < count; i++) {
            xi = (a * xi + c) % m;
            double ri = (double) xi / m;
            randomNumbers.add(ri);
        }

        return randomNumbers;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public long getSeed() {
        return seed;
    }
}
