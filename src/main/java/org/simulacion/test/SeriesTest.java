package org.simulacion.test;

import org.simulacion.utils.Stadistics;

import java.util.List;

public class SeriesTest implements TestInterface {
    @Override
    public Boolean checkSample(List<Double> sample, List<Integer> conditions) {
        int n = sample.size() - 1;
        int x = conditions.get(0);
        double alfa = conditions.get(1) / 100.0;

        if (n <= 0 || x <= 0 || x > n) return false;

        // Frecuencia esperada
        double Fe = (double) n / (x * x);

        // Matriz de frecuencias observadas Fjk
        int[][] frecuencias = new int[x][x];

        for (int i = 0; i < n; i++) {
            double u1 = sample.get(i);
            double u2 = sample.get(i + 1);
            int j = Math.min((int) (u1 * x), x - 1);
            int k = Math.min((int) (u2 * x), x - 1);
            frecuencias[j][k]++;
        }

        // Calcular el estadístico chi-cuadrado
        double chi2 = 0.0;
        for (int j = 0; j < x; j++) {
            for (int k = 0; k < x; k++) {
                double diff = frecuencias[j][k] - Fe;
                chi2 += diff * diff;
            }
        }
        chi2 *= ((double)(x * x) / n);

        // Grados de libertad: x^2 - x
        int gradosLibertad = (x * x) - x;
        double chi2_critical = Stadistics.chiCuadrado(alfa, gradosLibertad);

        return chi2 < chi2_critical;
    }

}

