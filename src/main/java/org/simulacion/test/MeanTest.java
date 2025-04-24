package org.simulacion.test;

import java.util.List;

import org.simulacion.utils.Stadistics;

public class MeanTest implements TestInterface {

    @Override
    public Boolean checkSample(List<Double> sample, List<Integer> conditions) {
        int n = sample.size(); // Tamaño de la muestra
        int alfaInt = conditions.get(0);
        double alfa = alfaInt / 100.0;

        // 1. Calcular el promedio aritmético X̄
        double sum = 0;
        for (double value : sample) {
            sum += value;
        }
        double promedio = sum / n;

        // 2. Calcular el estadístico Z₀
        double z0 = (promedio - 0.5) * Math.sqrt(n) / Math.sqrt(1.0 / 12);

        // 3. Obtener Zα usando la clase Stadistics
        double zAlpha = Stadistics.zNormal(alfa);

        // 4. Comparar |Z₀| < Zα
        return Math.abs(z0) < zAlpha;
    }
}
