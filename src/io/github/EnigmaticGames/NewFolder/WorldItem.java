package io.github.EnigmaticGames.NewFolder;

import java.awt.image.BufferedImage;

public class WorldItem {
	public BufferedImage sprite;
	
	public WorldItem(int width, int height) {
		sprite = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	}
}
