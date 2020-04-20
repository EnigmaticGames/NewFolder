package io.github.EnigmaticGames.NewFolder;

public class CollisionItem extends WorldItem {
	public int x;
	public int y;
	public int width;
	public int height;
	
	public CollisionItem(int setX, int setY, int width, int height) {
		super(width, height);
		x = setX;
		y = setY;
		width = width;
		height = height;
	}
	
	public boolean collidingWith(CollisionItem item) {
		// thank u mozilla
		if(x < item.x + item.width && x + width > item.x && y < item.y + item.width && y + height > item.y)
			return true;
		return false;
	}

}
