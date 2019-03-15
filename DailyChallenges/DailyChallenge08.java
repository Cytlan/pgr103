//
// Daily Challenge 8 - Jinxed sum
// For PGR103 Object-oriented Programming
// Kristiania University College
//
// Challenge text:
//     Given three int values, a, b, c, return their sum.
//     However, if one of the values is 4 then it does not count towards the sum and values to its right do not count.
//     Thus, if b is 4, then both b and c do not count.
//
// Challenge example:
//     jinxedSum(1, 2, 3) → 6
//     jinxedSum(1, 2, 4) → 3
//     jinxedSum(1, 4, 3) → 1
//
// Challenge hints:
//     <No hints>
//
// Solution by: Cytlan
//

import java.util.Scanner;

public class DailyChallenge08
{
	public static int jinxedSum(int a, int b, int c)
	{
		// If a is 4, then don't calculate anything
		if(a == 4)
			return 0;

		// If a is not 4, but b is 4, then 0 + a = a. Return a.
		if(b == 4)
			return a;

		// If a and b is not 4, but c is, then return a + c.
		if(c == 4)
			return a + b;

		// If none of the if's above were true, return a + b + c.
		return a + b + c;	
	}

	public static void main(String[] args)
	{
		System.out.println("Daily Challenge 8 - Jinxed sum");
		System.out.println("==============================");

		// Scanner object
		Scanner scan = new Scanner(System.in);

		// Get the required integers from the user
		System.out.print("1st integer: ");
		int firstInt = scan.nextInt();
		System.out.print("2nd integer: ");
		int secondInt = scan.nextInt();
		System.out.print("3rd integer: ");
		int thirdInt = scan.nextInt();

		// Print the result
		System.out.println("Jinxed sum: "+jinxedSum(firstInt, secondInt, thirdInt));
	}
}
