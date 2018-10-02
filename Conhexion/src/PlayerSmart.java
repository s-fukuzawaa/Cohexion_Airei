public class PlayerSmart implements Player
{
	// Constructs a new instance of the PlayerSmart class
	public PlayerSmart()
	{
		//throw new UnsupportedOperationException()
	}

	// Returns the Location where this Player chooses to move
	@Override
	public Location getNextMove(Board board, int player)
	{
		//throw new UnsupportedOperationException();
		while (true)
		{
			int row = (int) (Math.random() * board.getRows());
			int column = (int) (Math.random() * board.getColumns());
			int existingPlayer = board.getPlayer(new Location(row, column));
			if (existingPlayer == Board.PLAYER_NONE)
			{
				Location loc= new Location(row, column);
				Board b= new Board(board);
				b.setPlayer(loc, 1);
				
				return loc;
			}
		}
		
		

	}
}
