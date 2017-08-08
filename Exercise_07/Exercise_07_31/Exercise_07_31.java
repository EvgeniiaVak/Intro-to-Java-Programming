/*********************************************************************************
* (Merge two sorted lists) Write the following method that merges two sorted     *
* lists into a new sorted list.                                                  *
*                                                                                *
* public static int[] merge(int[] list1, int[] list2)                            *
*                                                                                *
* Implement the method in a way that takes at most list1.length + list2.         *
* length comparisons. Write a test program that prompts the user to enter two    *
* sorted lists and displays the merged list. Here is a sample run. Note that the *
* first number in the input indicates the number of the elements in the list.    *
* This numberis not part of the list.                                            *
*********************************************************************************/
import java.util.Scanner;

public class Exercise_07_31 {
	/** Main method */
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		// Prompt the user to enter two lists
		System.out.print("Enter list1: ");
		int[] list1 = new int[input.nextInt()];
		for (int i = 0; i < list1.length; i++) {
			list1[i] = input.nextInt();
		}


		System.out.print("Enter list2: ");
		int[] list2 = new int[input.nextInt()];
		for (int i = 0; i < list2.length; i++) {
			list2[i] = input.nextInt();
		}

		// Merge lists
		int[] list3 = merge(list1, list2);

		// Display the merged list
		System.out.print("The merged list is");
		for (int e: list3) {
			System.out.print(" " + e);
		}
		System.out.println();
	}

	/** merge merges two sorted lists into a new sorted list.*/
	public static int[] merge(int[] list1, int[] list2) {
		int[] newList = new int[list1.length + list2.length];

		int count1 = 0;
		int count2 = 0;
		int countNew = 0;

		int sumCompareTo = 0;
		for (int i = 0; i < newList.length; i++) {
		    int a = list1[count1];
		    int b = list2[count2];

		    if (a < b) {
			newList[i] = a;
			count1++;
			sumCompareTo++;
		    } else {
			newList[i] = b;
			count2++;
			sumCompareTo++;
		    }

		    countNew++;
		    if (count1 == list1.length || count2 == list2.length)
			break;
		}

		if (count1 == list1.length) {
		    for (int i = countNew; i < newList.length; i++) {
			newList[i] = list2[count2];
			count2++;
		    }
		} else if (count2 == list2.length) {
		    for (int i = countNew; i < newList.length; i++) {
			newList[i] = list1[count1];
			count1++;
		    }
		}

		System.out.printf("Compare to was %d times%n", sumCompareTo);
		return newList;
    }
}
