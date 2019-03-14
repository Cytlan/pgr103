PGR103 Cheat Sheet
==================

This is a cheat sheet for Java programming. It's in no particular order, but I try to keep the simplest concepts closer to the top.

It's meant to be used as a quick "how did I do that again?" reference.

If there's anything you keep forgetting, or think you will forget, send me a message on Discord, and I will add it to the cheat sheet.

Index
-----

* [Data types](#data-types)
    * [int](#int)
    * [float](#float)
    * [boolean](#boolean)
    * [double](#double)
    * [void](#void)
    * [null](#null)
* [Methods](#methods)
* [If statements](#if-statements)
* [Loops](#loops)
    * [for](#for-loops)
    * [while](#while-loops)
    * [do while](#do-while-loops)
* [Writing text to the output log](#writing-text-to-the-output-log)
* [Reading user input](#reading-user-input)
* [Reading a file](#reading-a-file)
* [Simplest Java program](#simplest-java-program)
* [Recursion](#recursion)
* [Constructor](#constructor)

Data types
----------

### int

An `int` can hold *whole* numbers, including negative numbers. It cannot contain fractions.

```java
int myInt1 = 12345;
int myInt2 = 0;
int myInt3 = -42;
```

### float

`floats` are similar to `int`, but it can contain fractions.

You cannot use bitwise operations on floats. If you do, it will automatically be converted to `int` and lose the fractional.

```java
float myFloat1 = 12345.123;
float myFloat1 = -28.88;
```

### boolean

`boolean` can only contain `true` or `false`.

```java
boolean myBool1 = true;
boolean myBool2 = false;
```

### double

`double` is exactly like `float`, except it can hold bigger numbers.

```java
double myDouble = 9.9999999999999; // This would have too many digits after the decimal to be used in a float
```

### void

`void` means "nothing". You use it as the return data type in methods if you don't want to return anything at all.

Your `public static void main(String[] args)` method uses this, because main doesn't return any value.

### null

`null` also means "nothing", but where `void` is a *type*, `null` is actually a *value*. Any variable that has an object as type are by default `null` until you assign it something else.

Methods
-------

A method contains 5 parts:
* Optional visibility (can be `public` or `private`. If you leave it out, it'll default to `private`).
* Optional modifier (for example `static`)
* Return type (can be any of the data types).
* Method name
* Method body

It looks likes this:
```java
public static int myMethod
{
	return 1;
}
```

Where
* `public` is the visibility
* `static` is the modifier
* `int` is the return type
* `myMethod` is the name
* `{ return 1; }` is the body.

All methods except those with `void` return type must `return something;`

If statements
-------------

Do make your code do different things depending on the situation, you need `if` statements.

If statements run the body if the condition in parenthesis is true.

Example:
```java
int myInt = 11;
if(myInt > 10) // Condition: Is myInt is greater than (but not equal to) 10?
{
	System.out.println("The condition was true!");
}
```

If you want to do something if the condition is false, you need `else`, like so:
```java
int myInt = 5;
if(myInt > 10) // Condition: Is myInt is greater than (but not equal to) 10?
{
	System.out.println("The condition was true!");
}
else
{
	System.out.println("The condition was false!");
}
```

Loops
-----

There are 3 kinds of loops:
* `for` loop
* `while` loop
* `do while` loop

### `for` loops

The `for` loop is the most common type of loop.

`for` loops have 6 parts to them:
* The `for` keyword
* Initialisation - Single line of code to do before the loop starts
* Condition - `if` statement to do every time the loop loops
* Operation - Single line of code to to every time the loop loops
* The loop body - The code to run on every loop

The initialisation, condition and operation are separated by `;`
 
Like this:
```java
// Print all numbers from 1 to 10
for(int i = 1; i <= 10; i++)
{
	System.out.println(i);
}
```

Where:
* `int i = 1` is the initialisation
* `i <= 10` is the condition
* `i++` is the operation
* `{ System.out.println(i); }` is the body

### `while` loops

`while` loops are just like `for` loops, except it doesn't have the initialisation or operation.

Like this:
```java
// Print all numbers from 1 to 10
int i = 1; // Initialised OUTISDE the loop!
while(i <= 10)
{
	System.out.println(i);
	i++; // Incrementing i must be done manually
}
```

### `do while` loops

`do while` loops are exactly like `while` loops, except the `while` is placed at the end of the loop. This means a `do while` loop will always run *at least* one time. 

`do while` loops are not used very often.

```java
// Print all numbers from 1 to 10
int i = 1;
do
{
	System.out.println(i);
	i++;
}
while(i <= 10); // Notice how the condition of the loop is only checked AFTER the loop body has been run
```

Writing text to the output log
------------------------------

Use `System.out.println()` to print a whole line.
Use `System.out.print()` to print only parts of a line.

Example:

```java

class MyClass
{
	public static void main(String[] args)
	{
		System.out.println("My line!");

		System.out.print("Some text");
		System.out.println(", but notice how this all appeared on the same line!");

		// You can print empty lines:
		System.out.println();

		System.out.println("You can print numbers:");
		System.out.println(10);

		// Alternate way of printing an empty line:
		System.out.print("\n");

		String someString = "Hello!";
		System.out.println("You can also print variables:");
		System.out.println(someString);
	}
}
```

What you should see in the output:
```
My line!
Some text, but notice how this all appeared on the same line!

You can print numbers:
10

You can also print variables:
Hello!
```

Reading user input
------------------

```java

import java.util.Scanner; // Remember to add this line!

class MyClass
{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner();

		// Reading a string:
		String strInput = scanner.nextLine();

		// Reading an int:
		int intInput1 = scanner.nextInt();
		int intInput2 = Integer.parseInt(scanner.nextLine()); // Alternative style

		// Reading a float:
		float floatInput1 = scanner.nextFloat();
		float floatInput2 = Float.parseFloat(scanner.nextLine()); // Alternative style
	}
}
```

Reading a file
--------------

Todo.

Simplest Java program
---------------------

All Java programs must have a `public static void main` method (can be in any class).

```java
class MyClass
{
	public static void main(String[] args)
	{
		// Your code here
	}
}
```

Recursion
---------

Recursion is when a method calls itself.

Example:
```java
public int countTo10(int currentValue)
{
	// Stop the recursion when we reach 10
	if(currentValue >= 10)
	{
		// Return WITHOUT calling ourselves to stop the recursion
		return currentValue;
	}

	// Recursion happens here: We call ourselfes to get the next value
	return countTo10(currentValue + 1);
}
```

Most recursive method can be rewritten as loops. In the example above:

```java
int currentValue = 0;
while(currentValue >= 10)
{
	currentValue = currentValue + 1;
}
```

Constructor
-----------

To initialize variables in an object as soon as the object is created, you need a constructor.

A constructor is just like any other method, but it must have *the same name as the class*, and it cannot have a return type.

Example:

```java
class MyClass
{
	int myInt;

	// Constructor
	public MyClass(int yourInt)
	{
		myInt = yourInt;
	}
}
```
