import java.util.Arrays;

public class Board
{
	public static final int PLAYER_NONE = 0;
	public static final int PLAYER_1 = 1;
	public static final int PLAYER_2 = 2;
		
	private int rows;
	private int columns;
	//2 dimentional array that represents board
	private int[][] board;
	//array that includes the 4 sites
	private WeightedQuickUnionUFCloneable w;
	private int[] unite;


	// Constructs a new board with the specified number of rows and columns
	public Board(int rows, int columns)
	{
		//throw new UnsupportedOperationException();
		this.rows=rows;
		this.columns=columns;
		this.board= new int[this.rows][this.columns];
		for(int i=0; i<this.rows; i++)
		{
			for(int j=0; j<this.columns; j++)
			{
				this.board[i][j]=0;
			}
		}
		this.w= new WeightedQuickUnionUFCloneable(this.columns*this.rows+4);
		this.unite= new int[this.columns*this.rows+4];
		for(int i=0; i<this.columns*this.rows+4; i++)
		{
			unite[i]=i;
		}
		
	}

	// Constructs a new Board that clones the state of the specified Board
	public Board(Board original)
	{
		//throw new UnsupportedOperationException();
		this.rows=original.getRows();
		this.columns=original.getColumns();
		this.w= new WeightedQuickUnionUFCloneable(this.columns*this.rows+4);
		this.unite= new int[this.columns*this.rows+4];
		for(int i=0; i<this.columns*this.rows+4; i++)
		{
			unite[i]=i;
		}
		this.board= new int[this.rows][this.columns];
		for(int i=0; i<this.rows; i++)
		{
			for(int j=0; j<this.columns; j++)
			{
				if(original.getPlayer(new Location(i,j))!=0)
				{
					setPlayer(new Location(i,j),original.getPlayer(new Location(i,j)));
				}
				this.board[i][j]=original.getPlayer(new Location(i,j));
			}
		}
		
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
		return this.board[location.getRow()][location.getColumn()];
	}

