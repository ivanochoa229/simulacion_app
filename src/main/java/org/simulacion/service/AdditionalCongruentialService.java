package org.simulacion.service;

import org.simulacion.exception.InvalidInputException;
import org.simulacion.generator.Generator;
import org.simulacion.presentation.dto.AdditionalCongruentialRequest;

import java.util.ArrayList;
import java.util.List;

public class AdditionalCongruentialService {
    private final Generator generator;

    public AdditionalCongruentialService(Generator generator) {
        this.generator = generator;
    }

    public List<Double> generateNumbers(AdditionalCongruentialRequest request) throws InvalidInputException {
        //validateRequest(request);

        List<Integer> conditions = buildConditionsList(request);

        return generator.getNumbers(conditions);
    }

    private List<Integer> buildConditionsList(AdditionalCongruentialRequest request) {
        List<Integer> conditions = new ArrayList<>();
        conditions.add(request.k());
        conditions.add(request.module());
        conditions.add(request.quantity());
        conditions.addAll(request.seeds());
        return conditions;
    }

    private void validateRequest(AdditionalCongruentialRequest request) throws InvalidInputException {
        if (request.k() <= 0) {
            throw new InvalidInputException("k debe ser mayor que cero");
        }

        if (request.module() <= 0) {
            throw new InvalidInputException("El módulo debe ser mayor que cero");
        }

        if (request.quantity() <= 0) {
            throw new InvalidInputException("La cantidad debe ser mayor que cero");
        }

        if (request.seeds() == null || request.seeds().size() != request.k()) {
            throw new InvalidInputException(
                    String.format("Se requieren exactamente %d semillas", request.k())
            );
        }

        for (int seed : request.seeds()) {
            if (seed < 0) {
                throw new InvalidInputException("Las semillas no pueden ser negativas");
            }
            if (seed >= request.module()) {
                throw new InvalidInputException(
                        String.format("Semilla %d debe ser menor que el módulo %d", seed, request.module())
                );
            }
        }
    }
}
