public class PlayerSmart implements Player
{
	// Constructs a new instance of the PlayerSmart class
	private int curPlayer;
	public PlayerSmart()
	{
		//throw new UnsupportedOperationException()
		this.curPlayer = Board.PLAYER_1;
	}

	// Returns the Location where this Player chooses to move
	@Override
	public Location getNextMove(Board board, int player)
	{
		//throw new UnsupportedOperationException();
		Board b= new Board(board);
		Location loc;
		Location finalloc;
		int max=0;
		while (true)
		{
			int row = (int) (Math.random() * b.getRows());
			int column = (int) (Math.random() * b.getColumns());
			int existingPlayer = b.getPlayer(new Location(row, column));
			if (existingPlayer == Board.PLAYER_NONE)
			{
				loc= new Location(row, column);
				b.setPlayer(loc, player);
				int count=0;
				int i=0;
				while(i<=1000)
				{
					int x=play(b);
					if(x==player)
					{
						count++;
					}
					i++;
				}
				if(count>max)
				{	
					max=count;
					finalloc= new Location(loc.getRow(),loc.getColumn());
				}
				return finalloc;
			}
		}
		

		
		

	}


		public int play(Board board)
		{
			Player[] players = new Player[] { new PlayerRandom(), new PlayerRandom() };

			while (board.getCurrentWinner() == Board.PLAYER_NONE) 
			{
				Player play = players[curPlayer - 1];
				
				// Ask player for its move
				Location nextMove = play.getNextMove(new Board(board), curPlayer);
				
				// Is the move legal?
				int currentOccupant = board.getPlayer(nextMove);
				if (currentOccupant != Board.PLAYER_NONE)
				{
					throw new UnsupportedOperationException("Player # " + curPlayer + " attempted an illegal move in row " +
							nextMove.getRow() + ", column " + nextMove.getColumn() + 
							", which is already occupied by player " + currentOccupant);
				}

				// Apply the move to the Board
				board.setPlayer(nextMove, curPlayer);

				
				// Toggle to the next player
				curPlayer = 3 - curPlayer;
				
			}
			return board.getCurrentWinner();
		}
}
