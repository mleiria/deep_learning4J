package pt.deeplearning.algebra;

import org.junit.jupiter.api.Test;
import pt.mleiria.core.StopWatch;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DLMatrixPerformanceTest {
    private final int start = 500;
    private final int end = 1000;

    private final int inc = 200;

    //@Test
    void matrixMultiplyPerformance() {
        final List<Result> results = new ArrayList<>();
        squareMatrix(results);
        rectagularMatrix(results);
        results.forEach(System.out::println);
    }
    private void squareMatrix(final List<Result> results){
        for (int i = start; i < end; i = i + inc) {
            final DLMatrix matrix1 = DLMatrixUtils.rand(i, i);
            final DLMatrix matrix2 = DLMatrixUtils.rand(i, i);

            final String matrixSize = "Matrix size: " + matrix1.shape()[0] + ";" + matrix1.shape()[1];
            System.out.println(matrixSize);

            final StopWatch sw1 = new StopWatch();
            final DLMatrix matrixRes1 = DLMatrixUtils.mulParallel(matrix1, matrix2);
            final String sw1Time = sw1.elapsedTime();
            System.out.println("Matrix multiplication Par:  " + sw1Time);

            final StopWatch sw2 = new StopWatch();
            final DLMatrix matrixRes2 = DLMatrixUtils.mulSeq(matrix1, matrix2);
            final String sw2Time = sw2.elapsedTime();
            System.out.println("Matrix multiplication Seq:  " + sw2Time);
            results.add(new Result(matrixSize, sw1Time, sw2Time));

            System.out.println("--------------------------------------------------");


            assertEquals(matrixRes1.component(10,10), matrixRes2.component(10,10));
        }
    }

    private void rectagularMatrix(final List<Result> results){
        for (int i = start; i < end; i = i + inc) {
            final DLMatrix matrix1 = DLMatrixUtils.rand(i, i*10);
            final DLMatrix matrix2 = DLMatrixUtils.rand(i*10, i);

            final String matrixSize = "Matrix size: " + matrix1.shape()[0] + ";" + matrix1.shape()[1];
            System.out.println(matrixSize);

            final StopWatch sw1 = new StopWatch();
            final DLMatrix matrixRes1 = DLMatrixUtils.mulParallel(matrix1, matrix2);
            final String sw1Time = sw1.elapsedTime();
            System.out.println("Matrix multiplication Par:   " + sw1Time);

            final StopWatch sw2 = new StopWatch();
            final DLMatrix matrixRes2 = DLMatrixUtils.mulSeq(matrix1, matrix2);
            final String sw2Time = sw2.elapsedTime();
            System.out.println("Matrix multiplication Seq:  " + sw2Time);
            results.add(new Result(matrixSize, sw1Time, sw2Time));

            System.out.println("--------------------------------------------------");

            assertEquals(matrixRes1.component(10,10), matrixRes2.component(10,10));
        }
    }

    private record Result (String matrixSize, String par, String seq){}


}
