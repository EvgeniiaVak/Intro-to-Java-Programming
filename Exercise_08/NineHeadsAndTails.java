package chapter08;

import java.util.Scanner;

public class NineHeadsAndTails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;

        //prompt the user to enter a number between 0 and 511
        while (true){
            System.out.print("enter a number between 0 and 511: ");
            number = scanner.nextInt();

            if (number >= 0 && number <= 511)
                break;
            else
                System.out.println("invalid input");
        }

        //translate into binary
        int[] binary = new int[9];
        for (int i = binary.length - 1; i >= 0; i--) {
            binary[i] = number % 2;
            number /= 2;
        }

        char[][] matrix = new char[3][3];
        //display the corresponding matrix
        int index = 0;
        for (int row = 0; row < 3; row++) {
            for (int cell = 0; cell < 3; cell++) {
                char side = (binary[index] == 0) ? 'H' : 'T';
                index++;
                System.out.print(side);
            }
            System.out.println();
        }

    }
}
