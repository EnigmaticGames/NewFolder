package io.github.EnigmaticGames.NewFolder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player extends CollisionItem {
	public Player() {
		super(0, 0, 16, 16);
		Graphics2D graphics = sprite.createGraphics();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 16, 16);
		graphics.dispose();
	}
}
