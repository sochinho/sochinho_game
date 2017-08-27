package sochinho_game;

public class Missile extends Sprite	{

	private final int MISSILE_SPEED = 2;
	private final int MAX_XPOS = 450;
	
	public Missile(int x, int y)	{
		super(x, y);
		
		loadImage("missile.png");
		getImageDim();
	}
	
	public void move()	{
		xPos += MISSILE_SPEED;
		if(xPos > MAX_XPOS)
			setVisible(false);
	}
	
}
