package org.simulacion.generator;

import java.util.ArrayList;
import java.util.List;

public class MultiplicativeCongruential<T extends Number> implements Generator<T> {
    List<Double> numbers;

    public MultiplicativeCongruential() {
        numbers = new ArrayList<>();
    }

    @Override
    public List<Double> getNumbers(List<T> conditions) {
        int a = conditions.get(0).intValue(), module = conditions.get(1).intValue(), seed = conditions.get(2).intValue(),
                quantity = conditions.get(3).intValue();
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
