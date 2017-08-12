/*********************************************************************************
* (Identical arrays) The two-dimensional arrays m1 and m2 are identical if they  *
* have the same contents. Write a method that returns true if m1 and m2 are      *
* identical, using the following header:                                         *
*                                                                                *
* public static boolean equals(int[][] m1, int[][] m2)                           *
*                                                                                *
* Write a test program that prompts the user to enter two 3 * 3 arrays of        *
* integers and displays whether the two are identical.                           *
*********************************************************************************/
import java.util.Scanner;

public class Exercise_08_29 {
	/** Main method */
	public static void main(String[] args) {
		// Prompt the user to enter two 3 x 3 arrays
		System.out.print("Enter list1: ");
		int[][] list1 = getArray();
		System.out.print("Enter list2: ");
		int[][] list2 = getArray();

		// Displays whether the two lists are identical
		System.out.println("The two arrays are" + 
			(equals(list1, list2) ? " " : " not ") + "identical");
	}

    /** (Identical arrays) The two-dimensional arrays m1 and m2 are identical
     * if they have the same contents. */
    public static boolean equals(int[][] m1, int[][] m2) {
        if (!(m1.length == m2.length))
            return false;

        //take a value from m1
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {

                //compare it to every value from m2
                int occurred = 0;
                for (int k = 0; k < m2.length; k++) {
                    for (int l = 0; l < m2[k].length; l++) {

                        if (m1[i][j] == m2[k][l])
                            occurred ++;

                    }
                }
                //if there is no such value in m2 - return false
                if (occurred == 0)
                    return false;

            }
        }

        return true;
    }
}
