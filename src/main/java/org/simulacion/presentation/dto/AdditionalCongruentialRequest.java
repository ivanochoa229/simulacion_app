package org.simulacion.presentation.dto;

import java.util.List;

public record AdditionalCongruentialRequest(int k, int module, int quantity, List<Integer> seeds) {
}
