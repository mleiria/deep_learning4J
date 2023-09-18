package pt.deeplearning.algebra;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class DLMatrixUtilsTest {
    private static final Logger LOG = Logger.getLogger(DLMatrixUtilsTest.class.getName());

    //private DLMatrix matrixA;


    public DLMatrix getMatrix(){
        final double[][] componentsA = new double[2][2];
        // linha 0, coluna 0
        componentsA[0][0] = 1.0;
        // linha 0, coluna 1
        componentsA[0][1] = 2.0;
        // linha 1, coluna 0
        componentsA[1][0] = 3.0;
        // linha 1, coluna 1
        componentsA[1][1] = 4.0;
        return new DLMatrix(componentsA);
    }




    @Test
    void add() {
        final DLMatrix matrixB = DLMatrixUtils.add(getMatrix(), 2.0);
        LOG.info("Matrix B: \n" + matrixB);
        assertEquals(3.0, matrixB.component(0, 0));
        assertEquals(4.0, matrixB.component(0, 1));
        assertEquals(5.0, matrixB.component(1, 0));
        assertEquals(6.0, matrixB.component(1, 1));
    }

    @Test
    void multiply() {
        final DLMatrix matrixB = DLMatrixUtils.multiply(getMatrix(), 2.0);
        LOG.info("Matrix B: \n" + matrixB);
        assertEquals(2.0, matrixB.component(0, 0));
        assertEquals(4.0, matrixB.component(0, 1));
        assertEquals(6.0, matrixB.component(1, 0));
        assertEquals(8.0, matrixB.component(1, 1));
    }

    @Test
    void identity() {
        final DLMatrix matrixB = DLMatrixUtils.identity(2, 2);
        LOG.info("Matrix B: \n" + matrixB);
        assertEquals(1.0, matrixB.component(0, 0));
        assertEquals(0.0, matrixB.component(0, 1));
        assertEquals(0.0, matrixB.component(1, 0));
        assertEquals(1.0, matrixB.component(1, 1));
    }
}