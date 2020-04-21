package io.github.EnigmaticGames.NewFolder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import io.github.EnigmaticGames.NewFolder.*;

public class Main {
	// Window settings
	private static final int windowWidth = 800;
	private static final int windowHeight = 600;
	private static final int maxFPS = 60;
	public static Window window;
	
	// Player settings
	public static Player player;
	
	// World storage
	public static Tile[][][] world;
	private static BufferedImage[] renderedChunks;
	
	public static void main(String[] args) {
		window = new Window(windowWidth, windowHeight);
		player = new Player();
		
		// Worldgen
		System.out.println("Generating world...");
		world = new Tile[16][64][16];
		renderedChunks = new BufferedImage[16];
		
		// Populate world with chunks of air
		for(int chunk = 0; chunk < 16; chunk++) {
			for(int y = 0; y < 64; y++) {
				for(int x = 0; x < 16; x++) {
					world[chunk][y][x] = new Tile(0);
				}
			}
		}
		
		// Generate world (flat line for now)
		for(int chunk = 0; chunk < 16; chunk++) {
			System.out.print("Generating chunk " + Integer.toString(chunk) + "...");
			for(int x = 0; x < 16; x++) {
				world[chunk][chunk][x] = new Tile(1);
			}
			// We pre-render all chunks; chunks will need to be re-rendered if they're modified.
			// This provides a pretty good performance boost.
			System.out.print(" Done. Rendering...");
			renderedChunks[chunk] = WorldUtils.renderChunk(world[chunk]);
			System.out.println("Done.");
		}
		
		
		boolean running = true;
		while(running) {
			try {
				if(window.keyManager.left)
					window.cameraX -= 7;
				
				if(window.keyManager.right)
					window.cameraX += 7;
				
				if(window.keyManager.up)
					window.cameraY -= 7;
				
				if(window.keyManager.down)
					window.cameraY += 8;
				
				for(int chunk = 0; chunk < 16; chunk++) {
					window.drawImageCamera(renderedChunks[chunk], 512 * chunk, 100);
				}
				window.repaint();
				Thread.sleep(1000 / maxFPS);
			} catch(Exception e) {
				System.out.println("Game crashed!");
				running = false;
			}
		}
	}
}

/*
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
}*/
