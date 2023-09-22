package pt.deeplearning;

import pt.deeplearning.algebra.DLMatrixUtils;
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

    /**
     *
     * @param w Parâmetro do modelo
     * @param b Parâmetro do modelo
     * @return  Valores alvo
     */
    public DLVector computeModelOutput(final double w, final double b){
        final int m = xTrain.dimension();
        final double[] f_wb = new double[m];
        for (int i = 0; i < m; i++) {
            f_wb[i] = w * xTrain.component(i) + b;
        }
        return new DLVector(f_wb);
    }







    public static void main(String[] args) {
        final DLVector xTrain = new DLVector(new double[]{1.0, 2.0});
        final DLVector yTrain = new DLVector(new double[]{300.0, 500.0});
        LOG.info(String.format("xTrain: %s", xTrain));
        LOG.info(String.format("yTrain: %s", yTrain));
        final int m = xTrain.dimension();
        LOG.info(String.format("Número de exemplos de treino: %d", m));
        final LinearRegression lr = new LinearRegression(xTrain,yTrain);
        final double xi = 1.2;
        final double w = 200.0;
        final double b = 100.0;
        final double cost1200sqft = w * xi + b;
        LOG.info(String.format("Preço previsto para uma casa de %s sqft: %f", xi*1000, cost1200sqft));

    }
}
