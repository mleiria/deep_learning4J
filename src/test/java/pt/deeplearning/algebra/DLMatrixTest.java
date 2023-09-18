package pt.deeplearning.algebra;


import org.junit.jupiter.api.Test;
import pt.mleiria.StopWatch;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;


class DLMatrixTest {
    private static final Logger LOG = Logger.getLogger(DLMatrixTest.class.getName());

    @Test
    void rows() {
        final DLMatrix matrix = new DLMatrix(4, 5);
        assertEquals(4, matrix.rows());
        LOG.info("\n" + matrix.toString());
    }

    @Test
    void columns() {
    }

    @Test
    void isSquare() {
    }

    @Test
    void transpose() {
        double[][] components = new double[4][4];
        components[0][0] = 16;
        components[0][1] = 2;
        components[0][2] = 3;
        components[0][3] = 13;
        components[1][0] = 5;
        components[1][1] = 11;
        components[1][2] = 10;
        components[1][3] = 8;
        components[2][0] = 9;
        components[2][1] = 7;
        components[2][2] = 6;
        components[2][3] = 12;
        components[3][0] = 4;
        components[3][1] = 14;
        components[3][2] = 15;
        components[3][3] = 1;
        DLMatrix a = new DLMatrix(components);
        LOG.info("Matrix a: \n" + a.toString());
        DLMatrix b = DLMatrixUtils.transpose(a);
        double[][] componentsTranspose = new double[4][4];
        componentsTranspose[0][0] = 16;
        componentsTranspose[0][1] = 5;
        componentsTranspose[0][2] = 9;
        componentsTranspose[0][3] = 4;
        componentsTranspose[1][0] = 2;
        componentsTranspose[1][1] = 11;
        componentsTranspose[1][2] = 7;
        componentsTranspose[1][3] = 14;
        componentsTranspose[2][0] = 3;
        componentsTranspose[2][1] = 10;
        componentsTranspose[2][2] = 6;
        componentsTranspose[2][3] = 15;
        componentsTranspose[3][0] = 13;
        componentsTranspose[3][1] = 8;
        componentsTranspose[3][2] = 12;
        componentsTranspose[3][3] = 1;
        DLMatrix c = new DLMatrix(componentsTranspose);
        LOG.info("Matrix a transposed: \n" + b.toString());
        LOG.info("Matrix toCheck: \n" + c.toString());
        final boolean isEquals = b.equals(c);
        assertTrue(isEquals);
    }


    @Test
    void ifTheMatrixAreEqualsThenTrue() {
        final double[][] componentsA = new double[2][2];
        // linha 0, coluna 0
        componentsA[0][0] = 1.0;
        // linha 0, coluna 1
        componentsA[0][1] = 2.0;
        // linha 1, coluna 0
        componentsA[1][0] = 3.0;
        // linha 1, coluna 1
        componentsA[1][1] = 4.0;
        final DLMatrix matrixA = new DLMatrix(componentsA);
        // Vamos fazer print para ver o aspecto:
        LOG.info("Matrix A: \n" + matrixA);

        final double[][] componentsB = new double[2][2];
        // linha 0, coluna 0
        componentsB[0][0] = 1.0;
        // linha 0, coluna 1
        componentsB[0][1] = 2.0;
        // linha 1, coluna 0
        componentsB[1][0] = 3.0;
        // linha 1, coluna 1
        componentsB[1][1] = 4.0;
        final DLMatrix matrixB = new DLMatrix(componentsB);
        // Vamos fazer print para ver o aspecto:
        LOG.info("Matrix B: \n" + matrixB);

        final boolean isEquals = DLMatrixUtils.equals(matrixA, matrixB);

        //Deverá dar true visto que as matrizes são iguais
        assertTrue(isEquals);
    }

    @Test
    void ifTheMatrixAreNotEqualsThenFalse() {
        final double[][] componentsA = new double[2][2];
        // linha 0, coluna 0
        componentsA[0][0] = 1.0;
        // linha 0, coluna 1
        componentsA[0][1] = 2.0;
        // linha 1, coluna 0
        componentsA[1][0] = 56.0;
        // linha 1, coluna 1
        componentsA[1][1] = 4.0;
        final DLMatrix matrixA = new DLMatrix(componentsA);
        // Vamos fazer print para ver o aspecto:                        
        LOG.info("Matrix A: \n" + matrixA);

        final double[][] componentsB = new double[2][2];
        // linha 0, coluna 0
        componentsB[0][0] = 1.0;
        // linha 0, coluna 1
        componentsB[0][1] = 2.0;
        // linha 1, coluna 0
        componentsB[1][0] = 3.0;
        // linha 1, coluna 1
        componentsB[1][1] = 4.0;
        final DLMatrix matrixB = new DLMatrix(componentsB);
        // Vamos fazer print para ver o aspecto:
        LOG.info("Matrix B: \n" + matrixB);

        final boolean isEquals = DLMatrixUtils.equals(matrixA, matrixB);

        //Deverá dar false visto que as matrizes são diferentes
        assertFalse(isEquals);
    }


