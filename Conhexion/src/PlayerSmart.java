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
		int winum=0;
		int max=0;
		Location loc= new Location(0,0);

		for(int i=0; i<b.getRows(); i++)
		{
			for(int j=0; j<b.getColumns(); j++)
			{
				winum=0;
				if(b.getPlayer(new Location(i,j))==Board.PLAYER_NONE)
				{

					b.setPlayer(new Location(i,j), player);
					for(int n=0; n<1000; n++)//
					{
						
						if(play(b)==player)
						{
							winum++;
						}
					}
					if(winum>=max)
					{
						max=winum;
						loc= new Location(i,j);
					}
					
				}
			}
		}
		return loc;
		
		


		
		

	}


		public int play(Board board)
		{
			Player[] players = new Player[] { new PlayerRandom(), new PlayerRandom() };

			while (board.getCurrentWinner() == 0) 
			{
				Player play = players[curPlayer - 1];
				
				// Ask player for its move
				
				
				// Apply the move to the Board
				board.setPlayer(play.getNextMove(board, curPlayer), curPlayer);

				
				// Toggle to the next player
				curPlayer = 3 - curPlayer;
				
			}
			return board.getCurrentWinner();
		}
}
