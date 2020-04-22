package io.github.EnigmaticGames.NewFolder;

import javax.swing.JFrame;
import io.github.EnigmaticGames.NewFolder.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Window extends JFrame implements MouseListener {
	public BufferedImage frameBuffer;
	public Keyboard keyManager;
	public int cameraX;
	public int cameraY;
	
	public int blockClickX = 0;
	public int blockClickY = 0;
	public int blockClickChunk = 0;
	public boolean blockClicked;
	
	public Window(int width, int height) {
		super(); // Create JFrame
		setTitle("New Folder"); // Set window title
		keyManager = new Keyboard(); // Setup keyboard listener
		addKeyListener(keyManager); // Make JFrame use keyboard listener
		addMouseListener(this); // Setup mouse listening
		setSize(width, height); // Set window size
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Terminate the game on window close
		setLocationRelativeTo(null); // Center window
		setVisible(true); // Make window visible
		frameBuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB); // Create image for framebuffer
	}
	
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse click!");
	}
	
	public void mouseEntered(MouseEvent e) {
		System.out.println("Mouse enter!");
	}
	
	public void mouseExited(MouseEvent e) {
		System.out.println("Mouse exit!");
	}
	
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		int worldX = x + cameraX;
		int worldY = y + cameraY;
		if(worldY >= 0 && worldX > 0) {
			int worldChunk = worldX / WorldUtils.chunkWidthPixels;
			int blockX = (worldX - (worldChunk * WorldUtils.chunkWidthPixels)) / 32;
			int blockY = worldY / 32;
			System.out.println("Block X" + Integer.toString(blockX) + "Y" + Integer.toString(blockY) + " in chunk " + Integer.toString(worldChunk) + " clicked.");
			blockClickX = blockX;
			blockClickY = blockY;
			blockClickChunk = worldChunk;
			blockClicked = true;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse release!");
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
