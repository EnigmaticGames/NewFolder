package io.github.EnigmaticGames.NewFolder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import io.github.EnigmaticGames.NewFolder.*;

public class Main {
	static int worldWidth = 1600;
	static int worldHeight = 1200;
	static int screenWidth = 800;
	static int screenHeight = 600;
	
	static int FPSCap = 60;
	
	public static Player player;
	public static Window window;
	
	public static void drawRelative(CollisionItem item) {
		window.drawImage(item.sprite, item.x - player.x + 392, item.y - player.y + 292);
	}
	
	public static void main(String[] args) {
		window = new Window(screenWidth, screenHeight); // Create game window
		player = new Player(); // Create player
		CollisionItem box = new CollisionItem(64, 64, 16, 16);
		Graphics2D boxGraphics = box.sprite.createGraphics();
		boxGraphics.setColor(Color.BLUE);
		boxGraphics.fillRect(0, 0, 16, 16);
		boxGraphics.dispose();
		
		// Render the static world objects once, into this image (this will change a bit when support for buildings and stuff is added)
		BufferedImage staticWorldObjects = new BufferedImage(worldWidth, worldHeight, BufferedImage.TYPE_INT_RGB);
		// TODO: Parse a tilemap and render to staticWorldObjects
		
		boolean running = true; // True unless game crashed
		
		while(running) {
			try {
				if(window.keyManager.left)
					player.x -= 5;
				
				if(window.keyManager.right)
					player.x += 5;
				
				if(window.keyManager.up)
					player.y -= 5;
				
				if(window.keyManager.down)
					player.y += 5;
				
				window.drawImage(player.sprite, 392, 292); // Player should always be centered on screen.
				drawRelative(box);
				if(player.collidingWith(box)) {
					System.out.println("Colliding!");
				}
				window.repaint();
				
				Thread.sleep(1000/FPSCap);
			} catch(Exception e) {
				running = false;
			}
		}
		
		// Game crash
	}
}
