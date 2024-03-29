package pt.deeplearning.algebra;

/**
 * Esta classe representa uma matriz no sentido da Álgebra Linear
 */
public class DLMatrix {

    protected final double[][] components;

    /**
     * Cria uma matriz a partir do array de doubles
     *
     * @param components
     */
    public DLMatrix(final double[][] components) {
        this.components = components;
    }

    /**
     * Cria uma matriz inicilaizada a zeros
     *
     * @param n número de linhas
     * @param m número de colunas
     * @throws IllegalArgumentException se o número de linhas ou o número de colunas for <= 0
     */
    public DLMatrix(final int n, final int m) {
        if (n <= 0 || m <= 0) {
            throw new IllegalArgumentException("Illegal matrix size.");
        }
        components = new double[n][m];
    }

    /**
     * Cria uma matriz quadrada
     *
     * @param dimension número de linhas que é igual ao número de colunas
     * @throws IllegalArgumentException se a dimensão for <= 0
     */
    public DLMatrix(final int dimension) {
        if (dimension <= 0) {
            throw new IllegalArgumentException("Illegal matrix size.");
        }
        components = new double[dimension][dimension];
    }

    /**
     * @return o número de linhas da matriz
     */
    public int rows() {
        return components.length;
    }

    /**
     * @return o número de colunas da matriz
     */
    public int columns() {
        return components[0].length;
    }

    /**
     * @param n int row num
     * @param m int column num
     * @return double matrix coords(n,m)
     */
    public double component(final int n, final int m) {
        return components[n][m];
    }


    /**
     * Returns a string representation of the Matrix.
     *
     * @return java.lang.String
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        char[] separator = {'[', ' '};
        int n = rows();
        int m = columns();
        for (int i = 0; i < n; i++) {
            separator[0] = '[';
            for (int j = 0; j < m; j++) {
                sb.append(separator);
                sb.append(components[i][j]);
                separator[0] = ' ';
            }
            sb.append(']');
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     *
     * @return an int[] where int[0] = num rows and int[1] = num columns
     */
    public int[] shape() {
        return new int[]{this.rows(), this.columns()};
    }

    public boolean equals(final DLMatrix anotherMatrix) {
        return DLMatrixUtils.equals(this, anotherMatrix);
    }


}
