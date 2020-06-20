package game;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public class Board implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ArrayList<Token>> gameboard = new ArrayList<ArrayList<Token>>();
	public Board()
	{
		for (int i=0; i<19; i++)
		{
			ArrayList<Token> row = new ArrayList<Token>();
			for (int j=0; j<19; j++)
			{
				Token e = new Token(TokenType.nothing);
				row.add(e);
			}
			gameboard.add(row);
		}
	}
	public void setUpBoard(ArrayList<ArrayList<Token>> gb) throws ArrayIndexOutOfBoundsException
	{
		if (gb.size() != 19)
		{
			throw new ArrayIndexOutOfBoundsException("Nem jo a meret!");
		}
		for (int i=0; i<18; i++)
		{
			if (gb.get(i).size() != 19)
			{
				throw new ArrayIndexOutOfBoundsException("Nem jo a meret!");
			}
		}
		gameboard=gb;
	}
	public void takePlace(int x, int y, Token t)
	{
		if(gameboard.size()<x || gameboard.get(x).size()<y || x<0 || y<0)
		{
			throw new IndexOutOfBoundsException();
		}
		gameboard.get(x).remove(y);
		gameboard.get(x).add(y,t);
	}
	public Token getToken(int x, int y)
	{
		return gameboard.get(x).get(y);
	}
	public void draw(Graphics gr)
	{
		for (int i=0; i<19;i++)
        {
       	 for (int j=0;j<19;j++)
       	 {
       		 if (getToken(i,j).getTokenType() == TokenType.x)
       		 {
       			 gr.setColor(Color.YELLOW);
       			 gr.drawLine(30*i, 30*j, 30*(i+1), 30*(j+1));
       			 gr.drawLine(30*(i+1), 30*j, 30*i, 30*(j+1));
       		 } else if(getToken(i,j).getTokenType() == TokenType.o)
       		 {
       			 gr.setColor(Color.GREEN);
       			 gr.drawOval(30*i, 30*j, 29, 29);
       		 }	 
       	 }
        }
	}
}
