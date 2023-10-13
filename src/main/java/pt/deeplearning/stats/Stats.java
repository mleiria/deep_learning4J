package pt.deeplearning.stats;

import pt.deeplearning.algebra.DLVector;
import pt.mleiria.dto.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;

import static pt.mleiria.collections.immutable.CollectionUtilities.dblArrToListFunc;
import static pt.mleiria.collections.immutable.CollectionUtilities.foldLeft;

public class Stats {
    private static final Logger LOG = Logger.getLogger(Stats.class.getName());

    private static final double ZERO_EPS = 1e-12;
    private static final double INV_SQRT_2PI = 1.0 / Math.sqrt(2.0 * Math.PI);

    private final DLVector vector;

    private final double min;
    private final double max;
    private final double sum;
    private final double sumSqr;

    private static final Function<Pair<Double, Double>, Function<Double, Pair<Double, Double>>> funcMinMax =
            x -> y -> {
                double a1 = y < x._1() ? y : x._1();
                double a2 = y > x._2() ? y : x._2();
                return new Pair<>(a1, a2);
            };

    private static final Function<Pair<Double, Double>, Function<Double, Pair<Double, Double>>> funcSumSumSqr =
            x -> y -> {
                double sumTmp = y + x._1();
                double sumSqrTmp = y * y + x._2();
                return new Pair<>(sumTmp, sumSqrTmp);
            };

    public Stats(final DLVector vector) {
        this.vector = vector;
        final List<Double> list = dblArrToListFunc.apply(vector.components());
        final Pair<Double, Double> minMax = foldLeft(list, new Pair<>(Double.MAX_VALUE, Double.MIN_VALUE), funcMinMax);
        min = minMax._1();
        max = minMax._2();
        final Pair<Double, Double> sumSumSqr = foldLeft(list, new Pair<>(0.0, 0.0), funcSumSumSqr);
        sum = sumSumSqr._1();
        sumSqr = sumSumSqr._2();
    }

    /**
     * @return Arithmetic mean of the vector values
     */
    public double mean() {
        return sum / vector.dimension();
    }

    /**
     * @return Computation of variance for the array values
     */
    public double variance() {
        return (sumSqr - mean() * mean() * vector.dimension()) / (vector.dimension() - 1.0);
    }

    /**
     * @return Computation of standard deviation for the array values
     */
    public double stDev() {
        return variance() < ZERO_EPS ? ZERO_EPS : Math.sqrt(variance());
    }

    /**
     * Fast normalization of values within a range of [0, 1]
     *
     * @return vector with normalized values
     */
    public DLVector normalize() {
        final double range = max - min;
        double[] res = Arrays.stream(vector.components())
                .map(x -> (x - min) / range)
                .toArray();
        return new DLVector(res);
    }

    /**
     * Normalize the data within a range [l, h]
     *
     * @param h higher bound for the normalization
     * @param l lower bound for the normalization
     * @return vector of values normalized over the interval [h, l]
     */
    public DLVector normalize(final double h, final double l) {
        final double range = h - l;
        double[] res = Arrays.stream(vector.components())
                .map(x -> (x - l) / range)
                .toArray();
        return new DLVector(res);
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }

    public double getSum() {
        return sum;
    }

    public double getSumSqr() {
        return sumSqr;
    }
}
