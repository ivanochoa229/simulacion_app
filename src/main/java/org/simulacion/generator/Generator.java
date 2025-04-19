package org.simulacion.generator;

import java.util.List;

public interface Generator<T> {
    List<Double> getNumbers(List<T> conditions);
}
