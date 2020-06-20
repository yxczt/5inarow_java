package game;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SerializationTest {
	ScoreBoard scb;
	Continuegames cgs = new Continuegames();
	@Before
	public void setup() throws ClassNotFoundException, FileNotFoundException, IOException
	{
		cgs.setContinuegames("continuegames_test.txt");
		scb= new ScoreBoard("scoreboard_test.txt");
	}
	
	@Test
	public void isOnTheBoardGoodTest() 
	{
		Player p= new Player("hello", new Token(TokenType.o));
		scb.addPlayer(p);
		boolean b= scb.IsOnTheScBoard(p);
		Assert.assertTrue(b);
	}
	@Test
	public void isOnTheBoardWrongTest() 
	{
		Player p= new Player("hello", new Token(TokenType.o));
		boolean b= scb.IsOnTheScBoard(p);
		Assert.assertFalse(b);
	}
	@Test
	public void changeResultOfGoodTest() 
	{
		Player p= new Player("a", new Token(TokenType.o));
		p.setWon(true);
		scb.changeResultOf(p);
		boolean b= scb.IsOnTheScBoard(p);
		Assert.assertTrue(b);
	}
	@Test
	public void saveScBoardTest() throws ClassNotFoundException, FileNotFoundException, IOException 
	{
		Player p= new Player("hello", new Token(TokenType.o));
		p.setWon(true);
		scb.addPlayer(p);
		scb.saveScBoard("scoreboard_test2.txt");
		ScoreBoard newscb = new ScoreBoard("scoreboard_test2.txt");
		Assert.assertNotEquals(scb.getScBoard(), newscb.getScBoard());
	}
	@Test
	public void addGameToContinuegamesTest() throws ClassNotFoundException, FileNotFoundException, IOException
	{
		Board b = new Board();
		Player p1 = new Player("first", new Token(TokenType.x));
		Player p2 = new Player("second", new Token(TokenType.o));
		Game g = new Game(b, p1, p2);
		cgs.add(g);
		cgs.saveContinuegames("continuegames_test2.txt");
		Continuegames newcgs = new Continuegames();
		newcgs.setContinuegames("continuegames_test2.txt");
		Assert.assertNotEquals(cgs.getContinuegames(), newcgs.getContinuegames());
	}
}
