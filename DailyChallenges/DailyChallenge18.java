
// Represents a cell
class Cell
{
	private int value = 0;
	private boolean fixed = false;

	public Cell(int value)
	{
		this.value = value;
		if(value > 0)
			fixed = true;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}

	public boolean isFixed()
	{
		return fixed;
	}
}

// Represents a board
class Board
{
	private boolean valid = false;
	private Cell[] board;

	public Board(int ... values)
	{
		// Must have 81 values
		if(values.length != 81)
			return;

		board = new Cell[81];

		for(int i = 0; i < 81; i++)
		{
			// All values must be between 0 and 9
			if(values[i] < 0 || values[i] > 9)
				return;
			board[i] = new Cell(values[i]);
		}
	}

	// Check if a value is found in a row
	boolean checkRow(int index, int value)
	{
		// Check all columns
		int row = (index / 9) * 9;
		for(int i = 0; i < 9; i++)
		{
			if(board[row + i].getValue() == value)
				return false;
		}
		return true;
	}

	// Check if a value is found in a column
	boolean checkCol(int index, int value)
	{
		// Check all columns
		int column = index % 9;
		for(int i = 0; i < 9; i++)
		{
			if(board[column + (i * 9)].getValue() == value)
				return false;
		}
		return true;
	}

	boolean checkBoundingBox(int index, int value)
	{
		// Get the row and column
		int row = index / 9;
		int column = index % 9;

		// Find out what index the 3x3 box starts at
		int x3 = (column / 3) * 3;
		int y3 = (row / 3) * 9 * 3;

		// Use that index as our search index
		int bbIndex = x3 + y3;

		// Loop through all cells in this 3x3
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				// If any cell contains the same value as we're attempting to use, it's an illegal move
				if(board[bbIndex + j + (i * 9)].getValue() == value)
					return false;
			}
		}
		return true;
	}

	public boolean solve()
	{
		int curIndex = 0;
		while(curIndex < 9 * 9)
		{
			// Fetch the next cell
			Cell cell = board[curIndex];

			// Skip fixed cells - We're not allowed to touch those
			if(cell.isFixed())
			{
				curIndex++;
				continue;
			}

			// Start testing for legal moves from the cells current value.
			// If we backtracked to this cell from an earlier dead end, the cell's value might not be 0!
			int testVal = cell.getValue();

			// Check all numbers to see if there's any legal move we can make
			while(testVal <= 9)
			{
				testVal++;
				if(
					checkRow(curIndex, testVal) &&
					checkCol(curIndex, testVal) &&
					checkBoundingBox(curIndex, testVal)
				)
					break;
			}

			// If the tested value overflows to 10, then we didn't find any legal move and must backtrack
			if(testVal == 10)
			{
				// Reset the current cell back to 0
				cell.setValue(0);

				// Go backwards, but only until we hit the 1st cell
				while(curIndex > 0)
				{
					// Fetch the previous cell
					curIndex--;
					cell = board[curIndex];

					// If it's not a fixed cell and hasn't reached 9 yet, continue trying from this point
				    if(!cell.isFixed()	 && cell.getValue() < 10)
		    		    break;

		    		// If it's not a fixed cell and has reached 9, reset it and continue backwards
		    		else if(!cell.isFixed())
		    			cell.setValue(0);
				}

				// We're at the first cell, and it has reached 9. There is no solution!
				// In this program, this might happen if the board provided is invalid.
				if(curIndex == 0)
				{
					System.out.println("There is no solution.");
					return false;
				}
			}
			else
			{
				// We found a legal move. Stick with it for now and move on to the next cell
				cell.setValue(testVal);
				curIndex++;
			}

			// Print every attempted solution, because it's fun to watch
			System.out.println(this);
		}
		return true;
	}

	public String toString()
	{
		String s = "";
		for(int i = 0; i < 9; i++)
		{
			for(int j = 0; j < 9; j++)
			{
				s += (board[(i * 9) + j].getValue() == 0) ? " " : board[(i * 9) + j].getValue();
				s += "|";
			}
			s += "\n";
		}
		return s;
	}
}

public class DailyChallenge18
{
	public static void main(String[] args)
	{
		// Create a board
		Board board = new Board(
			8, 0, 0,  0, 0, 0,  0, 0, 0,
			0, 0, 3,  6, 0, 0,  0, 0, 0,
			0, 7, 0,  0, 9, 0,  2, 0, 0,

			0, 5, 0,  0, 0, 7,  0, 0, 0,
			0, 0, 0,  0, 4, 5,  7, 0, 0,
			0, 0, 0,  1, 0, 0,  0, 3, 0,

			0, 0, 1,  0, 0, 0,  0, 6, 8,
			0, 0, 8,  5, 0, 0,  0, 1, 0,
			0, 9, 0,  0, 0, 0,  4, 0, 0
		);

		// Solve it!
		board.solve();
	}
}
