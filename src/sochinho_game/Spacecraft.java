package sochinho_game;

public class Spacecraft extends Sprite {

	private int moveX;
	private int moveY;
	private int energy;
	
	Spacecraft(int x, int y)	{
		super(x, y);
		initSpacecraft();
	}
	
	private void initSpacecraft()	{
		loadImage("spacecraft.png");
		getImageDim();
		energy = 100;
	}
	
	public void moveLeft()	{
		moveX = -1;
	}
	
	public void moveRight()	{
		moveX = 1;
	}
	
	public void moveUp()	{
		moveY = -1;
	}
	
	public void moveDown()	{
		moveY = 1;
	}
	
	public void releaseHornizontal()	{
		moveX = 0;
	}
	
	public void releaseVertical()	{
		moveY = 0;
	}
	
	public void move()	{
		xPos += moveX;
		yPos += moveY;
	}
	
	public void crash()	{
		energy -= 25;
	}
	
	public boolean isAlive()	{
		return energy > 0;
	}
	
	public int getEnergy()	{
		return energy;
	}
	
}
