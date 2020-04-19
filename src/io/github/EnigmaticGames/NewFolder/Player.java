package io.github.EnigmaticGames.NewFolder;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Player {
	BufferedImage sprite;
	public Player() {
		sprite = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = sprite.createGraphics();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 16, 16);
		graphics.dispose();
	}
}
