package org.simulacion;

import org.simulacion.generator.MiddleSquare;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MiddleSquare middleSquare = new MiddleSquare();
        List<Double> numbers = middleSquare.getNumbers(123, 4, 5);
        numbers.forEach(System.out::println);
    }
}