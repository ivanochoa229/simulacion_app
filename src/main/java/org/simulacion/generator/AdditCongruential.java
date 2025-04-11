package org.simulacion.generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdditCongruential {
    List<Double> numbers;
    List<Integer> seeds;
    Scanner scanner = new Scanner(System.in);

    public AdditCongruential() {
        numbers = new ArrayList<>();
        seeds = new ArrayList<>();
    }

    public List<Double> getNumbers(int k, int module, int quantity) {

        if (k < 0) {
            System.out.println("LA CONSTANTE 'k' DEBE SER POSITIVA");
            return null;
        }
        if (module < 0) {
            System.out.println("EL MODULO DEBE SER POSITIVO");
            return null;
        }

        for(int i = 0; i < k+1; i++) {
            System.out.println("Ingrese el valor de n-" + i);
            int n = scanner.nextInt();
            seeds.add((int) n);
        }

        int currentValueN = seeds.get(0) ;
        int nextValueN;

        for (int i = 0; i < quantity; i++) {
            int position = (i-k)*-1;
            int n = seeds.get(position);

            nextValueN = (currentValueN + n) % module;
            double u = (double) nextValueN/module;
            numbers.add(Math.floor(u * 1000) / 1000.0);
            currentValueN = nextValueN;
        }
        return numbers;
    }
}
