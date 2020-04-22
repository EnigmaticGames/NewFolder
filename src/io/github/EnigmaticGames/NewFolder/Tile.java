package io.github.EnigmaticGames.NewFolder;

import java.awt.Color;
import java.awt.Graphics2D;

public class Tile extends CollisionItem {
	public int id;
	public Tile(int itemID, int setTileX, int setTileY) {
		// The CollisionItem X position is only used for colliding with other objects
		// Drawing is handled by the WorldUtils chunk stuff
		super(setTileX, setTileY, 32, 32);
		id = itemID;
	}
}
