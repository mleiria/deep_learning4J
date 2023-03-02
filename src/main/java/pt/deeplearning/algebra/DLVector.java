package pt.deeplearning.algebra;

public class DLVector {

    protected final double[] components;

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
