package org.simulacion.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Arrays;

import org.simulacion.utils.Stadistics;

public class KolmogorovSmirnovTest implements TestInterface {
    public KolmogorovSmirnovTest(List<Double> sharedNumbers, List<Integer> conditions) {
    }

    @Override
    public Boolean checkSample(List<Double> sample, List<Integer> conditions) {

        int alphaInt = conditions.get(0);
        double alpha = alphaInt / 100.0;

        //Ordenar el array de numeros
        Collections.sort(sample);

        //Crear array de la distribución acumulada
        List<Double> distributionAcumulated = new ArrayList<>();
        for (int i = 1; i <= sample.size(); i++) {
            distributionAcumulated.add((double) i / sample.size());
        }

        //Determinar el estadistico K-S
        double ksEstadistic = 0.0;
        for (int i = 0; i < sample.size(); i++) {
            double diff = Math.abs(distributionAcumulated.get(i) - sample.get(i));
            if (diff > ksEstadistic) {
                ksEstadistic = diff;
            }
        }

        //Calcular el estadistico
        double dCritical = Stadistics.kolmogorovSmirnov(alpha, sample.size());

        //retornar true o false
        return ksEstadistic < dCritical;
    }
}
