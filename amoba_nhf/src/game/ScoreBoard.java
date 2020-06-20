package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

public class ScoreBoard {
	private ArrayList<Player> scboard = new ArrayList<Player>();
	
	public ScoreBoard(String file) throws ClassNotFoundException, IOException, FileNotFoundException
	{
		FileInputStream f = new FileInputStream(file);
		if(f.available()>0)
		{
			ObjectInputStream in = new ObjectInputStream(f);
	    	while(f.available()>0)
	    	{
	    		scboard.add((Player)in.readObject());
	    	}
	    	in.close();
		} else
		{
			f.close();
		}
	}
	public ArrayList<Player> getScBoard()
	{
		return scboard;
	}
	public void addPlayer(Player p)
	{
		p.incPlayedgames();
		if(p.getWon())
		{
			p.incWongames();
		} else
		{
			p.incLostgames();
		}
		scboard.add(p);
		Collections.sort(scboard, new PlayerComparator());
	}
	public boolean IsOnTheScBoard(Player p)
	{
		for (int i=0; i<scboard.size();i++)
		{
			if (p.getName().equals(scboard.get(i).getName()))
			{
				return true;
			}
		}
		return false;
	}
	public void changeResultOf(Player p)
	{
		for (int i=0; i<scboard.size();i++)
		{
			if (p.getName().equals(scboard.get(i).getName()))
			{
				scboard.get(i).incPlayedgames();
				if(p.getWon())
				{
					scboard.get(i).incWongames();
				} else
				{
					scboard.get(i).incLostgames();
				}
				Collections.sort(scboard, new PlayerComparator());
				break;
			}
		}
	}
	public void saveScBoard(String file) throws IOException, ClassNotFoundException, FileNotFoundException
	{
		FileOutputStream f = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(f);
		for(Player p: scboard) 
		{
			out.writeObject(p);
		}
		out.close();
	}
	public int numberOfPlayers()
	{
		return scboard.size();
	}
}
