package org.simulacion.service;

import org.simulacion.generator.Generator;
import org.simulacion.presentation.dto.MixedCongruentialRequest;

import java.util.ArrayList;
import java.util.List;

public class MixedCongruentialService {
    private final Generator generator;

    public MixedCongruentialService(Generator generator) {
        this.generator = generator;
    }

    public List<Double> generateNumbers(MixedCongruentialRequest request) {
        List<Integer> conditions = buildConditionsList(request);
        return generator.getNumbers(conditions);
    }

    private List<Integer> buildConditionsList(MixedCongruentialRequest request) {
        List<Integer> conditions = new ArrayList<>();
        conditions.add(request.a());
        conditions.add(request.c());
        conditions.add(request.m());
        conditions.add(request.seed());
        conditions.add(request.quantity());
        return conditions;
    }
}
