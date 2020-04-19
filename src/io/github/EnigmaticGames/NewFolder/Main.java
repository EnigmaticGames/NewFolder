package io.github.EnigmaticGames.NewFolder;

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
		
		// Render the static world objects once, into this image
		BufferedImage staticWorldObjects = new BufferedImage(worldWidth, worldHeight, BufferedImage.TYPE_INT_RGB);
		// TODO: Parse a tilemap and render to staticWorldObjects
		
		// Render all the dynamic world objects every frame, into this image
		BufferedImage dynamicWorldObjects = new BufferedImage(worldWidth, worldHeight, BufferedImage.TYPE_INT_RGB);
		
		// Camera position
		int cameraX = 0;
		int cameraY = 0;
		
		boolean running = true; // True unless game crashed
		
		while(running) {
			try {
				
				// Draw things to screen
				window.drawImage(staticWorldObjects, 0 - cameraX, 0 - cameraY);
				window.drawImage(dynamicWorldObjects, 0 - cameraX, 0 - cameraY);
				window.update();
				
				Thread.sleep(1000/FPSCap);
			} catch(Exception e) {
				running = false;
			}
		}
		
		// Game crash
	}
}