    @Test
    /*
      Neste teste fiz a soma de duas matrizes 2 por 2,
      Eu criei uma terceira matriz (matrixC) que é a resposta
      correta da soma e comparei (.equals que foi feito pelo manuel)
      com a matriz da soma.
     */
    void add() {

        final double[][] componentsA = new double[2][2];
        componentsA[0][0] = 0.0;
        componentsA[0][1] = 1.0;
        componentsA[1][0] = 2.0;
        componentsA[1][1] = 3.0;
        final DLMatrix matrixA = new DLMatrix(componentsA);
        LOG.info("Matrix A: \n" + matrixA);

        final double[][] componentsB = new double[2][2];
        componentsB[0][0] = 1.0;
        componentsB[0][1] = 2.0;
        componentsB[1][0] = 3.0;
        componentsB[1][1] = 3.0;
        final DLMatrix matrixB = new DLMatrix(componentsB);
        LOG.info("Matrix B: \n" + matrixB);

        final double[][] componentsC = new double[2][2];
        componentsC[0][0] = 1.0;
        componentsC[0][1] = 3.0;
        componentsC[1][0] = 5.0;
        componentsC[1][1] = 6.0;
        final DLMatrix matrixC = new DLMatrix(componentsC);
        // Vamos fazer print para ver o aspecto:
        LOG.info("Matrix C: \n" + matrixC);

        final DLMatrix matrixAdd = DLMatrixUtils.add(matrixA, matrixB);

        final boolean isEquals = DLMatrixUtils.equals(matrixAdd, matrixC);

        // Devera dar true visto que sao iguais
        assertTrue(isEquals);

    }

    @Test
    void addWithDifferentDimensions() {
        try {

            final double[][] componentsA = new double[2][2];
            componentsA[0][0] = 0.0;
            componentsA[0][1] = 1.0;
            componentsA[1][0] = 2.0;
            componentsA[1][1] = 3.0;
            final DLMatrix matrixA = new DLMatrix(componentsA);
            LOG.info("Matrix A: \n" + matrixA);

            final double[][] componentsB = new double[2][1];
            componentsB[0][0] = 1.0;
            componentsB[1][0] = 2.0;
            final DLMatrix matrixB = new DLMatrix(componentsB);
            LOG.info("Matrix B: \n" + matrixB);

            DLMatrixUtils.add(matrixA, matrixB);
            fail();

        } catch (IllegalArgumentException e) {
            LOG.info("Error: " + e.getMessage());
        }
    }

    @Test
    void subtract() {
    }

    @Test
    void multiply() {
        try {
            final double[][] componentsMatrixA = new double[3][4];
            componentsMatrixA[0][0] = 1.0;
            componentsMatrixA[0][1] = 2.0;
            componentsMatrixA[0][2] = 3.0;
            componentsMatrixA[0][3] = 4.0;
            componentsMatrixA[1][0] = 5.0;
            componentsMatrixA[1][1] = 6.0;
            componentsMatrixA[1][2] = 7.0;
            componentsMatrixA[1][3] = 8.0;
            componentsMatrixA[2][0] = 9.0;
            componentsMatrixA[2][1] = 10.0;
            componentsMatrixA[2][2] = 11.0;
            componentsMatrixA[2][3] = 12.0;
            final DLMatrix matrixA = new DLMatrix(componentsMatrixA);
            LOG.info("Matrix a: \n" + matrixA.toString());

            final double[][] componentsMatrixB = new double[4][2];
            componentsMatrixB[0][0] = 13.0;
            componentsMatrixB[0][1] = 14.0;
            componentsMatrixB[1][0] = 15.0;
            componentsMatrixB[1][1] = 16.0;
            componentsMatrixB[2][0] = 17.0;
            componentsMatrixB[2][1] = 18.0;
            componentsMatrixB[3][0] = 19.0;
            componentsMatrixB[3][1] = 20.0;
            final DLMatrix matrixB = new DLMatrix(componentsMatrixB);
            LOG.info("Matrix b: \n" + matrixB.toString());

            final DLMatrix result = DLMatrixUtils.mul(matrixA, matrixB);
            LOG.info("Matrix result: \n" + result.toString());
            assertEquals(3, result.rows());
            assertEquals(2, result.columns());

            assertEquals(170.0, result.component(0, 0));
            assertEquals(180.0, result.component(0, 1));
            assertEquals(426.0, result.component(1, 0));
            assertEquals(452.0, result.component(1, 1));
            assertEquals(682.0, result.component(2, 0));
            assertEquals(724.0, result.component(2, 1));
        } catch (Exception e) {
            LOG.severe(e.getMessage());
            fail();
        }


    }

