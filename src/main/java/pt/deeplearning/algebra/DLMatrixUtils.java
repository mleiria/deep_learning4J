package pt.deeplearning.algebra;

import static java.lang.Math.random;

public class DLMatrixUtils {

    /**
     *
     * @param rows
     * @param cols
     * @return Uma Matrix of 1.0
     */
    public static DLMatrix Ones(final int rows, final int cols) {
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
}
