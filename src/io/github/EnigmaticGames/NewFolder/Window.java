package io.github.EnigmaticGames.NewFolder;

import javax.swing.JFrame;

import io.github.EnigmaticGames.NewFolder.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Window extends JFrame {
	public BufferedImage frameBuffer;
	public Keyboard keyManager;
	public int cameraX;
	public int cameraY;
	
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
	
	public void drawImageUI(BufferedImage image, int x, int y) {
		Graphics2D graphics = frameBuffer.createGraphics();
		graphics.drawImage(image, x, y, this);
		graphics.dispose();
	}
	
	public void paint(Graphics g) {
		g.drawImage(frameBuffer, 0, 0, this);
		Graphics2D graphics = frameBuffer.createGraphics();
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, frameBuffer.getWidth(), frameBuffer.getHeight());
		graphics.dispose();
	}
	
	public void drawImageCamera(BufferedImage image, int x, int y) {
		Graphics2D graphics = frameBuffer.createGraphics();
		graphics.drawImage(image, x - cameraX, y - cameraY, this);
		graphics.dispose();
	}
	
	public void centerCameraOn(CollisionItem item) {
		cameraX = item.x - 400 + (item.width / 2);
		cameraY = item.y - 300 + (item.height / 2);
	}
	
	public void centerCameraOn(int x, int y) {
		cameraX = x - 400;
		cameraY = y - 300;
	}
}
