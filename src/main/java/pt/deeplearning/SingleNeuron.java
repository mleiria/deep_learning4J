package pt.deeplearning;

import pt.deeplearning.algebra.DLMatrixUtils;
import pt.deeplearning.algebra.DLVector;

public class SingleNeuron {

    private final DLVector inputs;
    private DLVector weights;
    private double bias;

    public SingleNeuron(final DLVector inputs){
        this.inputs = inputs;
    }

    public double output(){
        return DLMatrixUtils.product(inputs, weights) + bias;
    }

    public DLVector getInputs() {
        return inputs;
    }

    public DLVector getWeights() {
        return weights;
    }

    public void setWeights(DLVector weights) {
        this.weights = weights;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }
}
