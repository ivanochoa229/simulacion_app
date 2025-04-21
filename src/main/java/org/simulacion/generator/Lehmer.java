package org.simulacion.generator;

import java.util.ArrayList;
import java.util.List;

public class Lehmer<T extends Number> implements Generator<T> {
    List<Double> numbers;

    public Lehmer() {
        this.numbers = new ArrayList<>();
    }

    @Override
    public List<Double> getNumbers(List<T> conditions) {
        int t = conditions.get(0).intValue(), seed = conditions.get(1).intValue(), quantity = conditions.get(2).intValue();
        int n = String.valueOf(seed).length();
        int k = String.valueOf(t).length();

        if (!(k<n)) {
            System.out.println("EL NUMERO K DEBE SER MENOR QUE LA SEMILLA");
            return null;
        }

        String product;
        int left;
        int right;

        for (int i = 0; i < quantity; i++) {

            product = String.valueOf(seed * t);

            left = Integer.parseInt(product.substring(0, k));
            right = Integer.parseInt(product.substring(k));
            seed = right - left;

            int nDigits = String.valueOf(seed).length();
            double u = seed / Math.pow(10, nDigits);
            numbers.add(u);
            if (seed == 0) break;
        }
        return numbers;
    }
}
