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