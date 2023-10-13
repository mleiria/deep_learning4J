package pt.deeplearning.stats;

import org.junit.jupiter.api.Test;
import pt.deeplearning.algebra.DLVector;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatsTest {

    @Test
    void minMax() {
        final double[] arr = new double[]{-21.0, 2.0, -23.0, 30.0, 4.0, 5.0};
        final DLVector dlVector = new DLVector(arr);
        final Stats stats = new Stats(dlVector);

        assertEquals(-23.0, stats.getMin());
        assertEquals(30.0, stats.getMax());
    }

    @Test
    void sum() {
        final double[] arr = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
        final DLVector dlVector = new DLVector(arr);
        final Stats stats = new Stats(dlVector);
        assertEquals(15.0, stats.getSum());
        assertEquals(55.0, stats.getSumSqr());
    }

    @Test
    void sumSqr() {
        final double[] arr = new double[]{1.0, 2.0, 3.0};
        final DLVector dlVector = new DLVector(arr);
        final Stats stats = new Stats(dlVector);
        assertEquals(6.0, stats.getSum());
        assertEquals(14.0, stats.getSumSqr());
    }

}