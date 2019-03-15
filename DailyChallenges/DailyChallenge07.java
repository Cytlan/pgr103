//
// Daily Challenge 7 - Red or Blue
// For PGR103 Object-oriented Programming
// Kristiania University College
//
// Challenge text:
//     Given a string, if the string begins with "red" or "blue" return that color string, otherwise return the empty string.
//
// Challenge example:
//     "redxx" → "red"
//     "xxred" → ""
//     "blueTimes" → "blue"
//
// Challenge hints:
//     <No hints>
//
// Solution by: Cytlan
//

import java.util.Scanner;
import java.util.Random;

public class DailyChallenge07
{
	public static String redBlue(String str)
	{
		// Check for red
		if(str.startsWith("red"))
			return "red";

		// Check for blue
		if(str.startsWith("blue"))
			return "blue";

		// No colour found, return nothing
		return "";
	}

	public static void main(String[] args)
	{
		System.out.println("Daily Challenge 7 - Red or Blue");
		System.out.println("===============================");

		// Scanner object
		Scanner scan = new Scanner(System.in);

		// Get a string from the user
		System.out.print("Write a string: ");
		String input = scan.nextLine();

		System.out.println("Your colour: "+redBlue(input));
	}
}
