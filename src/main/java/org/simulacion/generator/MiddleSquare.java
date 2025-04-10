package org.simulacion.generator;

import java.util.ArrayList;
import java.util.List;

public class MiddleSquare {
    List<Double> numbers;

    public MiddleSquare() {
        numbers = new ArrayList<>();
    }

    public List<Double> getNumbers(int sead, int digits, int quantity){
        for (int i = 0; i < quantity; i++) {
            String x = String.valueOf((long) Math.pow(sead, 2)); // sead² como String
            if ((x.length() - digits) % 2 != 0) {
                x += "0";
            }
            int start = (x.length() - digits) / 2;
            int end = start + digits;
            String centralDigits = x.substring(start, end);

            double number = Double.parseDouble("0." + centralDigits);
            numbers.add(number);

            sead = Integer.parseInt(centralDigits);
        }
        return numbers;
    }
}
