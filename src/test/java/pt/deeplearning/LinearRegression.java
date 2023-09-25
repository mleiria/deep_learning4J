package pt.deeplearning;

import pt.deeplearning.algebra.DLVector;
import pt.deeplearning.linearregression.GradientCost;

import java.util.logging.Logger;

public class LinearRegression {

    private static final Logger LOG = Logger.getLogger(LinearRegression.class.getName());


    /**
     * @param xTrain Valores de treino
     * @param w      Parâmetro do modelo
     * @param b      Parâmetro do modelo
     * @return Valores alvo
     */
    public static DLVector computeModelOutput(final DLVector xTrain, final double w, final double b) {
        final int m = xTrain.dimension();
        final double[] f_wb = new double[m];
        for (int i = 0; i < m; i++) {
            f_wb[i] = w * xTrain.component(i) + b;
        }
        return new DLVector(f_wb);
    }

    /**
     * @param x Dados. m exemplos
     * @param y Valores alvo
     * @param w Parâmetros do modelo
     * @param b Parâmetros do modelo
     * @return O custo total. O custo de usar os valores w e b como parâmetros para a regressão
     * linear
     */
    public static double computeCost(final DLVector x, final DLVector y, final double w, final double b) {
        // Número de exemplos de treino
        final int m = x.dimension();
        double costSum = 0.0;
        for (int i = 0; i < m; i++) {
            final double f_wb = w * x.component(i) + b;
            final double cost = Math.pow(f_wb - y.component(i), 2);
            costSum += cost;
        }
        final double totalCost = (1.0 / (2.0 * m)) * costSum;
        return totalCost;
    }

    /**
     * Calcula o gradiente para a regressão linear
     * @param x Dados, m exemplos
     * @param y Valores alvo
     * @param w Parâmetro do modelo
     * @param b Parâmetro do modelo
     * @return GradientCost um DTO com duas variáveis:<br>
     * dJdW ≥ O gradiente do custo relativamente aoparâmetro w<br>
     * dJdB ≥ O gradiente do custo relativamente ao parâmetro b
     */
    public static GradientCost computeGradient(final DLVector x, final DLVector y, final double w, final double b) {
        // Número de exemplos de treino
        final int m = x.dimension();
        double dJdW = 0.0;
        double dJdB = 0.0;
        for (int i = 0; i < m; i++) {
            final double f_wb = w * x.component(i) + b;
            final double dJdWi = (f_wb - y.component(i)) * x.component(i);
            final double dJdBi = f_wb - y.component(i);
            dJdW += dJdWi;
            dJdB += dJdBi;
        }
        dJdW /= m;
        dJdB /= m;
        return new GradientCost(dJdW, dJdB);
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