    @Test
    void testMultiply() {
    }

    @Test
    void testSquare(){
        final double[][] componentsMatrixA = new double[2][2];
        componentsMatrixA[0][0] = 2.0;
        componentsMatrixA[0][1] = 2.0;
        componentsMatrixA[1][0] = 2.0;
        componentsMatrixA[1][1] = 2.0;

        final double[][] componentsMatrixB = new double[2][2];
        componentsMatrixB[0][0] = 4.0;
        componentsMatrixB[0][1] = 4.0;
        componentsMatrixB[1][0] = 4.0;
        componentsMatrixB[1][1] = 4.0;

        DLMatrix res = DLMatrixUtils.square(new DLMatrix(componentsMatrixA));
        LOG.info("Matrix : \n" + res);
        assertTrue(DLMatrixUtils.square(new DLMatrix(componentsMatrixA)).equals(new DLMatrix(componentsMatrixB)));

    }


    @Test
    void complexTest(){
        final double[][] componentsMatrixR = new double[2][2];
        componentsMatrixR[0][0] = -2.0;
        componentsMatrixR[0][1] = 0.0;
        componentsMatrixR[1][0] = 0.0;
        componentsMatrixR[1][1] = 2.0;
        final DLMatrix matrixR = new DLMatrix(componentsMatrixR);
        LOG.info("Matrix R: \n" + matrixR);

        final double[][] componentsMatrixX = new double[1][2];
        componentsMatrixX[0][0] = 1.0;
        componentsMatrixX[0][1] = 1.0;
        final DLMatrix matrixX = new DLMatrix(componentsMatrixX);
        LOG.info("Matrix X: \n" + matrixX);
        final DLMatrix matrixXTranspose = DLMatrixUtils.transpose(matrixX);
        LOG.info("Matrix X transpose: \n" + matrixXTranspose);

        final DLMatrix y = DLMatrixUtils.mul(matrixR, matrixXTranspose);

        LOG.info("Matrix y: \n" + y);
        assertNotNull(y);

    }
    @Test
    void complexTest1() {
        final double[][] componentsMatrixR = new double[2][2];
        componentsMatrixR[0][0] = -2.0;
        componentsMatrixR[0][1] = 0.0;
        componentsMatrixR[1][0] = 0.0;
        componentsMatrixR[1][1] = 2.0;
        final DLMatrix matrixR = new DLMatrix(componentsMatrixR);
        LOG.info("Matrix R: \n" + matrixR);

    }
    @Test
    void ones() {
        final DLMatrix matrixR = DLMatrixUtils.ones(2, 2);
        for(int i = 0; i < matrixR.rows(); i++){
            for(int j = 0; j < matrixR.columns(); j++){
                assertTrue(matrixR.component(i, j) == 1.0);
            }
        }
        LOG.info("Matrix R: \n" + matrixR);
    }

    @Test
    void matrixMultiply(){
        double[][] matrix1 = {
                {1.0, 2.0},
                {3.0, 4.0},
                {5.0, 6.0}
        };

        double[][] matrix2 = {
                {7.0, 8.0, 9.0},
                {10.0, 11.0, 12.0}
        };
        //Using vector API
        DLMatrix matrixRes = DLMatrixUtils.matrixMultiply(new DLMatrix(matrix1), new DLMatrix(matrix2));
        //Old skool
        DLMatrix matrixRes1 = DLMatrixUtils.mul(new DLMatrix(matrix1), new DLMatrix(matrix2));

        System.out.println(matrixRes);
        System.out.println();
        System.out.println(matrixRes1);
        assertTrue(matrixRes.equals(matrixRes1));
    }

    @Test
    void matrixMultiplyPerformance(){
        final int start = 1000;
        final int end = 100000;
        for(int i = start; i < end; i = i + 1000){
            final DLMatrix matrix1 = DLMatrixUtils.rand(i, i);
            final DLMatrix matrix2 = DLMatrixUtils.rand(i, i);
            final StopWatch sw = new StopWatch();
            final DLMatrix matrixRes = DLMatrixUtils.matrixMultiply(matrix1, matrix2);
            System.out.println(sw.elapsedTime());
            //System.out.println(matrixRes);

            final StopWatch sw1 = new StopWatch();
            final DLMatrix matrixRes1 = DLMatrixUtils.mul(matrix1, matrix2);
            System.out.println(sw1.elapsedTime());
            //System.out.println(matrixRes1);

        }

/*
        assertTrue(matrixRes.rows() == matrixRes1.rows());
        assertTrue(matrixRes.columns() == matrixRes1.columns());
*/
    }

}