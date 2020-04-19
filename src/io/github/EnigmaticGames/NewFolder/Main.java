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
	
	public static void main(String[] args) {
		Window window = new Window(screenWidth, screenHeight); // Create game window
		Player player = new Player(); // Create player
		
		// Render the static world objects once, into this image (this will change a bit when support for buildings and stuff is added)
		BufferedImage staticWorldObjects = new BufferedImage(worldWidth, worldHeight, BufferedImage.TYPE_INT_RGB);
		// TODO: Parse a tilemap and render to staticWorldObjects
		
		// Camera position
		int cameraX = 0;
		int cameraY = 0;
		
		boolean running = true; // True unless game crashed
		
		while(running) {
			try {
				window.drawImage(player.sprite, 392, 292);
				window.repaint();
				
				Thread.sleep(1000/FPSCap);
			} catch(Exception e) {
				running = false;
			}
		}
		
		// Game crash
	}
}
