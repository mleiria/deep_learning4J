package pt.deeplearning.algebra;

import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class DLMatrixTest {
    private static final Logger LOG = Logger.getLogger(DLMatrix.class.getName());

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
        DLMatrix b = a.transpose();
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
        LOG.info("Matrix tocheck: \n" + c.toString());
        assertTrue(b.equals(c));
    }

    @Test
    void testEquals() {
    }

    @Test
    void add() {
    }

    @Test
    void subtract() {
    }

    @Test
    void multiply() {
    }

    @Test
    void testMultiply() {
    }
}