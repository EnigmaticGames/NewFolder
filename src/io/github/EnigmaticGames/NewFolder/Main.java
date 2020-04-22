package io.github.EnigmaticGames.NewFolder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import io.github.EnigmaticGames.NewFolder.*;

public class Main {
	// Window settings
	private static final int windowWidth = 800; // Game window width
	private static final int windowHeight = 600; // Game window height
	private static final int maxFPS = 60; // Game is locked to this FPS
	public static Window window; // The actual window
	
	// Player settings
	public static Player player; // The player object
	private static int gravSpeed = 1;
	private static int maxGravity = 10;
	private static int jumpHeight = -12; // Still needs some tweaking to make it feel a-ok
	
	// World storage
	public static Tile[][][] world; // This stores all of the tiles in the world
	public static Tile[][] chunkCollision; /// This stores all of the tiles that are touching air
	private static BufferedImage[] renderedChunks; // This stores images of each chunk so we have to render at most 1 per frame
	
	public static void main(String[] args) {
		window = new Window(windowWidth, windowHeight); // Setup the window
		player = new Player(); // Initialize the player
		
		// Worldgen, just a straight line for now
		System.out.println("Generating world...");
		world = new Tile[WorldUtils.chunkCount][WorldUtils.chunkHeight][WorldUtils.chunkWidth]; // 16 chunks that are 16 wide and 64 tall
		renderedChunks = new BufferedImage[WorldUtils.chunkCount]; // 16 chunks
		chunkCollision = new Tile[WorldUtils.chunkCount][WorldUtils.chunkWidth * WorldUtils.chunkHeight]; // 16 chunks, 1024 blocks per chunk
		
		// Populate world with chunks of air
		for(int chunk = 0; chunk < WorldUtils.chunkCount; chunk++) {
			for(int y = 0; y < WorldUtils.chunkHeight; y++) {
				for(int x = 0; x < WorldUtils.chunkWidth; x++) {
					world[chunk][y][x] = new Tile(0, (chunk * 512) + (x * 32), y * 32);
				}
			}
		}
		
		// Generate world (flat line for now)
		for(int chunk = 0; chunk < 16; chunk++) {
			System.out.print("Generating chunk " + Integer.toString(chunk) + "...");
			for(int y = 0; y < 64; y++) {
				for(int x = 0; x < 16; x++) {
					world[chunk][y][x] = new Tile(1, (chunk * 512) + (x * 32), y * 32);
				}
			}
			
			// We pre-render all chunks; chunks will need to be re-rendered if they're modified.
			// This provides a pretty good performance boost.
			// TODO: Decide what tiles to check when checking for collision (only check ones surrounded by air)
			System.out.print(" Done. Rendering...");
			renderedChunks[chunk] = WorldUtils.renderChunk(world[chunk]);
			System.out.println("Done. Calculating collision...");
			chunkCollision[chunk] = WorldUtils.calculateCollision(world[chunk]);
			System.out.println("Done.");
		}
		
		
		boolean running = true;
		while(running) {
			try {
				int currentChunk = WorldUtils.getChunkId(player);
				
				if(window.keyManager.left)
					player.x -= 7;
				
				if(window.keyManager.right)
					player.x += 7;
				
				player.yVelocity += gravSpeed;
				if(player.yVelocity > maxGravity)
					player.yVelocity = maxGravity;
				
				player.y += player.yVelocity;
				player.collideWith(chunkCollision[currentChunk]);
				if(player.isOnFloor(chunkCollision[currentChunk])) {
					player.yVelocity = 0;
					if(window.keyManager.up) 
						player.yVelocity += jumpHeight;
				}
				
				if(currentChunk != 0) {
					// We can draw all 3 chunks
					window.drawImageCamera(renderedChunks[currentChunk - 1], 512 * (currentChunk - 1), 0);
					window.drawImageCamera(renderedChunks[currentChunk], 512 * currentChunk, 0);
					window.drawImageCamera(renderedChunks[currentChunk + 1], 512 * (currentChunk + 1), 0);
				} else if(currentChunk == 15) {
					// There are only 16 chunks in the world
					window.drawImageCamera(renderedChunks[currentChunk - 1], 512 * (currentChunk - 1), 0);
					window.drawImageCamera(renderedChunks[currentChunk], 512 * currentChunk, 0);
				} else {
					// There are no negative chunk IDs so we can only draw 0 and 1
					window.drawImageCamera(renderedChunks[currentChunk], 512 * currentChunk, 0);
					window.drawImageCamera(renderedChunks[currentChunk + 1], 512 * (currentChunk + 1), 0);
				}
				
				window.centerCameraOn(player);
				window.drawImageCamera(player.sprite, player.x, player.y);
				
				window.repaint();
				Thread.sleep(1000 / maxFPS);
			} catch(Exception e) {
				System.out.println("Game crashed!");
				System.out.println(e);
				running = false;
			}
		}
	}
}
