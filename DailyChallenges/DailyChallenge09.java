//
// Daily Challenge 9 - Palindromes
// For PGR103 Object-oriented Programming
// Kristiania University College
//
// Challenge text:
//     Write a Java program to check if a positive number is a palindrome or not.
//     Palindrome number remain the same either written forward or backwards.
//     Examples: 11, 151, 1125211, etc.
//     If you feel for some more challenge you can do the same for strings.
//     Example for string palindromes: Anna, tattarrattat, Rotator, etc.
//
// Challenge example:
//     <No examples>
//
// Challenge hints:
//     https://www.tutorialspoint.com/java/java_string_charat.htm should be helpful for the string
//     for the int modulo can be a possible solution
//
// Solution by: Cytlan
//

import java.util.Scanner;

public class DailyChallenge09
{
	public static boolean checkPalindrome(int num)
	{
		// Count the length of the number
		int orgNum = num;
		int reversedNum = 0;
		while(num > 0)
		{
			// Get the lower digit of the number, and add it to the reversed number
			reversedNum = (reversedNum * 10) + (num % 10);
			// Divide by 10 to get rid of the least significant digit
			num /= 10;
		}

		// Compare the original and the reversed number, and return the result
		return orgNum == reversedNum;
	}

	public static boolean checkPalindrome(String str)
	{
		// Convert to lowercase, so that we don't have to care about uppercase
		str = str.toLowerCase();

		// Get the length of the string
		int strLen = str.length();

		// We only need to loop through half the string, because we're checking it at both ends
		int halfSize = strLen / 2;

		// Create two counters, one from the start, and one from the end
		int frontIndex = 0;
		int backIndex = strLen - 1; // -1, because we start counting at 0

		// Loop through half the string and check both sides if they're equal
		for(int i = 0; i < halfSize; i++)
		{
			// If the character at the opposide sides of the string are not equal, end the search
			if(str.charAt(frontIndex) != str.charAt(backIndex))
				return false;

			// Move the indicies closer to the middle
			frontIndex++;
			backIndex--;
		}

		// Both sides were equal (otherwise we'd have returned false in the loop)
		// We can safely return true.
		return true;
	}

	public static void main(String[] args)
	{
		System.out.println("Daily Challenge 9 - Palindromes");
		System.out.println("===============================");

		// Scanner object
		Scanner scan = new Scanner(System.in);

		// Ask the user if they want to check an int palindrome, or a string palindrome
		System.out.print("Do you want to check an int (1) or a string (2): ");
		String choice = scan.nextLine();

		// Define a variable outside the if's, so that we can access it at the end
		boolean isPalindrome;

		if(choice.equals("1"))
		{
			System.out.print("Write an integer you think is a palindrome: ");
			int maybePalindrome = scan.nextInt();
			isPalindrome = checkPalindrome(maybePalindrome);

		}
		else if(choice.equals("2"))
		{
			System.out.print("Write a string you think is a palindrome: ");
			String maybePalindrome = scan.nextLine();
			isPalindrome = checkPalindrome(maybePalindrome);
		}
		else
		{
			System.out.println("Please answer 1 for int or 2 for string.");
			return;
		}

		// Let the user know the result
		if(isPalindrome)
			System.out.println("It is a palindrome!");
		else
			System.out.println("It is not a palindrome.");
	}
}
