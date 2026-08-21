package com.debuggeandoideas.api_collections.matrix;

public final class MatrixOperations {

    // [1, 2, 3]         [1, 4]
    // [4, 5, 6]   ->    [2, 5]
    //                   [3, 6]
    //
    // matriz[row][col] pasa a resultado[col][row]
    public int[][] transpose(int[][] matrix) {
        int rows = matrix.length; // 2
        int cols = matrix[0].length; // 3
        int[][] result = new int[cols][rows];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                result[col][row] = matrix[row][col];
            }
        }
        return result;
    }

    // [1, 2, 3]
    // [4, 5, 6]
    //
    // pares:   2, 4, 6  -> 3
    // impares: 1, 3, 5  -> 3
    //
    // resultado = [3, 3]  →  [0]=pares, [1]=impares
    public int[] countEvenAndOdd(int[][] matrix) {
        int rows = matrix.length; // 2
        int cols = matrix[0].length; // 3
        int even = 0;
        int odd = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (matrix[row][col] % 2 == 0) {
                    even++;
                } else {
                    odd++;
                }
            }
        }

        return new int[]{even, odd};
    }

    // [1, 2, 3] -> 0-0
    // [4, 5, 6] -> 1-1
    // [7, 8, 9] -> 2-2
    //
    // diagonal: matriz[0][0] + matriz[1][1] + matriz[2][2]
    //             1      +      5      +      9     = 15
    public int primaryDiagonalSum(int[][] matrix) {
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }
        return sum;
    }
}