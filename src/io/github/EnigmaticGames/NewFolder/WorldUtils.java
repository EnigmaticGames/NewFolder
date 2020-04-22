package io.github.EnigmaticGames.NewFolder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class WorldUtils {
	public static int chunkCount = 16;
	public static int chunkWidth = 16;
	public static int chunkHeight = 64;
	public static int chunkWidthPixels = chunkWidth * 32;
	public static int chunkHeightPixels = chunkHeight * 32;
	
	private static boolean doDebug = false;
	private static void debug(String msg) {
		if(doDebug)
			System.out.println("WorldUtils Debug: " + msg);
	}
	
	// Find out which chunk an item is in
	public static int getChunkId(CollisionItem item) {
		int chunk = item.x / chunkWidthPixels;
		return chunk;
	}
	
	public static Tile[] calculateCollision(Tile[][] chunk) {
		Tile[] items = new Tile[chunkWidth * chunkHeight];
		int currentTileID = 0;
		
		debug("Deciding collision objects for chunk.");
		
		for(int y = 0; y < chunkHeight; y++) {
			for(int x = 0; x < chunkWidth; x++) {
				if(chunk[y][x].id != 0) { // Don't check for collisions with air
					if(y == 0) {
						// Don't check anywhere
						items[currentTileID] = chunk[y][x];
					} else if(y == chunkHeight - 1) {
						// Don't check anywhere
						items[currentTileID] = chunk[y][x];
					} else {
						// Make sure we have at least 1 face touching air
						if(chunk[y - 1][x].id == 0) 
							items[currentTileID] = chunk[y][x];
						
						if(chunk[y + 1][x].id == 0) 
							items[currentTileID] = chunk[y][x];
						
						if(x != 0)
							if(chunk[y][x - 1].id == 0)
								items[currentTileID] = chunk[y][x];
						
						if(x != chunkWidth - 1)
							if(chunk[y][x + 1].id == 0)
								items[currentTileID] = chunk[y][x];
					}
					
					if(items[currentTileID] != null)
						debug("X" + Integer.toString(x) + "Y" + Integer.toString(y));
				}
				currentTileID ++;
			}
		}
		
		return items;
	}
	
	public static BufferedImage renderChunk(Tile[][] chunk) {
		//debug("Rendering chunk to texture...");
		BufferedImage finalChunk = new BufferedImage(chunkWidth * 32, chunkHeight * 32, BufferedImage.TYPE_INT_ARGB);
		Graphics2D graphics = finalChunk.createGraphics();
		
		for(int y = 0; y < chunkHeight; y++) {
			for(int x = 0; x < chunkWidth; x++) {
				if(chunk[y][x].id != 0) {
					//debug("Tile at X" + Integer.toString(x) + "Y" + Integer.toString(y) + " not blank.");
					// TODO: Draw sprites
					if((y/2)*2 == y) {
						if((x/2)*2 == x)
							graphics.setColor(new Color(255, 0, 255));
						else
							graphics.setColor(new Color(0, 0, 0));
					} else {
						if((x/2)*2 == x)
							graphics.setColor(new Color(0, 0, 0));
						else
							graphics.setColor(new Color(255, 0, 255));
					}
					
					graphics.fillRect(x * 32, y * 32, 32, 32);
					graphics.setColor(new Color(255, 255, 255));
					graphics.drawRect(x * 32, y * 32, 32, 32);
				} else {
					//debug("Tile at X" + Integer.toString(x) + "Y" + Integer.toString(y) + " blank.");
				}
			}
		}
		graphics.dispose();
		return finalChunk;
	}
}
