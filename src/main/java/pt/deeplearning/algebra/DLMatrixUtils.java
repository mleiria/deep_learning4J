package pt.deeplearning.algebra;

import static java.lang.Math.random;

public class DLMatrixUtils {


    /**
     * @return true se a matriz for quadrada
     */
    public static boolean isSquare(final DLMatrix matrix) {
        return matrix.rows() == matrix.columns();
    }

    /**
     * @return Matrix ttransposta
     */
    public static DLMatrix transpose(final DLMatrix matrix) {
        int n = matrix.rows();
        int m = matrix.columns();
        double[][] newComponents = new double[m][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newComponents[j][i] = matrix.components[i][j];
            }
        }
        return new DLMatrix(newComponents);
    }

    /**
     * @param matrixA
     * @param matrixB
     * @return true se as duas matrizes forem iguais
     */
    public static boolean equals(final DLMatrix matrixA, final DLMatrix matrixB) {

        //TODO Implementar
        return false;
    }

    /**
     * @param matrixA
     * @param matrixB
     * @return a soma das duas matrizes
     */
    public static DLMatrix add(final DLMatrix matrixA, final DLMatrix matrixB) {

        //TODO Implementar
        return null;
    }

    /**
     * Adiciona o value a todos os elementos da matriz
     * @param matrixA
     * @param value
     * @return a soma da matriz pelo elemento value
     */
    public static DLMatrix add(final DLMatrix matrixA, final double value) {

        //TODO Implementar
        return null;
    }

    /**
     * @param matrixA
     * @param matrixB
     * @return a diferença das duas matrizes
     */
    public DLMatrix subtract(final DLMatrix matrixA, final DLMatrix matrixB) {

        //TODO Implementar. Nota: podemos reaproveitar o método add para fazer o subtract
        return null;
    }

    /**
     * Multiplica cada elemento da matriz pelo valor
     * @param matrix
     * @param value o valor para multiplicr
     * @return A matriz multiplicada por um escalar
     */
    public DLMatrix multiply(final DLMatrix matrix, final double value) {

        //TODO Implementar
        return null;
    }

    /**
     * @param matrixA
     * @param matrixB
     * @return O produto das duas matrizes
     * @throws IllegalArgumentException se o número de colunas desta matriz for diferente
     *                                  do número de linhas da matriz recebida
     */
    public static DLMatrix multiply(final DLMatrix matrixA, final DLMatrix matrixB) {
        if (matrixA.columns() != matrixB.rows()) {
            throw new IllegalArgumentException("Illegal size.");
        }
        //TODO Implementar
        return null;
    }


    /**
     *
     * @param rows
     * @param cols
     * @return Uma Matrix of 1.0
     */
    public static DLMatrix ones(final int rows, final int cols) {
        final double[][] components = new double[rows][cols];
        // for each row
        for (int i = 0; i < rows; i++) {
            // for each column
            for (int j = 0; j < cols; j++) {
                components[i][j] = 1.0;
            }
        }
        return new DLMatrix(components);
    }

    /**
     * Cria uma matriz com elementos aleatórios distribuídos no
     * intervalo [0,1[
     *
     * @param rows
     * @param cols
     * @return Matrix
     */
    public static DLMatrix rand(final int rows, final int cols) {
        final double[][] components = new double[rows][cols];
        // for each row
        for (int i = 0; i < rows; i++) {
            // for each column
            for (int j = 0; j < cols; j++) {
                components[i][j] = random();
            }
        }
        return new DLMatrix(components);
    }

    /**
     *
     * @param rows
     * @param cols
     * @return Uma matriz Identidade
     */
    public static DLMatrix identity(final int rows, final int cols) {

        //TODO Implementar
        return null;
    }

    /**
     * Operador trace.
     * @param matrix
     * @return A soma dos elementos da diagonal principal
     */
    public static double trace(final DLMatrix matrix){

        //TODO Implementar
        return 0.0;

    }

    /**
     * Compute the scalar product (or dot product) of two vectors.
     *
     * @param v1
     * @param v2
     * @return double the scalar product of the receiver with the argument
     */
    public static double product(final DLVector v1, final DLVector v2) {
        int n = v2.dimension();
        if (v1.dimension() != n) {
            throw new IllegalArgumentException("Dimension of Vector v2 does not match.");
        }
        double sum = 0;
        for (int i = 0; i < v2.dimension(); i++) {
            sum += v1.components[i] * v2.components[i];
        }
        return sum;
    }
}
