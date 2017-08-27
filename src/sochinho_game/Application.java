package sochinho_game;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Application extends JFrame{
	
	private GameBoard gb;
	
	Application()	{
		initUI();
	}
	
	public void initUI()	{
		gb = new GameBoard();
		add(gb);
		setResizable(false);
		pack();
		setTitle("Sochicraft 2.0");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[])	{
		EventQueue.invokeLater(new Runnable()	{
			@Override
			public void run()	{
				Application app = new Application();
				app.setVisible(true);
			}
		});
	}
	
}
