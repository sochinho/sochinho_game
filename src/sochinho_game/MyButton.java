package sochinho_game;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class MyButton extends JButton{
	
	public MyButton()	{
		super();
		setBorder(BorderFactory.createLineBorder(Color.gray));
		initEffects();
	}
	
	public MyButton(String btnString)	{
		super(btnString);
		setBorder(BorderFactory.createLineBorder(Color.gray));
		initEffects();
	}
	
	private void initEffects()	{
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBorder(BorderFactory.createLineBorder(Color.black));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				setBorder(BorderFactory.createLineBorder(Color.gray));
			}
		});
	}
}
