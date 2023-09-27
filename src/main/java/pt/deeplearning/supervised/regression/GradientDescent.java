package pt.deeplearning.supervised.regression;

import pt.mleiria.dto.Pair;

import java.util.List;

public record GradientDescent(double w, double b, List<Double> jHistory, List<Pair<Double, Double>> pHistory) {
}
