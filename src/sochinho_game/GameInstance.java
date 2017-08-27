package sochinho_game;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.Timer;

public class GameInstance implements ActionListener{

	private boolean inGame;
	private final int GAME_DELAY = 5;
	private Timer timer;
	private int distance;
    private final int CRAFT_X = 40;
    private final int CRAFT_Y = 60;
    private final int HEART_ENERGY = 25;
    private final int ALIENS_NUMBER = 1000;
    private final int MIN_ALIEN_X = 300;
    private final int MIN_ALIEN_Y = 10;
    private final int MAX_ALIEN_X = 15000;
    private final int MAX_ALIEN_Y = 300;
	private Spacecraft craft;
	private ArrayList<Missile> missiles;
	private ArrayList<Alien> aliens;
	private ArrayList<Heart> hearts;
	private int[][] alienPositions;
	private TAdapter ta;
	
    public GameInstance()	{
    	initGame();
    }
    
    public Spacecraft getCraft()	{
    	return craft;
    }
    
    public ArrayList<Missile> getMissiles()	{
    	return missiles;
    }
    
    public ArrayList<Alien> getAliens()	{
    	return aliens;
    }
    
    public ArrayList<Heart> getHearts()	{
    	return hearts;
    }
    
    public int getDistance()	{
    	return distance;
    }
    
    public void initGame()	{
    	ta = new TAdapter();
    	alienPositions = new int[ALIENS_NUMBER][2];
    	craft = new Spacecraft(CRAFT_X, CRAFT_Y);
    	missiles = new ArrayList<Missile>();
    	aliens = new ArrayList<Alien>();
    	initAliens();
    	hearts = new ArrayList<Heart>();
    	initHearts();
    	inGame = true;
        timer = new Timer(GAME_DELAY, this);
        timer.start();
        distance = 0;
    }
    
    public void initAliens()	{
    	for(int i=0; i<ALIENS_NUMBER; i++)	{
    		alienPositions[i][0] = ThreadLocalRandom.current().nextInt(MIN_ALIEN_X, MAX_ALIEN_X);
    		alienPositions[i][1] = ThreadLocalRandom.current().nextInt(MIN_ALIEN_Y, MAX_ALIEN_Y);
    		aliens.add(new Alien(alienPositions[i][0], alienPositions[i][1]));
    	}
    }
    
    public void initHearts()	{
    	int heartsNum = (int) craft.getEnergy()/HEART_ENERGY;
    	for(int i=0; i<heartsNum; i++)	{
    		Heart h = new Heart(i*40+5, 0);
    		h.setVisible(true);
    		hearts.add(h);
    	}
    }
    
	public boolean isInGame()	{
		return inGame;
	}
	
    public void updateCraft() {
    	if(!craft.isAlive())	{
    		craft.setVisible(false);
    		inGame = false;
    	}
    	if(craft.isVisible())
    		craft.move();
    }
    
    public void updateMissiles() {
    	for(int i=0; i<missiles.size(); i++)	{
    		Missile m = missiles.get(i);
    		if(m.isVisible())
    			m.move();
    		else
    			missiles.remove(m);
    	}
    }
    
    public void updateAliens() {
    	for(int i=0; i<aliens.size(); i++)	{
    		Alien a = aliens.get(i);
    		if(a.isVisible())
    			a.move();
    		else
    			aliens.remove(a);
    	}
    }
    
    public void updateDistance()	{
    	distance += 1;
    }
    
    public void updateHearts()	{
    	if(craft.getEnergy()/HEART_ENERGY < hearts.size())	{
    		Heart h = hearts.get(hearts.size()-1);
    		h.setVisible(false);
    		hearts.remove(h);
    	}
    }
    
    public void checkCollisions()	{
    	Rectangle rc = craft.getBounds();
    	for(Alien a : aliens)
    		if(a.getBounds().intersects(rc))	{
    			craft.crash();
    			a.setVisible(false);
    		}
    	
    	for(Missile m : missiles)	{
    		Rectangle mr = m.getBounds();
    		for(Alien a : aliens)	{
    			if(a.getBounds().intersects(mr))
    				a.setVisible(false);
    		}
    	}
    	
    }
    
    public void fireMissile()	{
    	Missile m = new Missile(craft.getX()+craft.getWidth(), craft.getY()+10);
    	m.setVisible(true);
    	missiles.add(m);
    }
    
    @Override
	public void actionPerformed(ActionEvent e)	{
    	if(isInGame())	{
    		updateCraft();
    		updateMissiles();
    		updateAliens();
    		updateDistance();
    		updateHearts();
    		checkCollisions();
    	}
    	else
    		timer.stop();
    }
    
    public TAdapter getTAdapter()	{
    	return ta;
    }
    
	private class TAdapter extends KeyAdapter	{
		
		@Override
		public 	void keyReleased(KeyEvent e)	{
			int key = e.getKeyCode();
			switch(key)	{
			case KeyEvent.VK_LEFT:
				craft.releaseHornizontal();
				break;
			case KeyEvent.VK_RIGHT:
				craft.releaseHornizontal();
				break;
			case KeyEvent.VK_UP:
				craft.releaseVertical();
				break;
			case KeyEvent.VK_DOWN:
				craft.releaseVertical();
				break;
			}
		}
		
		@Override
		public 	void keyPressed(KeyEvent e)	{
			int key = e.getKeyCode();
			switch(key)	{
			case KeyEvent.VK_SPACE:
				fireMissile();
				break;
			case KeyEvent.VK_LEFT:
				craft.moveLeft();
				break;
			case KeyEvent.VK_RIGHT:
				craft.moveRight();
				break;
			case KeyEvent.VK_UP:
				craft.moveUp();
				break;
			case KeyEvent.VK_DOWN:
				craft.moveDown();
				break;
			}
		}
		
	}
	
}
