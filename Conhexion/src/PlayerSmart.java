public class PlayerSmart implements Player
{
	// Constructs a new instance of the PlayerSmart class
	

	// Returns the Location where this Player chooses to move
	@Override
	public Location getNextMove(Board board, int player)
	{
		//throw new UnsupportedOperationException();
		int winum=0;
		int max=0;
		Location loc= new Location(0,0);

		for(int i=0; i<board.getRows(); i++)
		{
			for(int j=0; j<board.getColumns(); j++)
			{
				winum=0;
				if(board.getPlayer(new Location(i,j))==Board.PLAYER_NONE)
				{
					Board b= new Board(board);

					b.setPlayer(new Location(i,j), player);
					for(int n=0; n<2000; n++)//
					{
						Player[] players = new Player[] { new PlayerRandom(), new PlayerRandom() };

						int curPlayer=1;
							while (b.getCurrentWinner() == b.PLAYER_NONE) 
							{
								
								Player play = players[curPlayer - 1];
								
								// Ask player for its move
								
								
								// Apply the move to the Board
								b.setPlayer(play.getNextMove(b, curPlayer), curPlayer);

								
								// Toggle to the next player
								curPlayer = 3 - curPlayer;
								
							}
						if(b.getCurrentWinner()==player)
						{
							winum++;
						}
					}//
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


		
}
