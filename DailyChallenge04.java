//
// Daily Challenge 4 - FizzString
// For PGR103 Object-oriented Programming
// Kristiania University College
//
// Challenge text:
//     Given a string str, if the string starts with "f" return "Fizz".
//     If the string ends with "b" return "Buzz".
//     If both the "f" and "b" conditions are true, return "FizzBuzz".
//     In all other cases, return the string unchanged.
//
// Challenge example:
//     fizzString("fig") → "Fizz"
//     fizzString("dib") → "Buzz"
//     fizzString("fib") → "FizzBuzz"
//
// Challenge hints:
//     The string functions str.startsWith() and str.endWith() can be helpful here
//
// Solution by: Cytlan
//

// We must import the Scanner before we can use it below
import java.util.Scanner;

public class DailyChallenge04
{
	public static String fizzString(String str)
	{
		// If both the "f" and "b" conditions are true, return "FizzBuzz".
		if(str.startsWith("f") && str.endsWith("b"))
			return "FizzBuzz";
		
		// If the string starts with "f" return "Fizz".
		if(str.startsWith("f"))
			return "Fizz";
		
		// If the string ends with "b" return "Buzz".
		if(str.endsWith("b"))
			return "Buzz";

		// In all other cases, return the string unchanged.
		return str;
	}

	public static void main(String[] args)
	{
		System.out.println("Daily Challenge 4 - FizzString");
		System.out.println("==================================");

		// Scanner object
		Scanner scan = new Scanner(System.in);

		// Read input
		System.out.print("Your string: ");
		String input = scan.nextLine();

		// Fizz the string
		String result = fizzString(input);

		// Print the sploded string
		System.out.println("Fizz string: "+result);
	}
}
