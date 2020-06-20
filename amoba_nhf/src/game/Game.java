package game;

import java.io.Serializable;
import java.util.Date;


public class Game implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Board b;
	private Player p1;
	private Player p2;
	private Player next;
	private Date date;
	private boolean firstmove = true;
	public Game(Board b, Player p1, Player p2)
	{
		this.b = b;
		this.p1 = p1;
		this.p2 = p2;
		this.next = p1;
		this.firstmove = true;
		this.date=new Date(System.currentTimeMillis());
	}
	public boolean IsThereWinner()
	{
        for (int x = 0; x <15; x++) 
        {
            for (int y = 0; y <19; y++) 
            {
                Token now=b.getToken(x, y);
                if (now.getTokenType()!=TokenType.nothing)
                {
	                int streak=0;
	                for(int i=0;i<5;i++)
	                {
	                	if(now.getTokenType() == b.getToken(x+i, y).getTokenType())
	                		{
	                			streak++;
	                		} else break;
	                }
	                if (streak>=5)
	                {
	                	if (now.getTokenType() == p1.getToken().getTokenType())
	                	{
	                		p1.setWon(true);
	                	} else
	                	{
	                		p2.setWon(true);
	                	}
	                	return true;
	                }
	                if (y<15)
	                {
	                	streak=0;
	                	for(int i=0;i<5;i++)
	                	{
	                		if(now.getTokenType() == b.getToken(x, y+i).getTokenType()) streak++;
	                		else break;
	                	}
	                	if (streak>=5)
		                {
	                		if (now.getTokenType() == p1.getToken().getTokenType())
		                	{
		                		p1.setWon(true);
		                	} else
		                	{
		                		p2.setWon(true);
		                	}
		                	return true;
		                }
	                	streak=0;
	                	for(int i=0;i<5;i++)
	                	{
	                		if(now.getTokenType() == b.getToken(x+i, y+i).getTokenType()) streak++;
	                		else break;
	                	}
	                	if (streak>=5)
		                {
	                		if (now.getTokenType() == p1.getToken().getTokenType())
		                	{
		                		p1.setWon(true);
		                	} else
		                	{
		                		p2.setWon(true);
		                	}
		                	return true;
		                }
	                }
	                if (y>3)
	                {
	                	streak=0;
	                	for(int i=0;i<5;i++)
	                	{
	                		if(now.getTokenType() == b.getToken(x+i, y-i).getTokenType()) streak++;
	                		else break;
	                	}
	                	if (streak>=5)
		                {
	                		if (now.getTokenType() == p1.getToken().getTokenType())
		                	{
		                		p1.setWon(true);
		                	} else
		                	{
		                		p2.setWon(true);
		                	}
		                	return true;
		                }
	                }
	                
                }
            }
        }
        return false;
    }
	public void placeToken(int x, int y) throws IndexOutOfBoundsException
	{
		if (x<0 || x>18 || y<0 || y>18) throw new IndexOutOfBoundsException("Nincs ilyen mezo!");
		if (next.equals(p1))
		{
			b.takePlace(x, y, p1.getToken());
			next=p2;
		} else
		{
			b.takePlace(x, y, p2.getToken());
			next=p1;
		}
	}
	public Player getPlayerOne()
	{
		return p1;
	}
	public Player getPlayerTwo()
	{
		return p2;
	}
	public Player getNext()
	{
		return next;
	}
	public Board getBoard()
	{
		return b;
	}
	public boolean checkplace(int x, int y) throws IndexOutOfBoundsException
	{
		if (x<0 || x>18 || y<0 || y>18) throw new IndexOutOfBoundsException("Nincs ilyen mezo!");
		if (firstmove)
			{
				firstmove=false;
				return true;
			}
		if (b.getToken(x, y).getTokenType() != TokenType.nothing)
		{
			return false;
		}
		if(x>0 && x<18 && y>0 && y<18)
		{
			if ((b.getToken(x+1, y).getTokenType() != TokenType.nothing) ||
					(b.getToken(x+1, y+1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x, y+1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x-1, y+1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x-1, y).getTokenType() != TokenType.nothing) ||
					(b.getToken(x-1, y-1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x, y-1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x+1, y-1).getTokenType() != TokenType.nothing))
			{
				return true;
			}
		} else if (x==18 && y>0 && y<18)
		{
			if (	(b.getToken(x, y+1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x-1, y+1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x-1, y).getTokenType() != TokenType.nothing) ||
					(b.getToken(x-1, y-1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x, y-1).getTokenType() != TokenType.nothing))
			{
				return true;
			}
		}else if (x>0 && x<18 && y==18 )
		{
			if ((b.getToken(x+1, y).getTokenType() != TokenType.nothing) ||
					(b.getToken(x-1, y).getTokenType() != TokenType.nothing) ||
					(b.getToken(x-1, y-1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x, y-1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x+1, y-1).getTokenType() != TokenType.nothing))
			{
				return true;
			}
		}else if(x==0 && y>0 && y<18)
		{
			if ((b.getToken(x+1, y).getTokenType() != TokenType.nothing) ||
					(b.getToken(x+1, y+1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x, y+1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x, y-1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x+1, y-1).getTokenType() != TokenType.nothing))
			{
				return true;
			}
		} else if(x>0 && x<18 && y==0)
		{
			if ((b.getToken(x+1, y).getTokenType() != TokenType.nothing) ||
					(b.getToken(x+1, y+1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x, y+1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x-1, y+1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x-1, y).getTokenType() != TokenType.nothing))
			{
				return true;
			}
		} else if(x==0 && y==0)
		{
			if ((b.getToken(x+1, y).getTokenType() != TokenType.nothing) ||
					(b.getToken(x+1, y+1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x, y+1).getTokenType() != TokenType.nothing))
			{
				return true;
			}
		} else if(x==18 && y==18)
		{
			if ((b.getToken(x-1, y).getTokenType() != TokenType.nothing) ||
					(b.getToken(x-1, y-1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x, y-1).getTokenType() != TokenType.nothing))
			{
				return true;
			}
		} else if(x==0 && y==18)
		{
			if ((b.getToken(x+1, y).getTokenType() != TokenType.nothing) ||
					(b.getToken(x+1, y-1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x, y-1).getTokenType() != TokenType.nothing))
			{
				return true;
			}
		} else if(x==18 && y==0)
		{
			if ((b.getToken(x-1, y).getTokenType() != TokenType.nothing) ||
					(b.getToken(x-1, y+1).getTokenType() != TokenType.nothing) ||
					(b.getToken(x, y+1).getTokenType() != TokenType.nothing))
			{
				return true;
			}
		}
		return false;
	}
	public String toString()
	{
		String pl1 = p1.toString();
		String pl2 = p2.toString();
		String d = date.toString();
		String n = next.toString();
		String s = (pl1 + " " + pl2 + " " + d + " next move: " + n);
		return s;
	}
	public void setSaveDate(Date d)
	{
		this.date=d;
	}

}
