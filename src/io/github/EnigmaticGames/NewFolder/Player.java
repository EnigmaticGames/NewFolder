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
	
	public boolean collideWith(Tile[] tiles) {
		boolean retCode = false;
		for(Tile tile : tiles) {
			//System.out.println(tile.x);
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
