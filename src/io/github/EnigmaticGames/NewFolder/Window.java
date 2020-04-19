package io.github.EnigmaticGames.NewFolder;

import javax.swing.JFrame;

import io.github.EnigmaticGames.NewFolder.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Window extends JFrame {
	BufferedImage frameBuffer;
	Keyboard keyManager;
	
	public Window(int width, int height) {
		super();
		setTitle("New Folder");
		keyManager = new Keyboard();
		addKeyListener(keyManager);
		setSize(width, height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}
	
	public void drawImage(BufferedImage image, int x, int y) {
		Graphics2D graphics = frameBuffer.createGraphics();
		graphics.drawImage(image, x, y, this);
		graphics.dispose();
	}
	
	public void update() {
		Graphics2D graphics = frameBuffer.createGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, getWidth(), getHeight());
		graphics.dispose();
		repaint();
	}
	
	public void paint(Graphics g) {
		g.drawImage(frameBuffer, 0, 0, this);
	}
}
