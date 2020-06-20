package game;

import java.util.ArrayList;

import org.junit.Test;

public class BoardTest {
	Board b = new Board();
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void setUpBoardWrongTest() throws ArrayIndexOutOfBoundsException
	{
		ArrayList<ArrayList<Token>> gb = new ArrayList<ArrayList<Token>>();
		for (int i=0; i<12; i++)
		{
			ArrayList<Token> row = new ArrayList<Token>();
			for (int j=0; j<19; j++)
			{
				Token e = new Token(TokenType.nothing);
				row.add(e);
			}
			gb.add(row);
		}
		b.setUpBoard(gb);
	}
	@Test (expected = ArrayIndexOutOfBoundsException.class)
	public void setUpBoardWrongAgainTest() throws ArrayIndexOutOfBoundsException
	{
		ArrayList<ArrayList<Token>> gb = new ArrayList<ArrayList<Token>>();
		for (int i=0; i<19; i++)
		{
			ArrayList<Token> row = new ArrayList<Token>();
			for (int j=0; j<22; j++)
			{
				Token e = new Token(TokenType.nothing);
				row.add(e);
			}
			gb.add(row);
		}
		b.setUpBoard(gb);
	}
	@Test
	public void setUpBoardGoodTest() throws ArrayIndexOutOfBoundsException
	{
		ArrayList<ArrayList<Token>> gb = new ArrayList<ArrayList<Token>>();
		for (int i=0; i<19; i++)
		{
			ArrayList<Token> row = new ArrayList<Token>();
			for (int j=0; j<19; j++)
			{
				Token e = new Token(TokenType.x);
				row.add(e);
			}
			gb.add(row);
		}
		b.setUpBoard(gb);
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void takePlaceWrongCheck() throws IndexOutOfBoundsException
	{
		b.takePlace(-1, -1, new Token(TokenType.x));
	}

}
