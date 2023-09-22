package pt.deeplearning.algebra;

import jdk.incubator.vector.DoubleVector;
import jdk.incubator.vector.VectorSpecies;

import java.util.function.BiPredicate;
import java.util.function.Function;

import static java.lang.Math.random;

public class DLMatrixUtils {

    private DLMatrixUtils(){}


    /**
     * @return true se a matriz for quadrada
     */
    public static boolean isSquare(final DLMatrix matrix) {
        return matrix.rows() == matrix.columns();
    }

    /**
     * @return Matrix transposta
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
     * @return true se as matrizes forem iguais (o mesmo número de linhas, o mesmo número de colunas e
     * os valores das células são iguais)
     */
    public static boolean equals(final DLMatrix matrixA, final DLMatrix matrixB) {
        if (matrixA.rows() != matrixB.rows()) {
            return false;
        }
        if (matrixA.columns() != matrixB.columns()) {
            return false;
        }
        // For all rows
        for (int i = 0; i < matrixA.rows(); i++) {
            // For all columns
            for (int j = 0; j < matrixA.columns(); j++) {
                if (matrixA.component(i, j) != matrixB.component(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * //TODO exception matrixA.rows() != matrixB.rows()
     * //TODO exception matrixA.columns() != matrixB.columns()
     *
     * @param matrixA
     * @param matrixB
     * @return a soma das duas matrizes
     */
    public static DLMatrix add(final DLMatrix matrixA, final DLMatrix matrixB) {

        if (matrixA.rows() != matrixB.rows()) {
            throw new IllegalArgumentException("Rows must have the same size");
        }
        if (matrixA.columns() != matrixB.columns()) {
            throw new IllegalArgumentException("Columns must have the same size");
        }
        int n = matrixA.rows();
        int m = matrixA.columns();

        return engine(n, m, matrixA, matrixB, x -> y -> x + y);

    }

    /**
     * Adiciona o value a todos os elementos da matriz
     *
     * @param matrixA
     * @param value
     * @return a soma da matriz pelo elemento value
     */
    public static DLMatrix add(final DLMatrix matrixA, final Double value) {

        int n = matrixA.rows();
        int m = matrixA.columns();

        return engine(n, m, matrixA, value, x -> y -> x + y);
    }

    /**
     * @param matrixA
     * @param matrixB
     * @return a diferença das duas matrizes
     */
    public static DLMatrix subtract(final DLMatrix matrixA, final DLMatrix matrixB) {

        if (matrixA.rows() != matrixB.rows()) {
            throw new IllegalArgumentException("Rows must have the same size");
        }
        if (matrixA.columns() != matrixB.columns()) {
            throw new IllegalArgumentException("Columns must have the same size");
        }
        int n = matrixA.rows();
        int m = matrixA.columns();

        return engine(n, m, matrixA, matrixB, x -> y -> x - y);
    }

    /**
     * Multiplica cada elemento da matriz pelo valor
     *
     * @param matrix
     * @param value  o valor para multiplicr
     * @return A matriz multiplicada por um escalar
     */
    public static DLMatrix multiply(final DLMatrix matrix, final Double value) {
        int n = matrix.rows();
        int m = matrix.columns();
        return engine(n, m, matrix, value, x -> y -> x * y);
    }

    /**
     * @param matrix
     * @return Uma Matrix with the values sqared
     */
    public static DLMatrix square(final DLMatrix matrix) {
        return engine(matrix, x -> Math.pow(x, 2));
    }

    /**
     * @param rows
     * @param cols
     * @return Uma Matrix of 1.0
     */
    public static DLMatrix ones(final int rows, final int cols) {
        return engine(rows, cols, x -> 1.0);
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
        return engine(rows, cols, x -> random());
    }

    /**
     * @param rows
     * @param cols
     * @return Uma matriz Identidade
     */
    public static DLMatrix identity(final int rows, final int cols) {
        return engine(rows, cols, x -> 1.0, (i, j) -> i.intValue() == j.intValue());
    }

    /**
     * Operador trace.
     *
     * @param matrix
     * @return A soma dos elementos da diagonal principal
     */
    public static double trace(final DLMatrix matrix) {
        return engine(matrix, x -> x, (i, j) -> i.intValue() == j.intValue());

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

    private static final VectorSpecies<Double> DOUBLE_VECTOR_SPECIES = DoubleVector.SPECIES_PREFERRED;

    public static double[] addTwoVectorsWithMasks(double[] arr1, double[] arr2) {
        double[] finalResult = new double[arr1.length];
        int i = 0;
        for (; i < DOUBLE_VECTOR_SPECIES.loopBound(arr1.length); i += DOUBLE_VECTOR_SPECIES.length()) {
            var mask = DOUBLE_VECTOR_SPECIES.indexInRange(i, arr1.length);
            var v1 = DoubleVector.fromArray(DOUBLE_VECTOR_SPECIES, arr1, i, mask);
            var v2 = DoubleVector.fromArray(DOUBLE_VECTOR_SPECIES, arr2, i, mask);
            var result = v1.add(v2, mask);
            result.intoArray(finalResult, i, mask);
        }

        // tail cleanup loop
        for (; i < arr1.length; i++) {
            finalResult[i] = arr1[i] + arr2[i];
        }
        return finalResult;
    }

    /**
     * Nº cols = Nº linhas
     *
     * @param a
     * @param b
     * @return
     */
    public static DLMatrix mul(final DLMatrix a, final DLMatrix b) {
        int p = a.components[0].length; //cols
        int n = a.components.length; //rows
        int m = b.components[0].length; //cols

        if (p != b.components.length) {
            throw new IllegalArgumentException("Matrix mismatch! " +
                    p + " != " + b.components.length);
        }
        final double[][] res = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                double sum = 0.0;
                for (int k = 0; k < p; k++) {
                    sum += a.components[i][k] * b.components[k][j];
                    res[i][j] = sum;
                }
            }
        }
        return new DLMatrix(res);
    }

    private static DLMatrix engine(int rows, int cols, Function<Double, Double> f, BiPredicate<Integer, Integer> p) {
        final double[][] components = new double[rows][cols];
        // for each row
        for (int i = 0; i < rows; i++) {
            // for each column
            for (int j = 0; j < cols; j++) {
                if (p.test(i, j)) {
                    components[i][j] = f.apply(components[i][j]);
                }
            }
        }
        return new DLMatrix(components);
    }

    private static double engine(DLMatrix matrix, Function<Double, Double> f, BiPredicate<Integer, Integer> p) {
        double res = 0.0;
        // for each row
        for (int i = 0; i < matrix.rows(); i++) {
            // for each column
            for (int j = 0; j < matrix.columns(); j++) {
                if (p.test(i, j)) {
                    res += f.apply(matrix.component(i, j));
                }
            }
        }
        return res;
    }

    private static DLMatrix engine(final DLMatrix matrix, Function<Double, Double> f) {
        final double[][] components = new double[matrix.rows()][matrix.columns()];
        // for each row
        for (int i = 0; i < matrix.rows(); i++) {
            // for each column
            for (int j = 0; j < matrix.columns(); j++) {
                components[i][j] = f.apply(matrix.components[i][j]);
            }
        }
        return new DLMatrix(components);
    }

    private static DLMatrix engine(final int rows, final int cols,
                                   final DLMatrix matrixA, final Double value,
                                   Function<Double, Function<Double, Double>> oper) {

        final double[][] components = new double[rows][cols];
        // for each row
        for (int i = 0; i < rows; i++) {
            // for each column
            for (int j = 0; j < cols; j++) {
                components[i][j] = oper.apply(matrixA.component(i, j)).apply(value);
            }
        }
        return new DLMatrix(components);
    }

    private static DLMatrix engine(final int rows, final int cols,
                                   final DLMatrix matrixA, final DLMatrix matrixB,
                                   Function<Double, Function<Double, Double>> oper) {

        final double[][] components = new double[rows][cols];
        // for each row
        for (int i = 0; i < rows; i++) {
            // for each column
            for (int j = 0; j < cols; j++) {
                //components[i][j] = oper.apply(matrixA.component(i, j), matrixB.component(i, j));
                components[i][j] = oper.apply(matrixA.component(i, j)).apply(matrixB.component(i, j));
            }
        }
        return new DLMatrix(components);
    }

    private static DLMatrix engine(final int rows, final int cols, Function<Double, Double> f) {
        final double[][] components = new double[rows][cols];
        // for each row
        for (int i = 0; i < rows; i++) {
            // for each column
            for (int j = 0; j < cols; j++) {
                components[i][j] = f.apply(components[i][j]);
            }
        }
        return new DLMatrix(components);
    }
}
