//
// Daily Challenge 10 - Image Manipulation
// For PGR103 Object-oriented Programming
// Kristiania University College
//
// Challenge text:
//     The task to solve is to read in a color image and change the pixel values to grey scale like you would apply a grey filter on it
//
// Challenge example:
//     <No examples>
//
// Challenge hints:
//     https://docs.oracle.com/javase/8/docs/api/javax/imageio/package-summary.html
//     https://en.wikipedia.org/wiki/Grayscale
//
// Solution by: Cytlan
//

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class DailyChallenge10
{
	// Apply greyscale filter
	static public void imageGreyscale(BufferedImage image)
	{
		// Get the image width and height
		int width = image.getWidth();
		int height = image.getHeight();

		// Iterate trough the image
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				// Get the current pixel
				int pixel = image.getRGB(x, y);

				// Get the colour channels
				int blue  =  pixel        & 0xFF;
				int green = (pixel >>  8) & 0xFF;
				int red   = (pixel >> 16) & 0xFF;
				int alpha = (pixel >> 24) & 0xFF;

				// Get the average colour
				int grey = (red + green + blue) / 3;

				// Combine the colours into a pixel, using the average colour on all channels
				// Using the same value on all channels is what causes the image to turn greyscale
				int newPixel = grey | (grey << 8) | (grey << 16) | (alpha << 24);

				// Set the new pixel
				image.setRGB(x, y, newPixel);
			}
		}

		// No need to return anything - The image was passed by reference!
	}

	// Note: Image contrast was not part of the challenge. It was an extra challenge I imposed on myself.
	//       You can safely ignore this function, unless you're interested in more advanced image manipulation.
	//       Additionally, I use a lot of bitshifting to access the individual colour channels in this function.
	//       Bitshifting is more efficient, but a lot more difficult to read!
	static public void imageContrast(BufferedImage image, int contrast)
	{
		// Don't manipulate the image if the contrast level is out of bounds
		if(contrast > 255 || contrast < -255)
			return;

		// Calculate the contrast factor
		float factor = ((float)(259 * (contrast + 255))) / (float)((255 * (259 - contrast)));

		// Get the image width and height
		int width = image.getWidth();
		int height = image.getHeight();

		// Iterate trough the image
		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				// Get the current pixel
				int pixel = image.getRGB(x, y);

				// For all colour channels
				for(int i = 0; i < 3; i++)
				{
					// Get colour
					int colour = (pixel >> (i * 8)) & 0xFF;

					// Apply contrast
					colour = (int)(factor * (float)(colour - 128)) + 128;

					// Truncate the colour
					if(colour > 0xFF) colour = 0xFF;
					if(colour < 0) colour = 0;

					// Mask off old colour
					int mask = ~(0xFF << (i * 8));
					pixel &= mask;

					// Add new colour
					pixel |= colour << (i * 8);
				}

				// Set the new pixel
				image.setRGB(x, y, pixel);
			}
		}

		// No need to return anything - The image was passed by reference!
	}

	public static void main(String[] args)
	{
		System.out.println("Daily Challenge 10 - Image Manipulation");
		System.out.println("=======================================");

		// Scanner object
		Scanner scanner = new Scanner(System.in);

		// Ask the user for filenames
		System.out.print("Enter input filename [test.jpeg]: ");
		String inFilename = scanner.nextLine();
		System.out.print("Enter outpout filename [out.jpeg]: ");
		String outFilename = scanner.nextLine();

		// If no filenames were given, use defaults
		if(inFilename.isEmpty())
			inFilename = "test.jpeg";
		if(outFilename.isEmpty())
			outFilename = "out.jpeg";

		BufferedImage image = null;
		File file = null;
		try
		{
			// Read the file
			file = new File(inFilename);
			image = ImageIO.read(file);
		}
		catch(IOException e)
		{
			// Could not read the file. End the program.
			System.out.println(e);
			return;
		}

		// Which operation?
		System.out.print("Which operation should be perform, greyscale(1) or contrast(2): ");
		String operation = scanner.nextLine();

		// Greyscale - The original challenge
		if(operation.equals("1"))
		{
			imageGreyscale(image);
		}
		// Contrast - Extra challenge (You can safely ignore this.)
		else if(operation.equals("2"))
		{
			System.out.print("Enter contrast level (-255 to 255): ");
			String contrastStr = scanner.nextLine();
			int contrastLevel = Integer.parseInt(contrastStr);
			if(contrastLevel > 255 || contrastLevel < -255)
			{
				System.out.println("Contrast level must be between -255 and 255");
				return;
			}
			imageContrast(image, contrastLevel);
		}
		// The user failed to write either 1 or 2...
		else
		{
			System.out.print("Please enter 1 for greyscale, or 2 for contrast.");
			return;
		}

		// Write the manipulated image to disk
		try
		{
			file = new File(outFilename);
			ImageIO.write(image, "jpeg", file);
		}
		catch(IOException e)
		{
			// Failed to write file
			System.out.println(e);
		}
	}
}