	// Places a game piece from the specified player (represented by
	// one of the three "player" ints defined on this class) into the
	// specified location on the board
	public void setPlayer(Location location, int player)
	{
		//throw new UnsupportedOperationException();
		this.board[location.getRow()][location.getColumn()]=player;

		int loc=Convert(location);
		
		if(player!=0)
		{
			if(location.getRow()==0)
			{
				if(location.getColumn()==0)
				{
					if(getPlayer(new Location(location.getRow()+1,location.getColumn() ))==player)
					{w.union(loc, Convert(new Location(location.getRow()+1,location.getColumn() )));}
					else if(getPlayer(new Location(location.getRow(),location.getColumn()+1 ))==player)
					{w.union(loc, Convert(new Location(location.getRow(),location.getColumn()+1 )));}
					if(player==2)
					{
						w.union(loc,this.unite[unite.length-1]);
	
					}
					else if(player==1)
					{
						w.union(loc,this.unite[unite.length-4]);
	
					}
				
				}
				
				else if(location.getColumn()==getColumns()-1)
				{
					Location forth= new Location(location.getRow(),location.getColumn()-1 );
					Location fifth= new Location(location.getRow()+1,location.getColumn()-1 );
					Location first= new Location(location.getRow()+1,location.getColumn() );
					if(getPlayer(first)==player)
					{w.union(loc, Convert(first));}
					else if(getPlayer(forth)==player)
					{w.union(loc, Convert(forth));}
					else if(getPlayer(fifth)==player)
					{w.union(loc, Convert(fifth));}
					
					if(player==1)
					{
						w.union(loc,this.unite[unite.length-4]);
					}
					else if(player==2)
					{
						w.union(loc, this.unite[unite.length-3]);
					}
	
				}
				
				else
				{
					Location forth= new Location(location.getRow(),location.getColumn()-1 );
					Location fifth= new Location(location.getRow()+1,location.getColumn()-1 );
					Location first= new Location(location.getRow()+1,location.getColumn() );
					Location third= new Location(location.getRow(),location.getColumn()+1 );
					
					if(getPlayer(first)==player)
					{
						if(player==1)
						{
							w.union( this.unite[unite.length-4],Convert(first));
						}
						w.union(loc, Convert(first));

					}
					 if(getPlayer(third)==player)
					{
						if(player==1)
						{
							w.union(this.unite[unite.length-4],Convert(third));
						}

						w.union(loc, Convert(third));
					}
					if(getPlayer(forth)==player)
					{
						if(player==1)
						{
							w.union(this.unite[unite.length-4],Convert(forth));
						}

						w.union(loc, Convert(forth));
					}
					if(getPlayer(fifth)==player)
					{
						if(player==1)
						{
							w.union(this.unite[unite.length-4],Convert(fifth));
						}
						w.union(loc, Convert(fifth));

					}
					else if(player==1)
					{
						w.union(loc, this.unite[unite.length-4]);
					}
					
				}
			}
			else if(location.getRow()==getRows()-1)
			{
				if(location.getColumn()==0)
				{
					Location second= new Location(location.getRow()-1,location.getColumn() );
					Location third= new Location(location.getRow(),location.getColumn()+1 );
					Location fifth= new Location(location.getRow()-1,location.getColumn()+1 );
					if(getPlayer(second)==player)
					{w.union(loc, Convert(second));}
					else if(getPlayer(third)==player)
					{w.union(loc, Convert(third));}
					else if(getPlayer(fifth)==player)
					{w.union(loc, Convert(fifth));}
					
					if(player==2)
					{
						w.union(loc,this.unite[unite.length-1]);
	
					}
					else if(player==1)
					{
						w.union(loc,this.unite[unite.length-2]);
	
					}
				}
				
				else if(location.getColumn()==getColumns()-1)
				{
					Location second= new Location(location.getRow()-1,location.getColumn() );
					Location forth= new Location(location.getRow(),location.getColumn()-1 );
					 if(getPlayer(second)==player)
					{w.union(loc, Convert(second));}
					else if(getPlayer(forth)==player)
					{w.union(loc, Convert(forth));}
					 
					 if(player==1)
					 {
							w.union(loc,this.unite[unite.length-2]);
					 }
					 else if(player==2)
					 {
							w.union(loc, this.unite[unite.length-3]);
	
					 }
				}
				
				else 
				{
					Location second= new Location(location.getRow()-1,location.getColumn() );
					Location third= new Location(location.getRow(),location.getColumn()+1 );
					Location fifth= new Location(location.getRow()-1,location.getColumn()+1 );
					Location forth= new Location(location.getRow(),location.getColumn()-1 );
					if(getPlayer(second)==player)
					{
						if(player==1)
						{
							w.union(this.unite[unite.length-2],Convert(second));
						}

						w.union(loc, Convert(second));
					}
					if(getPlayer(third)==player)
					{
						if(player==1)
						{
							w.union(this.unite[unite.length-2],Convert(third));
						}

						w.union(loc, Convert(third));
					}
					if(getPlayer(forth)==player)
					{
						if(player==1)
						{
							w.union(this.unite[unite.length-2],Convert(forth));
						}
						w.union(loc, Convert(forth));

					}
					if(getPlayer(fifth)==player)
					{
						if(player==1)
						{
							w.union( this.unite[unite.length-2],Convert(fifth));
						}
						w.union(loc, Convert(fifth));

					}
					else if(player==1)
					{
						w.union(loc, this.unite[unite.length-2]);
					}
				}
			}
			else if(location.getColumn()==getColumns()-1)
			{
				Location first= new Location(location.getRow()+1,location.getColumn() );
				Location second= new Location(location.getRow()-1,location.getColumn() );
				Location forth= new Location(location.getRow(),location.getColumn()-1 );
				Location sixth= new Location(location.getRow()+1,location.getColumn() -1);
				if(getPlayer(first)==player)
				{
					if(player==2)
					{
						w.union(this.unite[unite.length-3],Convert(first));
					}
					w.union(loc, Convert(first));
				}
				if(getPlayer(second)==player)
				{
					if(player==2)
					{
						w.union(this.unite[unite.length-3],Convert(second));
					}
					w.union(loc, Convert(second));
				}
				if(getPlayer(forth)==player)
				{
					if(player==2)
					{
						w.union(this.unite[unite.length-3],Convert(forth));
					}
					w.union(loc, Convert(forth));

				}
				if(getPlayer(sixth)==player)
				{
					if(player==2)
					{
						w.union(this.unite[unite.length-3],Convert(sixth));
					}
					w.union(loc, Convert(sixth));

				}
				else if(player==2)
				{
					w.union(loc, this.unite[unite.length-3]);
				}
				
			}
			else if(location.getColumn()==0)
			{
				Location first= new Location(location.getRow()+1,location.getColumn() );
				Location second= new Location(location.getRow()-1,location.getColumn() );
				Location fifth= new Location(location.getRow()-1,location.getColumn()+1 );
				Location third= new Location(location.getRow(),location.getColumn()+1 );
				if(getPlayer(first)==player)
				{
					if(player==2)
					{
						w.union(this.unite[unite.length-1],Convert(first));
					}

					w.union(loc, Convert(first));
				}
				if(getPlayer(second)==player)
				{
					if(player==2)
					{
						w.union(this.unite[unite.length-1],Convert(second));
					}
					w.union(loc, Convert(second));

				}
				if(getPlayer(third)==player)
				{
					if(player==2)
					{
						w.union(this.unite[unite.length-1],Convert(third));
					}
					w.union(loc, Convert(third));

				}
				if(getPlayer(fifth)==player)
				{
					if(player==2)
					{
						w.union(this.unite[unite.length-1],Convert(fifth));
					}
					w.union(loc, Convert(fifth));

				}
				else if(player==2)
				{
					w.union(loc, this.unite[unite.length-1]);
				}
				
			}
			else 
			{
				Location first= new Location(location.getRow()+1,location.getColumn() );
				Location second= new Location(location.getRow()-1,location.getColumn() );
				Location third= new Location(location.getRow(),location.getColumn()+1 );
				Location forth= new Location(location.getRow(),location.getColumn()-1 );
				Location fifth= new Location(location.getRow()-1,location.getColumn()+1 );
				Location sixth= new Location(location.getRow()+1,location.getColumn() -1);
				
				if(getPlayer(first)==player)
				{w.union(Convert(first),loc);}
				if(getPlayer(second)==player)
				{w.union( Convert(second),loc);}
				if(getPlayer(third)==player)
				{w.union(Convert(third),loc);}
				if(getPlayer(forth)==player)
				{w.union(Convert(forth),loc);}
				if(getPlayer(fifth)==player)
				{w.union(Convert(fifth),loc);}
				if(getPlayer(sixth)==player)
				{w.union(Convert(sixth),loc);}
			}
		}
		
		
	}
	
