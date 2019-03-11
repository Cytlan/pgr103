//
// Daily Challenge 5 - Pig ears
// For PGR103 Object-oriented Programming
// Kristiania University College
//
// Challenge text:
//     We have a number of pigs and each pig has two ears.
//     We want to compute the total number of ears across all the pigs recursively (without loops or multiplication).
//
// Challenge example:
//     pigEars(0) → 0
//     pigEars(1) → 2
//     pigEars(2) → 4
//
// Challenge hints:
//     https://www.geeksforgeeks.org/recursion/
//
// Solution by: Cytlan
//

import java.util.Scanner;

public class DailyChallenge05
{
	public static int pigEars(int pigs)
	{
		// If there are no more pigs to count, end the recursion by not calling pigEars again
		if(pigs == 0)
			return 0;

		// Count the next pig's ears, and return the result of that + the 2 ears of the current pig
		return pigEars(pigs - 1) + 2;
	}

	public static void main(String[] args)
	{
		System.out.println("Daily Challenge 5 - Pig ears");
		System.out.println("============================");

		// Scanner object
		Scanner scan = new Scanner(System.in);

		// Ask the user how many pigs they have
		System.out.print("How many pigs: ");
		int pigCount = scan.nextInt();

		// Count the ears
		int earCount = pigEars(pigCount);

		// Print the result
		System.out.println("These pigs have "+earCount+" ears");
	}
}
