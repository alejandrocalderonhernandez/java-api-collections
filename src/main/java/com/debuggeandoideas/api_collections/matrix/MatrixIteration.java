package com.debuggeandoideas.api_collections.matrix;

public class MatrixIteration {


    public static void main(String[] args) {
        int[][] matrix = new int[3][3];
    /*
        //Filas
        for (int row = 0; row < 3; row++) {
            //Columnas
            for (int col = 0; col < 3; col++) {
                matrix[row][col] = 0;
            }
        }


        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
       */

        int length = matrix.length;

        for (int i = 0; i < length * length; i++) {
            int row = i / length;
            int col = i % length;

            matrix[row][col] = 2;
        }


        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }




    }
}
