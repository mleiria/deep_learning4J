package pt.deeplearning;

import pt.deeplearning.algebra.DLVector;

import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;


class SingleNeuronTest {

    private static final Logger LOG = Logger.getLogger(SingleNeuronTest.class.getName());

    /**
     * Ver página 8 do livro Neural Networks from scratch
     */
    @Test
    void testSingleNeuronOutput() {
        /**
         * Começamos com um único neurónio com 3 inputs
         */
        final double[] inputComponents = new double[]{1.0, 2.0, 3.0};
        /**
         * Os pesos (weights) têm a mesma dimensão dos inputs
         */
        final double[] weightComponents = new double[]{0.2, 0.8, -0.5};
        /**
         * Como só temos um neurónio, o bias só tem um valor
         */
        final double bias = 2;

        final DLVector inputs = new DLVector(inputComponents);
        final DLVector weights = new DLVector(weightComponents);

        final SingleNeuron singleNeuron = new SingleNeuron(inputs);
        singleNeuron.setWeights(weights);
        singleNeuron.setBias(bias);
        /**
         * O output desta rede neuronal é:
         */
        final double output = singleNeuron.output();
        LOG.info("Output da rede: " + output);
        assertEquals(2.3, output);
    }

}