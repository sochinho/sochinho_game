package sochinho_game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener{
	
	private final int BOARD_WIDTH = 500;
	private final int BOARD_HEIGHT = 400;
	private final int BOARD_DELAY = 15;
	private Timer timer;
	private final String[] menuPositions = {"START GAME", "EXIT GAME"};
	private final String logoPath = "sochicraft_logo.png";
	private final String bgPath = "background.png";
	private final String footerText = "Author: sochinho@hotmail.com";
	private ArrayList<MyButton> menuButtons;
	Mode currentMode;
	GameInstance game;
	
	public GameBoard()	{
		timer = new Timer(BOARD_DELAY, this);
		menuButtons = new ArrayList<MyButton>();
		for(int i=0; i<menuPositions.length; i++)	{
			MyButton jb = new MyButton(menuPositions[i]);
			jb.setName(menuPositions[i]);
			menuButtons.add(jb);
		}
		initGameBoard();
		initMenu();
	}
	
	public void initGameBoard()	{
		currentMode = Mode.IDLE;
		setFocusable(true);
		setBackground(Color.LIGHT_GRAY);
		setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
		setVisible(true);
	}
	
	public void initMenu()	{
		timer.stop();
		currentMode = Mode.MENU;
		drawMenu();
		initUI();
	}
	
	private void drawMenu()	{
		setLayout(new GridBagLayout());
		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon(logoPath));
		add(logo);
		int topPadding = (int)Math.round(0.05*BOARD_HEIGHT);
		int xPadding = (int)Math.round(0.1*BOARD_WIDTH);
		int btnWidth = (int)Math.round(0.05*BOARD_HEIGHT);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(topPadding, xPadding, 0, xPadding);
		for(int i=0; i<menuButtons.size(); i++)	{
			MyButton jb = menuButtons.get(i);
			c.ipady = btnWidth;
			c.gridy = i+1;
			add(jb, c);
		}
		c.gridy = menuButtons.size()+2;
		JLabel footer = new JLabel();
		footer.setText(footerText);
		add(footer, c);
	}
	
	private void drawObjects(Graphics g)	{
		g.drawImage(new ImageIcon(bgPath).getImage(), 0, 0, null);
		if(game.getCraft().isVisible())	{
			g.drawImage(game.getCraft().getImage(), game.getCraft().getX(), game.getCraft().getY(), this);
		}
		for(Missile m : game.getMissiles())	{
			if(m.isVisible())
				g.drawImage(m.getImage(), m.getX(), m.getY(), this);
		}
		for(Alien a : game.getAliens())	{
			if(a.isVisible())
				g.drawImage(a.getImage(), a.getX(), a.getY(), this);
		}
		for(Heart h : game.getHearts())	{
			if(h.isVisible())
				g.drawImage(h.getImage(), h.getX(), h.getY(), this);
		}
		g.setColor(Color.WHITE);
		g.drawString("Distance: " + game.getDistance() + " meters", 5, 50);
	}
	
	@Override
	public void paintComponent(Graphics g)	{
		super.paintComponent(g);

		switch(currentMode)	{
		case GAME:
			drawObjects(g);
			break;
		case MENU:
			break;
		case IDLE:
			setVisible(false);
		default:
			break;
		}
		
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void initUI()	{
		for(MyButton jb : menuButtons)	{
			jb.addMouseListener(new MouseAdapter()	{
				@Override
				public void mouseClicked(MouseEvent e)	{
					switch(jb.getName())	{
					case "START GAME":
						startGame();
						break;
					case "EXIT GAME":
						exitGame();
						break;
					default:
						break;
					}
				}
			});

		}
	}
	
	public void startGame()	{
		currentMode = Mode.GAME;
		game = new GameInstance();
		addKeyListener(game.getTAdapter());
		timer.start();
		removeAll();
	};
	
	public void stopGame()	{
		currentMode = Mode.MENU;
		timer.stop();
		removeAll();
		setVisible(false);
		drawMenu();
		setVisible(true);
	}
	
	public void exitGame() {
		currentMode = Mode.IDLE;
		setVisible(false);
		((Window) getRootPane().getParent()).dispose();
	};
	
  @Override
  public void actionPerformed(ActionEvent e)	{
	  if(game.isInGame())
		  repaint();
	  else	{
		  stopGame();
	  }
		  
  }
  
}
