package io.github.EnigmaticGames.NewFolder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends CollisionItem {
	public int yVelocity = 0;
	public boolean isOnFloor = false;
	
	public Player() {
		super(0, -300, 32, 64);
		Graphics2D graphics = sprite.createGraphics();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 16, 16);
		graphics.dispose();
	}
	
	public boolean collideWith(Tile[] tiles) {
		boolean retCode = false;
		isOnFloor = false;
		
		for(Tile tile : tiles) {
			if(tile != null) {
				if(collidingWith(tile)) {
					retCode = true;
					if(tile.y < y + height) {
						if(y + height - tile.y <= yVelocity) {
							isOnFloor = true;
							yVelocity = 0;
							while(y + height > tile.y) {
								y -= 1;
							}
						}
					}
				}
			}
		}
		return retCode;
	}
}
