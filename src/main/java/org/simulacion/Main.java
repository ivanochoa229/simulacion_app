package org.simulacion;

import org.simulacion.generator.MiddleSquare;
import org.simulacion.generator.MultCongruential;
import org.simulacion.generator.AdditCongruential;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        /* METODO PARTE CENTRAL DEL CUADRADO */
        /* MiddleSquare middleSquare = new MiddleSquare();
         List<Double> numbers = middleSquare.getNumbers(123, 4, 5);
        numbers.forEach(System.out::println); */

        /* METODO CONGRUENCIAL MULTIPLICATIVO */
        /* MultCongruential multCongruential = new MultCongruential();
        List<Double> numbers = multCongruential.getNumbers(1317, 5631, 547, 5);
        numbers.forEach(System.out::println); */

        /* METODO CONGRUENCIAL ADITIVO */
        AdditCongruential additCongruential = new AdditCongruential();
        List<Double> numbers = additCongruential.getNumbers(3, 5147, 3);
        numbers.forEach(System.out::println);

    }
}