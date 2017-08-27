package sochinho_game;

public class Alien extends Sprite{
	
	private final int ALIEN_SPEED = 1;
	private final int MIN_XPOS = 0;
	
	public Alien(int x, int y)	{
		super(x, y);
		
		loadImage("alien.png");
		getImageDim();
	}
	
	public void move()	{
		xPos -= ALIEN_SPEED;
		if(xPos < MIN_XPOS)
			setVisible(false);
	}
	

}
