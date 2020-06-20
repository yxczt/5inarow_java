package game;

import java.io.Serializable;

public class Player implements Comparable<Player>, Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int playedgames;
	private int wongames;
	private int lostgames;
	private Token t;
	private boolean won;
	
	public Player(String name, Token t)
	{
		this.name=name;
		this.playedgames = 0;
		this.wongames = 0;
		this.lostgames = 0;
		this.t=t;
		this.won=false;
	}
	public String getName()
	{
		return this.name;
	}
	public String toString()
	{
		return name + " " + t.getTokenType().toString();
	}
	public String playerScore()
	{
		return this.name + " " + this.playedgames + " " + this.wongames + " " + this.lostgames + " " + this.getPower();
	}
	public int getPower()
	{
		return this.wongames-this.lostgames;
	}
	public void setWon(boolean b)
	{
		this.won=b;
	}
	public boolean getWon()
	{
		return won;
	}
	public int getWongames()
	{
		return wongames;
	}
	public void incPlayedgames()
	{
		this.playedgames++;
	}
	public void incWongames()
	{
		this.wongames++;
	}
	public void incLostgames()
	{
		this.lostgames++;
	}
	public Token getToken()
	{
		return t;
	}
	@Override
	public int compareTo(Player p) {
		return this.getPower()-p.getPower();
	}

}