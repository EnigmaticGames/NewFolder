package io.github.EnigmaticGames.NewFolder;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Keyboard extends KeyAdapter {
	public boolean left = false;
	public boolean right = false;
	public boolean up = false;
	public boolean down = false;
	
	public void keyPressed(KeyEvent event) {
		int keycode = event.getKeyCode();
		if(keycode == KeyEvent.VK_LEFT)
			left = true;
		if(keycode == KeyEvent.VK_RIGHT)
			right = true;
		if(keycode == KeyEvent.VK_UP)
			up = true;
		if(keycode == KeyEvent.VK_DOWN)
			down = true;
	}
	
	public void keyReleased(KeyEvent event) {
		int keycode = event.getKeyCode();
		if(keycode == KeyEvent.VK_LEFT)
			left = false;
		if(keycode == KeyEvent.VK_RIGHT)
			right = false;
		if(keycode == KeyEvent.VK_UP)
			up = false;
		if(keycode == KeyEvent.VK_DOWN)
			down = false;
	}
}