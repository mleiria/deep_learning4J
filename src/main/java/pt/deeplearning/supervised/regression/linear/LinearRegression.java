package pt.deeplearning.supervised.regression.linear;

import pt.deeplearning.algebra.DLVector;
import pt.deeplearning.supervised.regression.GradientCost;
import pt.deeplearning.supervised.regression.GradientDescent;
import pt.mleiria.dto.Pair;

import java.util.ArrayList;
import java.util.List;

public class LinearRegression {

    private final DLVector x;
    private final DLVector y;
    private final double alpha;
    private final int numIters;

    /**
     * @param x        Dados. m exemplos
     * @param y        Valores alvo
     * @param alpha
     * @param numIters
     */
    public LinearRegression(final DLVector x, final DLVector y, final double alpha, final int numIters) {
        this.x = x;
        this.y = y;
        this.alpha = alpha;
        this.numIters = numIters;
    }

    /**
     * @param x
     * @param y
     */
    public LinearRegression(final DLVector x, final DLVector y) {
        this(x, y, 0.0, 10000);
    }


    /**
     * @param xTrain Valores de treino
     * @param w      Parâmetro do modelo
     * @param b      Parâmetro do modelo
     * @return Valores alvo
     */
    public DLVector computeModelOutput(final DLVector xTrain, final double w, final double b) {
        final int m = xTrain.dimension();
        final double[] fwb = new double[m];
        for (int i = 0; i < m; i++) {
            fwb[i] = w * xTrain.component(i) + b;
        }
        return new DLVector(fwb);
    }

    /**
     * @param w Parâmetros do modelo
     * @param b Parâmetros do modelo
     * @return O custo total. O custo de usar os valores w e b como parâmetros para a regressão
     * linear
     */
    private double computeCost(final double w, final double b) {
        // Número de exemplos de treino
        final int m = x.dimension();
        double cost = 0.0;
        for (int i = 0; i < m; i++) {
            final double f_wb = w * x.component(i) + b;
            cost = cost + Math.pow(f_wb - y.component(i), 2);
        }
        final double totalCost = (1.0 / (2.0 * m)) * cost;
        return totalCost;
    }

    /**
     * Calcula o gradiente para a regressão linear
     *
     * @param w Parâmetro do modelo
     * @param b Parâmetro do modelo
     * @return GradientCost um DTO com duas variáveis:<br>
     * dJdW ≥ O gradiente do custo relativamente aoparâmetro w<br>
     * dJdB ≥ O gradiente do custo relativamente ao parâmetro b
     */
    private GradientCost computeGradient(final double w, final double b) {
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
        dJdW = dJdW / m;
        dJdB = dJdB / m;
        return new GradientCost(dJdW, dJdB);
    }

    /**
     * Executa o algoritmo do gradiente descendente para ajustar os parâmetros w e b.
     * Estes parâmetros (w e b) vão sendo atualizados à medida que o número de iterações,
     * numIters vai aumentando
     * @param wIn Valor inicial para w
     * @param bIn Valor inicial para b
     * @return um objeto GradientDescent com os valores finais de w, b e ainda duas listas
     * com dados históricos
     */
    public GradientDescent gradientDescent(final double wIn, final double bIn) {

        // Duas listas para guardar os dados intermédios da computação. Serão usados para
        // mostrar a evolução do gradiente graficamente
        final List<Double> jHistory = new ArrayList<>();
        final List<Pair<Double, Double>> pHistory = new ArrayList<>();

        double b = bIn;
        double w = wIn;
        int i = 0;
        while (i <= numIters) {
            // Cálcula o gradiente e faz um update aos parâmetros usando a função computeGradient
            final GradientCost gradientCost = computeGradient(w, b);
            // Update aos parâmetros
            b = b - alpha * gradientCost.dJdB();
            w = w - alpha * gradientCost.dJdW();
            // Grava o custo J até ao máximo de 100000 para evitar exaustão de recursos
            if (i < 100000) {
                jHistory.add(computeCost(w, b));
                pHistory.add(new Pair<>(w, b));

            }
            i++;
        }
        return new GradientDescent(w, b, jHistory, pHistory);
    }


}
