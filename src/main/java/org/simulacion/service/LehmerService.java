package org.simulacion.service;

import org.simulacion.exception.InvalidInputException;
import org.simulacion.generator.Generator;
import org.simulacion.presentation.dto.LehmerRequest;

import java.util.ArrayList;
import java.util.List;

public class LehmerService {
    private final Generator generator;

    public LehmerService(Generator generator) {
        this.generator = generator;
    }

    public List<Double> generateNumbers(LehmerRequest request) throws InvalidInputException {
        List<Integer> conditions = buildConditionsList(request);
        return generator.getNumbers(conditions);
    }

    private List<Integer> buildConditionsList(LehmerRequest request) {
        List<Integer> conditions = new ArrayList<>();
        conditions.add(request.t());
        conditions.add(request.seed());
        conditions.add(request.quantity());
        return conditions;
    }

}
