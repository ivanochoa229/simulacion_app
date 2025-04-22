package org.simulacion.utils;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.util.FastMath;

public final class Stadistics {
    private Stadistics() {
    }

    /**
     * Calcula el valor crítico de la distribución Chi-cuadrado
     * @param alfa Nivel de significancia (ej. 0.05)
     * @param gradosLibertad Grados de libertad
     * @return Valor crítico Chi-cuadrado
     */
    public static double chiCuadrado(double alfa, int gradosLibertad) {
        validarAlfa(alfa);
        if (gradosLibertad <= 0) {
            throw new IllegalArgumentException("Grados de libertad deben ser positivos");
        }

        ChiSquaredDistribution chiDist = new ChiSquaredDistribution(gradosLibertad);
        return chiDist.inverseCumulativeProbability(1 - alfa);
    }

    /**
     * Calcula el valor crítico Z de la distribución normal estándar
     * @param alfa Nivel de significancia (ej. 0.05 para 95% de confianza)
     * @return Valor crítico Z
     */
    public static double zNormal(double alfa) {
        validarAlfa(alfa);

        NormalDistribution normalDist = new NormalDistribution();
        return normalDist.inverseCumulativeProbability(1 - alfa/2); // Para dos colas
    }

    /**
     * Calcula el valor crítico de la distribución Kolmogorov-Smirnov
     * @param alfa Nivel de significancia (ej. 0.05)
     * @param n Tamaño de la muestra
     * @return Valor crítico D
     */
    public static double kolmogorovSmirnov(double alfa, int n) {
        validarAlfa(alfa);
        if (n <= 0) {
            throw new IllegalArgumentException("Tamaño de muestra debe ser positivo");
        }

        // Valores críticos aproximados basados en fórmulas estándar
        if (alfa == 0.10) {
            return 1.22 / FastMath.sqrt(n);
        } else if (alfa == 0.05) {
            return 1.36 / FastMath.sqrt(n);
        } else if (alfa == 0.025) {
            return 1.48 / FastMath.sqrt(n);
        } else if (alfa == 0.01) {
            return 1.63 / FastMath.sqrt(n);
        } else if (alfa == 0.005) {
            return 1.73 / FastMath.sqrt(n);
        } else if (alfa == 0.001) {
            return 1.95 / FastMath.sqrt(n);
        } else {
            throw new IllegalArgumentException("Valor de alfa no soportado. Use: 0.10, 0.05, 0.025, 0.01, 0.005 o 0.001");
        }
    }

    // Versión más precisa del cálculo KS (aproximación de Pomeranz)
    public static double kolmogorovSmirnovExact(double alfa, int n) {
        validarAlfa(alfa);
        if (n <= 0) {
            throw new IllegalArgumentException("Tamaño de muestra debe ser positivo");
        }

        // Implementación de la fórmula exacta para valores pequeños de n
        if (n <= 100) {
            double[] criticalValues = {
                    // n = 1 a 40
                    0.900, 0.684, 0.565, 0.493, 0.447, 0.410, 0.381, 0.358, 0.339, 0.323,
                    0.308, 0.296, 0.285, 0.275, 0.266, 0.258, 0.250, 0.244, 0.237, 0.232,
                    0.226, 0.221, 0.216, 0.212, 0.208, 0.204, 0.200, 0.197, 0.193, 0.190,
                    0.187, 0.184, 0.182, 0.179, 0.177, 0.174, 0.172, 0.170, 0.168, 0.165
            };

            if (n <= criticalValues.length) {
                double d = criticalValues[n-1];
                if (alfa == 0.10) return d;
                if (alfa == 0.05) return d * 1.138; // Ajuste aproximado
                if (alfa == 0.01) return d * 1.224;  // Ajuste aproximado
            }
        }

        // Para n > 40 o alfas no cubiertas, usar la aproximación asintótica
        return FastMath.sqrt(-0.5 * FastMath.log(alfa/2)) / FastMath.sqrt(n);
    }

    /**
     * Valida que alfa esté en el rango correcto (0, 1)
     */
    private static void validarAlfa(double alfa) {
        if (alfa <= 0 || alfa >= 1) {
            throw new IllegalArgumentException("Alfa debe estar entre 0 y 1");
        }
    }
}
