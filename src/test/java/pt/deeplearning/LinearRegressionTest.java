package pt.deeplearning;

import org.junit.jupiter.api.Test;
import pt.deeplearning.algebra.DLVector;
import pt.deeplearning.supervised.regression.GradientCost;
import pt.deeplearning.supervised.regression.GradientDescent;
import pt.deeplearning.supervised.regression.linear.LinearRegression;
import pt.mleiria.dto.Pair;
import pt.mleiria.io.utils.TableGenerator;
import pt.mleiria.io.utils.ViewUtilities;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pt.mleiria.collections.immutable.CollectionUtilities.list;
import static pt.mleiria.io.utils.ViewUtilities.formatWith4Decimals;

public class LinearRegressionTest {

    private static final Logger LOG = Logger.getLogger(LinearRegressionTest.class.getName());

    private static final double DELTA = 1e-2;

    @Test
    void gradientDescentSmallData() {
        // Dados de treino
        final DLVector xTrain = new DLVector(new double[]{1.0, 2.0});
        final DLVector yTrain = new DLVector(new double[]{300.0, 500.0});
        // Initialize parameters
        final double w_init = 0.0;
        final double b_init = 0.0;
        // Some gradiente descent settings

        final int iterations = 10000;
        final double tmp_alpha = 1.0e-2;
        // Large value for alpha
        //final int iterations = 10;
        //final double tmp_alpha = 8.0e-1;
        // Run gradient descent
        final LinearRegression linearRegression = new LinearRegression(xTrain, yTrain, tmp_alpha, iterations);
        final GradientDescent gradientDescent = linearRegression.gradientDescent(w_init, b_init);
        LOG.info(String.format("(w, b) found by gradient descent:( %8.4f, %8.4f)",
                gradientDescent.w(), gradientDescent.b()));
        //Show iterations vs cost
        printData(gradientDescent, iterations);
        // Previsions
        DLVector values = new DLVector(new double[]{1.0, 1.2, 2.0});
        DLVector previsions = linearRegression.computeModelOutput(values, gradientDescent.w(), gradientDescent.b());
        LOG.info(String.format("Valor previsto para uma casa com 1000 metros quadrados: %.2f", previsions.component(0)));
        LOG.info(String.format("Valor previsto para uma casa com 1200 metros quadrados: %.2f", previsions.component(1)));
        LOG.info(String.format("Valor previsto para uma casa com 2000 metros quadrados: %.2f", previsions.component(2)));
        assertEquals(199.9929, gradientDescent.w(), DELTA);
        assertEquals(100.0116, gradientDescent.b(), DELTA);
    }

    private void printData(GradientDescent gradientDescent, int iterations){
        final TableGenerator tableGenerator = new TableGenerator();
        final List<String> headerList = list("Iteration", "Cost");
        final List<Double> costHistory = gradientDescent.jHistory();
        final List<List<String>> dataList =
                IntStream.range(0, costHistory.size())
                        .filter(i -> i % Math.ceil((double) iterations / 10) == 0)
                        .mapToObj(i -> list(String.valueOf(i), formatWith4Decimals.apply(costHistory.get(i))))
                        .collect(Collectors.toList());
        LOG.info("\n" + tableGenerator.generateTable(headerList, dataList));
    }


    public static void main(String[] args) {
        final DLVector xTrain = new DLVector(new double[]{1.0, 2.0});
        final DLVector yTrain = new DLVector(new double[]{300.0, 500.0});
        LOG.info(String.format("xTrain: %s", xTrain));
        LOG.info(String.format("yTrain: %s", yTrain));
        final int m = xTrain.dimension();
        LOG.info(String.format("Número de exemplos de treino: %d", m));
        final double xi = 1.2;
        final double w = 200.0;
        final double b = 100.0;
        final double cost1200sqft = w * xi + b;
        LOG.info(String.format("Preço previsto para uma casa de %s sqft: %f", xi * 1000, cost1200sqft));
    }
}
