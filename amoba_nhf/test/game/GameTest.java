package game;

import java.io.IOException;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {
	Game g;
	Player p1;
	Player p2;
	Board b;
	Player next;

	@Before
	public void setup()
	{
		b = new Board();
		p1 = new Player("first", new Token(TokenType.x));
		next = p1;
		p2 = new Player("second", new Token(TokenType.o));
		g = new Game(b, p1, p2);
	}
	@Test (expected=IndexOutOfBoundsException.class)
	public void wrongCheckPlaceTest()
	{
		g.checkplace(-1, -1);
	}
	@Test
	public void firstCheckPlaceTest()
	{
		boolean b=g.checkplace(5, 5);
		Assert.assertTrue(b);
		
	}
	@Test
	public void twoTimesSamePlaceCheckPlaceTest()
	{
		g.checkplace(0, 0);
		g.placeToken(0, 0);
		boolean b =g.checkplace(0, 0);
		Assert.assertFalse(b);
	}
	@Test
	public void laterMiddleWrongCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(5, 5);
		g.placeToken(5, 5);
		boolean b=g.checkplace(3, 3);
		Assert.assertFalse(b);
	}
	@Test
	public void laterMiddleGoodCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(5, 5);
		g.placeToken(5, 5);
		boolean b=g.checkplace(5, 4);
		Assert.assertTrue(b);
	}
	@Test
	public void laterLeftSideWrongCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(5, 5);
		g.placeToken(5, 5);
		boolean b=g.checkplace(0, 4);
		Assert.assertFalse(b);
	}
	@Test
	public void laterLeftSideGoodCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(1, 5);
		g.placeToken(1, 5);
		boolean b=g.checkplace(0, 4);
		Assert.assertTrue(b);
	}
	@Test
	public void laterRightSideWrongCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(5, 5);
		g.placeToken(5, 5);
		boolean b=g.checkplace(18, 4);
		Assert.assertFalse(b);
	}
	@Test
	public void laterRightSideGoodCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(18, 5);
		g.placeToken(18, 5);
		boolean b=g.checkplace(18, 4);
		Assert.assertTrue(b);
	}
	@Test
	public void laterTopSideWrongCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(5, 5);
		g.placeToken(5, 5);
		boolean b=g.checkplace(4, 0);
		Assert.assertFalse(b);
	}
	@Test
	public void laterTopSideGoodCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(4, 1);
		g.placeToken(4, 1);
		boolean b=g.checkplace(4, 0);
		Assert.assertTrue(b);
	}
	@Test
	public void laterBottomSideWrongCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(5, 5);
		g.placeToken(5, 5);
		boolean b=g.checkplace(4, 18);
		Assert.assertFalse(b);
	}
	@Test
	public void laterBottomSideGoodCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(4, 17);
		g.placeToken(4, 17);
		boolean b=g.checkplace(4, 18);
		Assert.assertTrue(b);
	}
	@Test
	public void laterLeftTopCornerWrongCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(5, 5);
		g.placeToken(5, 5);
		boolean b=g.checkplace(0, 0);
		Assert.assertFalse(b);
	}
	@Test
	public void laterLeftTopCornerGoodCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(1, 0);
		g.placeToken(1, 0);
		boolean b=g.checkplace(0, 0);
		Assert.assertTrue(b);
	}
	@Test
	public void laterRightTopCornerWrongCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(5, 5);
		g.placeToken(5, 5);
		boolean b=g.checkplace(18, 0);
		Assert.assertFalse(b);
	}
	@Test
	public void laterRightTopCornerGoodCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(17, 0);
		g.placeToken(17, 0);
		boolean b=g.checkplace(18, 0);
		Assert.assertTrue(b);
	}
	@Test
	public void laterLeftBottomCornerWrongCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(5, 5);
		g.placeToken(5, 5);
		boolean b=g.checkplace(0, 18);
		Assert.assertFalse(b);
	}
	@Test
	public void laterLeftBottomCornerGoodCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(1, 17);
		g.placeToken(1, 17);
		boolean b=g.checkplace(0, 18);
		Assert.assertTrue(b);
	}
	@Test
	public void laterRightBottomCornerWrongCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(5, 5);
		g.placeToken(5, 5);
		boolean b=g.checkplace(18, 18);
		Assert.assertFalse(b);
	}
	@Test
	public void laterRightBottomCornerGoodCheckPlaceTest() throws IndexOutOfBoundsException, IOException
	{
		g.checkplace(17, 17);
		g.placeToken(17, 17);
		boolean b=g.checkplace(18, 18);
		Assert.assertTrue(b);
	}
	@Test (expected=IndexOutOfBoundsException.class)
	public void wrongPlacingTest() throws IndexOutOfBoundsException, IOException {
		g.placeToken(-1,-1);
	}
	
	@Test
	public void goodFirstPlacingTest() throws IndexOutOfBoundsException, IOException
	{
		g.placeToken(0, 0);
		TokenType tt = g.getBoard().getToken(0, 0).getTokenType();
		Assert.assertEquals(TokenType.x, tt);
	}
	@Test
	public void goodSecondPlacingTest() throws IndexOutOfBoundsException, IOException
	{
		g.placeToken(0, 0);
		g.placeToken(0, 1);
		TokenType tt = g.getBoard().getToken(0, 1).getTokenType();
		Assert.assertEquals(TokenType.o, tt);
	}
	@Test
	public void goodThirdPlacingTest() throws IndexOutOfBoundsException, IOException
	{
		g.placeToken(0, 0);
		g.placeToken(0, 1);
		g.placeToken(1, 1);
		TokenType tt = g.getBoard().getToken(1, 1).getTokenType();
		Assert.assertEquals(TokenType.x, tt);
	}
	@Test
	public void firstIsThereWinnerCheck()
	{
		boolean b=g.IsThereWinner();
		Assert.assertFalse(b);
	}
	@Test
	public void soonIsThereWinnerCheck()
	{
		g.placeToken(0, 0);
		g.placeToken(0, 1);
		g.placeToken(0, 2);
		g.placeToken(0, 3);
		g.placeToken(0, 4);
		boolean b=g.IsThereWinner();
		Assert.assertFalse(b);
	}
	@Test
	public void laterRowIsThereWinnerCheck()
	{
		g.placeToken(0, 0);
		g.placeToken(0, 1);
		g.placeToken(1, 0);
		g.placeToken(0, 2);
		g.placeToken(2, 0);
		g.placeToken(0, 3);
		g.placeToken(3, 0);
		g.placeToken(0, 4);
		g.placeToken(4, 0);
		boolean b=g.IsThereWinner();
		Assert.assertTrue(b);
	}
	@Test
	public void laterColoumnIsThereWinnerCheck()
	{
		g.placeToken(0, 0);
		g.placeToken(1, 0);
		g.placeToken(0, 1);
		g.placeToken(2, 0);
		g.placeToken(0, 2);
		g.placeToken(3, 0);
		g.placeToken(0, 3);
		g.placeToken(4, 0);
		g.placeToken(0, 4);
		boolean b=g.IsThereWinner();
		Assert.assertTrue(b);
	}
	@Test
	public void laterUpRightDiagonalIsThereWinnerCheck()
	{
		g.placeToken(0, 18);
		g.placeToken(1, 18);
		g.placeToken(1, 17);
		g.placeToken(2, 18);
		g.placeToken(2, 16);
		g.placeToken(3, 18);
		g.placeToken(3, 15);
		g.placeToken(4, 18);
		g.placeToken(4, 14);
		boolean b=g.IsThereWinner();
		Assert.assertTrue(b);
	}
	@Test
	public void laterDownRightDiagonalIsThereWinnerCheck()
	{
		g.placeToken(0, 0);
		g.placeToken(1, 0);
		g.placeToken(1, 1);
		g.placeToken(2, 0);
		g.placeToken(2, 2);
		g.placeToken(3, 0);
		g.placeToken(3, 3);
		g.placeToken(4, 0);
		g.placeToken(4, 4);
		boolean b=g.IsThereWinner();
		Assert.assertTrue(b);
	}
	@Test
	public void toStringCheck()
	{
		String s = g.toString();
		Assert.assertEquals("first x second o " + new Date(System.currentTimeMillis()+1) + " next move: first x", s);
	}
	@Test
	public void getPlayerOneCheck()
	{
		Player p = g.getPlayerOne();
		Assert.assertEquals(p, p1);
	}
	@Test
	public void getPlayerTwoCheck()
	{
		Player p = g.getPlayerTwo();
		Assert.assertEquals(p, p2);
	}
	@Test
	public void getBoardCheck()
	{
		Board bo = g.getBoard();
		Assert.assertEquals(bo, b);
	}
	@Test
	public void getNextCheck()
	{
		Player p = g.getNext();
		Assert.assertEquals(p, next);
	}
	
	
	

}
