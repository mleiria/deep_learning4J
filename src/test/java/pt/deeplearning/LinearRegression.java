package pt.deeplearning;

import pt.deeplearning.algebra.DLVector;

import java.util.logging.Logger;

public class LinearRegression {

    private static final Logger LOG = Logger.getLogger(LinearRegression.class.getName());

    /**
     * xTrain é a variável de entrada
     */
    private final DLVector xTrain;
    /**
     * yTrain é a variável de saída
     */
    private final DLVector yTrain;

    public LinearRegression(final DLVector xTrain, final DLVector yTrain) {
        this.xTrain = xTrain;
        this.yTrain = yTrain;
    }


    public static void main(String[] args) {
        final DLVector xTrain = new DLVector(new double[]{1.0, 2.0});
        final DLVector yTrain = new DLVector(new double[]{300.0, 500.0});
        LOG.info(String.format("xTrain: %s", xTrain));
        LOG.info(String.format("yTrain: %s", yTrain));
        final int m = xTrain.dimension();
        LOG.info(String.format("Número de exemplos de treino: %d", m));
    }
}
