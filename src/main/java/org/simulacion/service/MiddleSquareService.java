package org.simulacion.service;

import org.simulacion.exception.InvalidInputException;
import org.simulacion.generator.Generator;
import org.simulacion.presentation.dto.MiddleSquareRequest;

import java.util.List;

public class MiddleSquareService {

    private final Generator generator;

    public MiddleSquareService(Generator generator) {
        this.generator = generator;
    }

    public List<Double> generateNumbers(MiddleSquareRequest request) {
        validateInput(request);
        List<Integer> conditions = List.of(
                request.seed(),
                request.digits(),
                request.quantity()
        );
        return generator.getNumbers(conditions);
    }

    private void validateInput(MiddleSquareRequest request) {
        if (request.digits() <= 0 || request.quantity() <= 0) {
            throw new InvalidInputException("Dígitos y cantidad deben ser > 0.");
        }
    }
}
