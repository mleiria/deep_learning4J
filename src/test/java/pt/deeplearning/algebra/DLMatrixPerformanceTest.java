package pt.deeplearning.algebra;

import org.junit.jupiter.api.Test;
import pt.mleiria.core.StopWatch;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DLMatrixPerformanceTest {

    @Test
    void matrixMultiplyPerformance() {
        final List<Result> results = new ArrayList<>();
        squareMatrix(results);
        rectagularMatrix(results);
        System.out.println(results);
    }
    private void squareMatrix(final List<Result> results){
        final int start = 100;
        final int end = 3000;
        for (int i = start; i < end; i = i + 200) {
            final DLMatrix matrix1 = DLMatrixUtils.rand(i, i);
            final DLMatrix matrix2 = DLMatrixUtils.rand(i, i);

            final String matrixSize = "Matrix size: " + matrix1.shape()[0] + ";" + matrix1.shape()[1];
            System.out.println(matrixSize);

            final StopWatch sw1 = new StopWatch();
            final DLMatrix matrixRes1 = DLMatrixUtils.mulFP(matrix1, matrix2);
            final String sw1Time = sw1.elapsedTime();
            System.out.println("Matrix multiplication FP:   " + sw1Time);

            final StopWatch sw2 = new StopWatch();
            final DLMatrix matrixRes2 = DLMatrixUtils.mul(matrix1, matrix2);
            final String sw2Time = sw2.elapsedTime();
            System.out.println("Matrix multiplication Imp:  " + sw2Time);
            results.add(new Result(matrixSize, sw1Time, sw2Time));

            System.out.println("--------------------------------------------------");


            assertEquals(matrixRes1.component(10,10), matrixRes2.component(10,10));
        }
    }

    private void rectagularMatrix(final List<Result> results){
        final int start = 100;
        final int end = 3000;
        for (int i = start; i < end; i = i + 200) {
            final DLMatrix matrix1 = DLMatrixUtils.rand(i, i*10);
            final DLMatrix matrix2 = DLMatrixUtils.rand(i*10, i);

            final String matrixSize = "Matrix size: " + matrix1.shape()[0] + ";" + matrix1.shape()[1];
            System.out.println(matrixSize);

            final StopWatch sw1 = new StopWatch();
            final DLMatrix matrixRes1 = DLMatrixUtils.mulFP(matrix1, matrix2);
            final String sw1Time = sw1.elapsedTime();
            System.out.println("Matrix multiplication FP:   " + sw1Time);

            final StopWatch sw2 = new StopWatch();
            final DLMatrix matrixRes2 = DLMatrixUtils.mul(matrix1, matrix2);
            final String sw2Time = sw2.elapsedTime();
            System.out.println("Matrix multiplication Imp:  " + sw2Time);
            results.add(new Result(matrixSize, sw1Time, sw2Time));

            System.out.println("--------------------------------------------------");

            assertEquals(matrixRes1.component(10,10), matrixRes2.component(10,10));
        }
    }

    private record Result (String matrixSize, String fp, String imp){}


}
