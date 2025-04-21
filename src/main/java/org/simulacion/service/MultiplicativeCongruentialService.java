package org.simulacion.service;

import org.simulacion.generator.Generator;
import org.simulacion.presentation.dto.MultiplicativeCongruentialRequest;

import java.util.ArrayList;
import java.util.List;

public class MultiplicativeCongruentialService {
    private final Generator generator;

    public MultiplicativeCongruentialService(Generator generator) {
        this.generator = generator;
    }

    public List<Double> generateNumbers(MultiplicativeCongruentialRequest request) {
        List<Integer> conditions = buildConditionsList(request);
        return generator.getNumbers(conditions);
    }

    private List<Integer> buildConditionsList(MultiplicativeCongruentialRequest request) {
        List<Integer> conditions = new ArrayList<>();
        conditions.add(request.a());
        conditions.add(request.m());
        conditions.add(request.seed());
        conditions.add(request.quantity());
        return conditions;
    }
}