	private int Convert(Location location)
	{
		int loc= getColumns()*location.getRow()+location.getColumn();
		return loc;
	}

	// Although the GameManager does not need to call this method, the
	// tests will call it to help verify that you're using the
	// union-find data structure correctly
	public boolean isConnected(Location location1, Location location2)
	{
		//throw new UnsupportedOperationException();
		
		return w.connected(Convert(location1), Convert(location2));
	}

	// Returns whether the specified location on the board contains
	// a game piece that is connected to one of the corresponding
	// player's sides.
	public int getSideConnection(Location location)
	{
		//throw new UnsupportedOperationException();
		int player=this.board[location.getRow()][location.getColumn()];
		int loc= Convert(location);
		if(player==1)
		{
			if(w.connected(loc, this.unite[unite.length-2])||w.connected(loc, this.unite[unite.length-4]))
			{
				return 1;
			}
		}
		if(player==2)
		{
			if(w.connected(loc, this.unite[unite.length-1])||w.connected(loc, this.unite[unite.length-3]))
			{
				return 2;
			}
		}
		return 0;
		
		
	}

	// Returns one of the three "player" ints indicating who is the winner
	// of the current Board.  PLAYER_NONE indicates no one has won yet.
	public int getCurrentWinner()
	{
		//throw new UnsupportedOperationException();
		if(w.connected(this.unite.length-4, this.unite.length-2)==true)
		{
			return 1;
		}
		if(w.connected(this.unite.length-3,this.unite.length-1)==true)
		{
			return 2;
		}
		return 0;
	}
}
