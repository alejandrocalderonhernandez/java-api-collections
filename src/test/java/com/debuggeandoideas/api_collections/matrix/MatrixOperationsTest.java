package com.debuggeandoideas.api_collections.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixOperationsTest {

    private final MatrixOperations operations = new MatrixOperations();

    @Test
    void transposeSwapsRowsAndColumns() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] result = operations.transpose(matrix);

        int[][] expected = {
                {1, 4},
                {2, 5},
                {3, 6}
        };
        assertArrayEquals(expected, result);
    }

    @Test
    void countEvenAndOddCountsCorrectly() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[] result = operations.countEvenAndOdd(matrix);

        assertEquals(3, result[0]); // pares: 2, 4, 6
        assertEquals(3, result[1]); // impares: 1, 3, 5
    }

    @Test
    void primaryDiagonalSumAddsTopLeftToBottomRight() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int result = operations.primaryDiagonalSum(matrix);

        assertEquals(15, result); // 1 + 5 + 9
    }
}