package pt.deeplearning.algebra;

public class DLVector {

    private double[] components;

    public DLVector(final double[] components) {
        this.components = components;
    }

    /**
     * @return int, a dimensão do vector
     */
    public int dimension() {
        return components.length;
    }

    /**
     * Compute the scalar product (or dot product) of two vectors.
     *
     * @param v
     * @return double the scalar product of the receiver with the argument
     */
    public double product(final DLVector v) {
        int n = v.dimension();
        if (components.length != n) {
            throw new IllegalArgumentException("Dimension of Vector v does not match.");
        }
        return secureProduct(v);
    }

    /**
     * @param v
     * @return double o produto interno entre dois vectores
     */
    private double secureProduct(final DLVector v) {
        double sum = 0;
        for (int i = 0; i < v.dimension(); i++) {
            sum += components[i] * v.components[i];
        }
        return sum;
    }

    /**
     * Represetnação em String do DLVector
     *
     * @return java.lang.String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        char[] separator = {'[', ' '};
        for (final double component : components) {
            sb.append(separator);
            sb.append(component);
            separator[0] = ',';
        }
        sb.append(']');
        return sb.toString();
    }

}
