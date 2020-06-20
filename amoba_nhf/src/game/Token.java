package game;

import java.io.Serializable;

public class Token implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TokenType tt;
	public TokenType getTokenType()
	{
		return tt;
	}
	public Token(TokenType tt)
	{
		this.tt=tt;
	}
}
