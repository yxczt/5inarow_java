package game;

import java.awt.Color;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JPanel;

public class DrawCanvas extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game g;
    private Continuegames cgs;
    private ScoreBoard scoreboard;
    public DrawCanvas(Continuegames cgs, ScoreBoard scb)
    {
    	this.cgs=cgs;
    	this.scoreboard=scb;
    }
    
    @Override
    public void paintComponent(Graphics gr) {
       super.paintComponent(gr);
       setBackground(Color.BLACK);
       setForeground(Color.GRAY);
       for (int i=0; i<20; i++)
       {
      	 gr.drawLine(30*i, 0, 30*i, 570);
       }
       for (int j=0; j<20; j++)
       {
      	 gr.drawLine(0, 30*j, 570, 30*j);
       }
       if (g!=null)
       {
    	   g.getBoard().draw(gr);
       }
    }
    public void redraw()
    {
    	repaint();
    }
    public void setGame(Game g)
    {
    	this.g=g;
    }
    public Game getGame()
    {
    	return g;
    }
    public Continuegames getCGames()
    {
    	return cgs;
    }
    public void addToCgs(Game g)
    {
    	cgs.add(g);
    }
    public void saveContinuegames(String file) throws IOException, ClassNotFoundException, FileNotFoundException
	{
		cgs.saveContinuegames(file);
	}
    public ScoreBoard getScoreBoard()
    {
    	return scoreboard;
    }
    public void refreshScores(Player p)
    {
    	if (scoreboard.IsOnTheScBoard(p))
    	{
    		scoreboard.changeResultOf(p);
    	} else
    	{
    		scoreboard.addPlayer(p);
    	}
    }
    public void saveScoreBoard(String file) throws ClassNotFoundException, FileNotFoundException, IOException
    {
    	scoreboard.saveScBoard(file);
    }
}