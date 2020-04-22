package io.github.EnigmaticGames.NewFolder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends CollisionItem {
	public int yVelocity = 0;
	public Player() {
		super(0, -300, 32, 64);
		Graphics2D graphics = sprite.createGraphics();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 16, 16);
		graphics.dispose();
	}
	
	public boolean isOnFloor(Tile[] tiles) {
		boolean retCode = false;
		for(Tile tile : tiles) {
			if(tile != null) {
				if(x < tile.x + tile.width && x + width > tile.x) {
					if(tile.y - (y + height) <= 3 && tile.y - (y + height) >= -3)
						retCode = true;
				}
			}
		}
		return retCode;
	}
	
	public boolean collideWith(Tile[] tiles) {
		boolean retCode = false;
		for(Tile tile : tiles) {
			if(collidingWith(tile)) {
				retCode = true;
				while(y + height > tile.y) {
					y -= 1;
				}
			}
		}
		return retCode;
	}
}
