package sochinho_game;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Sprite {

	protected int xPos;
	protected int yPos;
	private int width;
	private int height;
	private boolean isVisible;
	private Image img;
	
	public Sprite(int x, int y)	{
		xPos = x;
		yPos = y;
		isVisible = true;
	}
	
	protected void loadImage(String image)	{
		ImageIcon ii = new ImageIcon(image);
		img = ii.getImage();
	}
	
	protected void getImageDim()	{
		width = img.getWidth(null);
		height = img.getHeight(null);
	}
	
	public Image getImage()	{
		return img;
	}
	
	public int getX()	{
		return xPos;
	}
	
	public int getY()	{
		return yPos;
	}
	
	public int getWidth()	{
		return width;
	}
	
	public int getHeight()	{
		return height;
	}
	
	public boolean isVisible()	{
		return isVisible;
	}
	
	public void setVisible(boolean visible)	{
		isVisible = visible;
	}
	
	public Rectangle getBounds()	{
		return new Rectangle(xPos, yPos, width, height);
	}
	
}
