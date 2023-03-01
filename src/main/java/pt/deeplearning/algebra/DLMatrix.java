package pt.deeplearning.algebra;

/**
 * Esta classe representa uma matriz no sentido da Álgebra Linear
 */
public class DLMatrix {

    private final double[][] components;

    /**
     * Cria uma matriz a partir do arrau de doubles
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
     * @return true se a matriz for quadrada
     */
    public boolean isSquare() {
        return rows() == columns();
    }

    /**
     * @return Matrix ttransposta
     */
    public DLMatrix transpose() {
        int n = rows();
        int m = columns();
        double[][] newComponents = new double[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newComponents[j][i] = components[i][j];
            }
        }
        return new DLMatrix(newComponents);
    }

    /**
     * @param matrix
     * @return true se as duas matrizes forem iguais
     */
    public boolean equals(final DLMatrix matrix) {

        //TODO Implementar
        return false;
    }

    /**
     * @param matrix
     * @return a soma das duas matrizes
     */
    public DLMatrix add(final DLMatrix matrix) {

        //TODO Implementar
        return null;
    }

    /**
     * @param matrix
     * @return a diferença das duas matrizes
     */
    public DLMatrix subtract(final DLMatrix matrix) {

        //TODO Implementar. Nota: podemos reaproveitar o método add para fazer o subtract
        return null;
    }

    /**
     * @param value o valor para multiplicr
     * @return A matriz multiplicada por um escalar
     */
    public DLMatrix multiply(final double value) {

        //TODO Implementar
        return null;
    }

    /**
     * @param matrix
     * @return O produto das duas matrizes
     */
    public DLMatrix multiply(final DLMatrix matrix) {

        //TODO Implementar
        return null;
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


}
