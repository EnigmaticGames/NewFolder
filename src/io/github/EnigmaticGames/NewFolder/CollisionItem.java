package io.github.EnigmaticGames.NewFolder;

public class CollisionItem extends WorldItem {
	public int x;
	public int y;
	public int width;
	public int height;
	
	public CollisionItem(int setX, int setY, int setWidth, int setHeight) {
		super(setWidth, setHeight);
		x = setX;
		y = setY;
		width = setWidth;
		height = setHeight;
	}
	
	public boolean collidingWith(CollisionItem item) {
		// thank u mozilla
		if(x < item.x + item.width && x + width > item.x && y < item.y + item.width && y + height > item.y)
			return true;
		return false;
	}
}
