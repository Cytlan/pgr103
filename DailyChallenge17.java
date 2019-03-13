//
// Daily Challenge 17 - Timelords
// For PGR103 Object-oriented Programming
// Kristiania University College
//
// Challenge text:
//     The task for the challenge is to get familiar with the Java Time (https://docs.oracle.com/javase/8/docs/api/java/time/package-summary.html)
//
//     The specific task it to create an age calculator.
//     The age calculator can calculate your age in years given your birthdate.
//     In addition to that, the age calculator can also calculate the difference between peoples ages and return who is the oldest person.
//     For all tasks use Java Time! You will mainly need LocalDate and Period fro this task. 
//
// Challenge example:
//     https://gist.github.com/kelkalot/3821910591859e356ed702e6e0668432 
//
// Challenge hints:
//     <No hints>
//
// Solution by: Cytlan
//

import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DailyChallenge17
{
	public static void main(String[] args)
	{
		System.out.println("Daily Challenge 17 - Timelords");
		System.out.println("==============================");

		Scanner scan = new Scanner(System.in);

		System.out.print("Would you like to calculate your age (1), or calculate age difference (2)? ");
		String command = scan.nextLine();

		if(command.equals("1"))
		{
			// Ask the user for their birthdate
			System.out.print("Write your birthday (yyyy-mm-dd): ");
			String birthdateStr = scan.nextLine();
			LocalDate birthdate = LocalDate.parse(birthdateStr, DateTimeFormatter.ISO_LOCAL_DATE);
			
			// Get the current date
			LocalDate currentDate = LocalDate.now();

			// Calculate the period between the two dates
			Period period = Period.between(birthdate, currentDate);
			Period monthsPeriod = period.minusYears(period.getYears());
			Period daysPeriod = monthsPeriod.minusMonths(period.getMonths());

			// Get the years, months and days of the period
			int years = period.getYears();
			int months = monthsPeriod.getMonths();
			int days = daysPeriod.getDays();

			System.out.println(
				"You are "+
				years+" year"+(years == 1 ? "" : "s")+", "+
				months+" month"+(months == 1 ? "" : "s")+" and "+
				days+" day"+(days == 1 ? "" : "s")+" old"
			);
		}
		else if(command.equals("2"))
		{
			// Ask the user for the first birthdate
			System.out.print("Write person 1's birthday (yyyy-mm-dd): ");
			String birthdateStr1 = scan.nextLine();
			LocalDate birthdate1 = LocalDate.parse(birthdateStr1, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

			// Ask the user for the second birthdate
			System.out.print("Write person 2's birthday (yyyy-mm-dd): ");
			String birthdateStr2 = scan.nextLine();
			LocalDate birthdate2 = LocalDate.parse(birthdateStr2, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			// Calculate the number of years between the two dates
			Period period = Period.between(birthdate1, birthdate2);

			int daysInPeriod = period.getDays();

			// Positive days = Person 1 is older
			if(daysInPeriod > 0)
			{
				System.out.println("Person 1 is "+daysInPeriod+" days older than person 2");
			}
			// Negative days = Person 2 is older
			else if(daysInPeriod < 0)
			{
				System.out.println("Person 2 is "+Math.abs(daysInPeriod)+" days older than person 1");
			}
			// 0 days = same age
			else
			{
				System.out.println("Person 1 and person 2 are the same age");
			}
		}
		else
		{
			System.out.println("Please write 1 or 2");
		}
	}
}
