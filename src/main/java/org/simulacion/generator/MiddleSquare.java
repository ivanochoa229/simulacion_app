package org.simulacion.generator;

import java.util.ArrayList;
import java.util.List;

public class MiddleSquare implements Generator{
    private final List<Double> numbers;

    public MiddleSquare() {
        numbers = new ArrayList<>();
    }

    @Override
    public List<Double> getNumbers(List<Integer> conditions) {
        int seed = conditions.get(0), digits = conditions.get(1), quantity = conditions.get(2);
        numbers.clear();
        for (int i = 0; i < quantity; i++) {
            String x = String.valueOf((long) Math.pow(seed, 2));
            if ((x.length() - digits) % 2 != 0) {
                x += "0";
            }
            if (digits > x.length()) {
                throw new IllegalArgumentException(
                        String.format("No se pueden extraer %d dígitos centrales. El cuadrado de %d solo tiene %d dígitos: %s",
                                digits, seed, x.length(), x)
                );
            }
            int start = (x.length() - digits) / 2;
            int end = start + digits;
            String centralDigits = x.substring(start, end);

            double number = Double.parseDouble("0." + centralDigits);
            numbers.add(number);

            seed = Integer.parseInt(centralDigits);
        }
        return numbers;
    }
}
