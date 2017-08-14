package chapter08;

import utility.ArrayKeeper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class LargestBlock {
    public static void main(String[] args) throws IOException {
        InputStream is = new FileInputStream(new File("src/chapter08/block.txt"));
        Scanner scanner = new Scanner(is);

        //Enter the number of rows in the matrix: 5
        //Enter the matrix row by row:
        // 1 0 1 0 1
        // 1 1 1 0 1
        // 1 0 1 1 1
        // 1 0 1 1 1
        // 1 0 1 1 1
        //The maximum square submatrix is at (2, 2) with size 3
        int[][] matrix = ArrayKeeper.readIntMatrix(scanner);
        int[] block = findLargestBlock(matrix);
        if (matrix.length != 0)
            System.out.printf("The maximum square submatrix is at (%d, %d) with size %d",
                block[0], block[1], block[2]);

        scanner.close();
        is.close();
    }

    public static int[] findLargestBlock(int[][] m) {
        if (m.length == 0)
            return null;

        int x = 0, y = 0, size = 0, posX = 0, posY = 0, posSize = 0;

        //search for a 1
        for (int row = 0; row < m.length; row++) {
            for (int cell = 0; cell < m[row].length; cell++) {
                if (m[row][cell] == 1) {
                    // assume it is a beginning of a largest block
                    posX = cell;
                    posY = row;
                    posSize = 1;

                    //count 1s that surround that block
                    // x 1 0
                    // 1 1 0
                    // 0 0 0

                    boolean foundAllOnes;
                    do {
                        int counter = 0;
                        foundAllOnes = false; // assume we fail to find all 1s

                        //check all the rows till [cell + size] in column [cell + possible size + 1]
                        if ((row + posSize) < m.length && cell + 1 < m[row].length) {
                            for (int i = row; i < row + posSize; i++) {
                                if (m[i][cell + 1] == 1)
                                    counter++;
                            }
                        }

                        //check all the columns(cells) from [cell + size] till back to the starting cell
                        // (on the row beneath block)
                        if ((cell + posSize) < m[row].length && row + posSize < m.length) {
                            for (int i = cell + posSize; i >= cell; i--) {// the 1 in the angle counts here
                                if (m[row + posSize][i] == 1)
                                    counter++;
                            }
                        }

                        //if counter equals possible size * 2 + 1  -- increase possible size
                        // (which means each surrounding digit indeed equals 1
                        if (counter == posSize * 2 + 1) {
                            posSize++;
                            foundAllOnes = true;

                            //if it's indeed the largest block so far - show it
                            if (posSize > size) {
                                size = posSize;
                                x = posX;
                                y = posY;
                            }
                        }

                    } while (foundAllOnes);
                } else {
                    //in case m[row][cell] == 0 we should begin next iteration with nearest digit
                    posSize = 0;
                }
                //begin next iteration after the possible largest block
                cell += posSize;
            }
        }

        return new int[]{x, y, size};
    }
}
