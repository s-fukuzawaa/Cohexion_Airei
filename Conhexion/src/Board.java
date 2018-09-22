import java.util.Arrays;

public class Board
{
	public static final int PLAYER_NONE = 0;
	public static final int PLAYER_1 = 1;
	public static final int PLAYER_2 = 2;
		
	private int rows;
	private int columns;
	//2 dimentional array that represents board
	private int[][] remember;
	//array that includes the 4 sites
	private WeightedQuickUnionUFCloneable w;
	private int[] unite;
	private WeightedQuickUnionUFCloneable united;


	// Constructs a new board with the specified number of rows and columns
	public Board(int rows, int columns)
	{
		//throw new UnsupportedOperationException();
		this.rows=rows;
		this.columns=columns;
		this.remember= new int[this.rows][this.columns];
		this.w= new WeightedQuickUnionUFCloneable(remember.length);
		this.unite= new int[remember.length+4];
		for(int i=0; i<remember.length; i++)
		{
			unite[i]=i+1;
		}
		this.united= new WeightedQuickUnionUFCloneable(unite.length);
		
	}

	// Constructs a new Board that clones the state of the specified Board
	public Board(Board original)
	{
		throw new UnsupportedOperationException();
	}

	// Returns the total number of rows in this Board
	public int getRows()
	{
		//throw new UnsupportedOperationException();
		return this.rows;
	}

	// Returns the total number of columns in this Board
	public int getColumns()
	{
		//throw new UnsupportedOperationException();
		return this.columns;
	}

	// Returns one of the three "player" ints defined on this class
	// representing which player, if any, occupies the specified
	// location on the board
	public int getPlayer(Location location)
	{
		//throw new UnsupportedOperationException();
		return this.remember[location.getRow()][location.getColumn()];
	}

	// Places a game piece from the specified player (represented by
	// one of the three "player" ints defined on this class) into the
	// specified location on the board
	public void setPlayer(Location location, int player)
	{
		//throw new UnsupportedOperationException();
		this.remember[location.getRow()][location.getColumn()]=player;

		Location first= new Location(location.getRow()+1,location.getColumn() );
		Location second= new Location(location.getRow()-1,location.getColumn() );
		Location third= new Location(location.getRow(),location.getColumn()+1 );
		Location forth= new Location(location.getRow(),location.getColumn()-1 );
		Location fifth= new Location(location.getRow()-1,location.getColumn()+1 );
		Location sixth= new Location(location.getRow()+1,location.getColumn() -1);

		int loc=Convert(location);
		
		if(getPlayer(first)==player)
		{w.union(loc, Convert(first));}
		else if(getPlayer(second)==player)
		{w.union(loc, Convert(second));}
		else if(getPlayer(third)==player)
		{w.union(loc, Convert(third));}
		else if(getPlayer(forth)==player)
		{w.union(loc, Convert(forth));}
		else if(getPlayer(fifth)==player)
		{w.union(loc, Convert(fifth));}
		else if(getPlayer(sixth)==player)
		{w.union(loc, Convert(sixth));}
		
		if(location.getRow()==0 && location.getColumn()==0)
		{
			if(player==1)
			{
				united.union(loc,this.unite[unite.length-4]);
			}
			if(player==2)
			{
				united.union(loc,this.unite[unite.length-1]);
			}
		}
		else if(location.getRow()==0 && location.getColumn()==getColumns()-1)
		{
			if(player==1)
			{
				united.union(loc,this.unite[unite.length-4]);
			}
			if(player==2)
			{
				united.union(loc,this.unite[unite.length-3]);
			}
		}
		else if(location.getRow()==getRows()-1 && location.getColumn()==getColumns()-1)
		{
			if(player==1)
			{
				united.union(loc,this.unite[unite.length-3]);
			}
			if(player==2)
			{
				united.union(loc,this.unite[unite.length-2]);
			}
		}
		else if(location.getRow()==getRows()-1 && location.getColumn()==0)
		{
			if(player==1)
			{
				united.union(loc,this.unite[unite.length-2]);
			}
			if(player==2)
			{
				united.union(loc,this.unite[unite.length-1]);
			}
		}
	}
	
	private int Convert(Location location)
	{
		int loc= 1+getColumns()*location.getRow()+location.getColumn();
		return loc;
	}

	// Although the GameManager does not need to call this method, the
	// tests will call it to help verify that you're using the
	// union-find data structure correctly
	public boolean isConnected(Location location1, Location location2)
	{
		//throw new UnsupportedOperationException();
		return this.w.connected(Convert(location1), Convert(location2));
	}

	// Returns whether the specified location on the board contains
	// a game piece that is connected to one of the corresponding
	// player's sides.
	public int getSideConnection(Location location)
	{
		throw new UnsupportedOperationException();
	}

	// Returns one of the three "player" ints indicating who is the winner
	// of the current Board.  PLAYER_NONE indicates no one has won yet.
	public int getCurrentWinner()
	{
		throw new UnsupportedOperationException();
	}
}
