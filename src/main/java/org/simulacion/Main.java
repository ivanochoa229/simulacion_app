package org.simulacion;

import org.simulacion.generator.Lehmer;
import org.simulacion.generator.MiddleSquare;
import org.simulacion.generator.MultCongruential;
import org.simulacion.generator.AdditCongruential;
import org.simulacion.generator.MixedCongruential;

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
        System.out.println("Números generados por el Metodo Congruencial Aditivo:\t");
        numbers.forEach(System.out::println);

        /* METODO LEHMER */
        Lehmer lehmer = new Lehmer();
        List<Double> numbersLehmer = lehmer.getNumbers(73, 35451, 8);
        System.out.println("Numeros generados por el Metodo Lehmer:\t");
        numbersLehmer.forEach(System.out::println);
        
        /*METODO CONGRUENCIAL MIXTO*/
       MixedCongruential mc = new MixedCongruential(5, 1, 16, 7);
       System.out.println("Números generados por el Método Congruencial Mixto:\t");
       mc.generate(10).forEach(System.out::println);

    }
}