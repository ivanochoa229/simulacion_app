package org.simulacion.test;

import java.util.ArrayList;
import java.util.List;
import org.simulacion.utils.Stadistics;

public class UpDownRunsTest implements TestInterface {
    @Override
    public Boolean checkSample(List<Double> sample, List<Integer> conditions) {

        //Obtención del chi cuadrado alfa
        int x = sample.size();
        double alfa = conditions.get(0)/100.0;
        double chi2_critical = Stadistics.chiCuadrado(alfa,x/2);

        //Formar cadena de bits
        String binaryString = "";
        for (int i = 0; i < sample.size(); i++) {
            if (sample.get(i) > 0.5) {
                binaryString = binaryString + "1";
            } else {
                binaryString = binaryString + "0";
            }
        }

        //obtener frecuencias observables
        int[] observedFrecuencies = contarFrecuenciaDeLongitudes(binaryString);

        //obtener frecuencias esperadas
        double[] expectedFrecuencies = new double[observedFrecuencies.length];
        for( int i = 1; i <= expectedFrecuencies.length; i++){
            expectedFrecuencies[i-1] = (x-i+3) / Math.pow(2, i+1);
        }

        //calcular chi cuadrado
        double chi2 = 0.0;
        for (int j = 0; j < expectedFrecuencies.length; j++) {
            chi2 += Math.pow(observedFrecuencies[j] - expectedFrecuencies[j], 2) / expectedFrecuencies[j];
        }

        // comparar ambos estadisticos
        return chi2 < chi2_critical;
    }

    public static int[] contarFrecuenciaDeLongitudes(String bits) {
        List<Integer> frecuencias = new ArrayList<>();

        int contador = 1;
        for (int i = 1; i < bits.length(); i++) {
            if (bits.charAt(i) == bits.charAt(i - 1)) {
                contador++;
            } else {
                asegurarTamanio(frecuencias, contador);
                frecuencias.set(contador - 1, frecuencias.get(contador - 1) + 1);
                contador = 1;
            }
        }

        // Agregamos la última secuencia
        asegurarTamanio(frecuencias, contador);
        frecuencias.set(contador - 1, frecuencias.get(contador - 1) + 1);

        // Convertimos List a array
        return frecuencias.stream().mapToInt(i -> i).toArray();
    }

    private static void asegurarTamanio(List<Integer> lista, int longitud) {
        while (lista.size() < longitud) {
            lista.add(0);
        }
    }

}
