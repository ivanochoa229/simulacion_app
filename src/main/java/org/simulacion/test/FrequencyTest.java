package org.simulacion.test;

import org.simulacion.utils.Stadistics;

import java.util.List;

public class FrequencyTest implements TestInterface {
    @Override
    public Boolean checkSample(List<Double> sample, List<Integer> conditions) {
        int n = sample.size(); // Tamaño de la muestra
        int alfaint = conditions.get(1); //Alfa
        double alfa = alfaint/100.0;
        int x = conditions.get(0); // Número de subintervalos
        if (x <= 0 || n <= 0 || x > n) return false;

        // Paso 2: Frecuencia esperada
        double Fe = (double) n / x;

        // Paso 3: Contar frecuencia observada
        int[] observed = new int[x];
        for (double value : sample) {
            int index = Math.min((int)(value * x), x - 1); // Asegura que el índice sea válido
            observed[index]++;
        }

        // Paso 4: Calcular estadístico chi-cuadrado
        double chi2 = 0.0;
        for (int j = 0; j < x; j++) {
            chi2 += Math.pow(observed[j] - Fe, 2) / Fe;
        }

        // Paso 5: Comparar con valor crítico
        // Se puede obtener el valor crítico usando tablas o librerías externas.
        // Supongamos que lo agregás como conditions.get(1)
        double chi2_critical = Stadistics.chiCuadrado(alfa,n-1);

        return chi2 < chi2_critical;
    }

}

