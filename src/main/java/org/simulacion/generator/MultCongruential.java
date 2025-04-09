package org.simulacion.generator;

import java.util.ArrayList;
import java.util.List;

public class MultCongruential {
    List<Double> numbers;

    public MultCongruential() {
        numbers = new ArrayList<>();
    }

    public List<Double> getNumbers(int seed, int a, int module, int quantity) {

        if (seed < 0) {
            System.out.println("LA SEMILLA DEBE SER POSITIVA");
            return null;
        }
        if (a < 0) {
            System.out.println("LA CONSTANTE MULTIPLICATIVA 'a' DEBE SER POSITIVA");
            return null;
        }
        if (module < 0) {
            System.out.println("EL MODULO DEBE SER POSITIVO");
            return null;
        }

        int currentValue = seed;
        int nextValue;

        for (int i = 0; i < quantity; i++) {
            nextValue = (currentValue * a) % module;
            double u = (double) nextValue/module;
            numbers.add(Math.floor(u * 1000) / 1000.0);
            currentValue = nextValue;
        }
        return numbers;
    }
}
