package game;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.ArrayList;

public class Continuegames {
	private ArrayList<Game> gs = new ArrayList<Game>();
	
	public ArrayList<Game> getContinuegames()
	{
		return gs;
	}
	public void setContinuegames(String file) throws IOException, ClassNotFoundException, FileNotFoundException
	{
		FileInputStream f = new FileInputStream(file);
		if(f.available()>0)
		{
			ObjectInputStream in = new ObjectInputStream(f);
	    	while(f.available()>0)
	    	{
	    		gs.add((Game)in.readObject());
	    	}
	    	in.close();
		} else
		{
			f.close();
		}
	}
	public void saveContinuegames(String file) throws IOException, ClassNotFoundException, FileNotFoundException
	{
		FileOutputStream f = new FileOutputStream(file);
		ObjectOutputStream out = new ObjectOutputStream(f);
		for(Game g: gs) 
		{
			g.setSaveDate(new Date(System.currentTimeMillis()));
			out.writeObject(g);
		}
		out.close();
	}
	public void add(Game g)
	{
		gs.add(g);
	}
	public void remove(int i)
	{
		gs.remove(i);
	}
}
