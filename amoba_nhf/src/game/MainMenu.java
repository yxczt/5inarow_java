package game;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.*;


public class MainMenu extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JMenuItem newgame;
	private JMenuItem continuegame;
	private JMenuItem scoreboard;
	private JMenuItem exit;
	private JMenu m;
	private JMenuBar mb;
	private DrawCanvas canvas;
	
	
	private boolean gameon = false;
	
	public MainMenu() throws ClassNotFoundException, FileNotFoundException, IOException
	{
		this.setTitle("Amoba");
		this.setDefaultCloseOperation(MainMenu.DO_NOTHING_ON_CLOSE);
		this.setSize(577, 623);
		GridLayout lm=new GridLayout();
		lm.setRows(1);
		lm.setColumns(1);
		this.setLayout(lm);
		
		newgame = new JMenuItem("New Game");
		ActionListener ngal = new NewgameActionListener();
		newgame.addActionListener(ngal);
		continuegame = new JMenuItem("Continue Game");
		ActionListener cgal = new ContinuegameActionListener();
		continuegame.addActionListener(cgal);
		scoreboard = new JMenuItem("Scoreboard");
		ActionListener sbal = new ScoreboardActionListener();
		scoreboard.addActionListener(sbal);
		exit = new JMenuItem("Exit");
		ActionListener eal = new ExitActionListener();
		exit.addActionListener(eal);
		m = new JMenu("Menu");
		m.add(newgame);
		m.add(continuegame);
		m.add(scoreboard);
		m.add(exit);
		mb = new JMenuBar();
		mb.add(m);
		this.setJMenuBar(mb);
		
		Continuegames cgs= new Continuegames();
		cgs.setContinuegames("continuegames.txt");
		ScoreBoard scb= new ScoreBoard("scoreboard.txt");
		canvas = new DrawCanvas(cgs, scb);
		this.setContentPane(canvas);
		MouseListener ml = new CanvasMouseListener();
		canvas.addMouseListener(ml);
		
		this.setVisible(true);
		this.setResizable(false);
	}
	private class NewgameActionListener implements ActionListener
	{		
		@Override
		public void actionPerformed(ActionEvent ae) 
		{
			if(gameon)
			{
				canvas.addToCgs(canvas.getGame());
			}
			String name1 = (String) JOptionPane.showInputDialog(null, "Please enter the first player's name!",
					"First Player", JOptionPane.PLAIN_MESSAGE);
			if(name1!=null)
			{
				String name2 = (String) JOptionPane.showInputDialog(null, "Please enter the second player's name!",
						"Second Player", JOptionPane.PLAIN_MESSAGE);
				if(name2!=null)
				{
					Player p1 = new Player(name1, new Token(TokenType.x));
					Player p2 = new Player(name2, new Token(TokenType.o));
					Board b = new Board();
					Game g = new Game(b, p1, p2);
					canvas.setGame(g);
					gameon = true;
					canvas.redraw();
				}
				
			}
		}
	}
	private class CanvasMouseListener implements MouseListener
	{
		@Override
		public void mouseClicked(MouseEvent me) throws IndexOutOfBoundsException
		{
				if(gameon)
				{
					int x = me.getX()/30;
					int y= me.getY()/30;
					if(x<0 || x>19 || y<0 || y>19)
					{
						throw new IndexOutOfBoundsException("Nincs ilyen mezo!");
					}
					if (canvas.getGame().checkplace(x,y))
					{
							canvas.getGame().placeToken(x, y);
							canvas.redraw();
						if (canvas.getGame().IsThereWinner())
						{
							gameon = false;
							if(canvas.getGame().getPlayerOne().getWon())
							{
							JOptionPane.showMessageDialog(
								    canvas, canvas.getGame().getPlayerOne().getName() + " won!");
							} else
							{
								JOptionPane.showMessageDialog(
									    canvas, canvas.getGame().getPlayerTwo().getName() + " won!");
							}
							canvas.refreshScores(canvas.getGame().getPlayerOne());
							canvas.refreshScores(canvas.getGame().getPlayerTwo());
						}
					}
				}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			
			
		}
	}
	private class ExitActionListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(gameon)
			{
				canvas.addToCgs(canvas.getGame());
			}
			try {
				canvas.saveContinuegames("continuegames.txt");
				canvas.saveScoreBoard("scoreboard.txt");
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);
		}
		
	}
	private class ContinuegameActionListener implements ActionListener
	{		
		@Override
		public void actionPerformed(ActionEvent ae) 
		{
			if(gameon)
			{
				canvas.addToCgs(canvas.getGame());
			}
			String[] als = new String[canvas.getCGames().getContinuegames().size()];
			for (int i=0; i<als.length;i++)
			{
				als[i]=canvas.getCGames().getContinuegames().get(i).toString();
			}
			String game = (String) JOptionPane.showInputDialog(null, "Please select game to continue!", 
					"Continue Game", JOptionPane.PLAIN_MESSAGE, null, als, null);
			if(game!=null)
			{
				for (int i=0; i<canvas.getCGames().getContinuegames().size();i++)
				{
					if(game.equals(canvas.getCGames().getContinuegames().get(i).toString()))
					{
						canvas.setGame(canvas.getCGames().getContinuegames().get(i));
						canvas.getCGames().remove(i);
					}
				}
				gameon = true;
				canvas.redraw();
			}
		}
	}
	private class ScoreboardActionListener implements ActionListener
	{		
		@Override
		public void actionPerformed(ActionEvent ae) 
		{
			int size = 10;
			if (canvas.getScoreBoard().numberOfPlayers()<10)
			{
				size=canvas.getScoreBoard().numberOfPlayers();
			}
			String bestof = "Place Name Played Won Lost Power\n";
			for(int i=0; i<size; i++)
			{
				bestof+= i+1 + " " + canvas.getScoreBoard().getScBoard().get(i).playerScore() + "\n";
			}
				JOptionPane.showMessageDialog(null, bestof);
		}
	}
	
}