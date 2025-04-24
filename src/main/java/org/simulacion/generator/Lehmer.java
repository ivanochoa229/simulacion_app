package org.simulacion.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lehmer<T extends Number> implements Generator<T> {
    List<Double> numbers;

    public Lehmer() {
        this.numbers = new ArrayList<>();
    }

    @Override
    public List<Double> getNumbers(List<T> conditions) {
        List<Double> numbers = new ArrayList<>();

        int t = conditions.get(0).intValue();
        int seed = conditions.get(1).intValue();
        int quantity = conditions.get(2).intValue();

        int n = String.valueOf(seed).length();
        int k = String.valueOf(t).length();

        if (k >= n) {
            System.out.println("ERROR: El número de dígitos de t debe ser menor que el de la semilla.");
            return Collections.emptyList();
        }

        for (int i = 0; i < quantity; i++) {
            String product = String.valueOf(seed * t);

            // Asegurarse de que el producto tenga al menos k+n dígitos rellenando con ceros si es necesario
            while (product.length() < k + n) {
                product = "0" + product;
            }

            int left = Integer.parseInt(product.substring(0, k));
            int right = Integer.parseInt(product.substring(k));

            // Nueva semilla como valor absoluto de la diferencia
            seed = Math.abs(right - left);

            // Evitar semilla cero para evitar ciclos constantes
            if (seed == 0) {
                System.out.println("La semilla se volvió cero. Finalizando generación.");
                break;
            }

            // Asegurar que la división produce un número entre 0 y 1
            String seedStr = String.format("%0" + n + "d", seed); // Rellenar con ceros si es necesario
            double u = Integer.parseInt(seedStr) / Math.pow(10, n);
            numbers.add(u);
        }

        numbers.forEach(System.out::println);
        return numbers;
    }
}
