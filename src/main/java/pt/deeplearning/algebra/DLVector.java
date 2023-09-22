package pt.deeplearning.algebra;

public class DLVector {

    protected final double[] components;

    public DLVector(final double[] components) {
        this.components = components;
    }

    public DLVector(final int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Illegal vector size.");
        }
        this.components = new double[dimension];
    }

    /**
     * @return int, a dimensão do vector
     */
    public int dimension() {
        return components.length;
    }

    /**
     *
     * @param n the index of the coponent to retreive
     * @return the value on that index
     */
    public double component(final int n){
        return components[n];
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
