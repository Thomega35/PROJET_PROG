package tile;

import java.awt.image.BufferedImage;

public class Tile {
	public BufferedImage image;
	public boolean collision;

	Tile(boolean collision) {
		this.collision = collision;
	}
}
