
public class Location 
{
	private int rowP;
	private int columnP;
	// Constructs a new Location object with the specified row and column
	public Location(int rowP, int columnP)
	{
		//throw new UnsupportedOperationException();
		this.rowP=rowP;
		this.columnP=columnP;
	}

	// Returns the row integer that was passed to the constructor
	public int getRow()
	{
		//throw new UnsupportedOperationException();
		return this.rowP;
	}

	// Returns the column integer that was passed to the constructor
	public int getColumn()
	{
		//throw new UnsupportedOperationException();
		return this.columnP;
	}
}

