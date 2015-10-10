import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.Color;

public class Player {

	private double x = 400;
	private double y = 100;
	private double dx;
	private double dy;

	private double moveSpeed;
	private double maxSpeed;
	private double maxFallingSpeed;
	private double stopSpeed;
	private double gravity;
	private double jumpStart;

	private int width;
	private int height;

	private boolean left;
	private boolean right;
	private boolean jumping;
	private boolean falling;

	private BufferedImage image;
	private int[] pixels;

	private TileMap map;

	private boolean topLeft;
	private boolean topRight;
	private boolean bottomLeft;
	private boolean bottomRight;

	public Player(TileMap map) {
		x = 0;
		y = 0;

		this.map = map;

		moveSpeed = 0.6;
		maxSpeed = 4.2;
		maxFallingSpeed = 12;
		stopSpeed = 0.3;
		jumpStart = -11.0;
		gravity = 0.64;

		width = 20;
		height = 20;

		left = right = jumping = false;
		falling = true;

		topLeft = topRight = bottomLeft = bottomRight = true;

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		for(int i = 0; i < pixels.length; ++i) {
			pixels[i] = 0xFF0000;
		}
	}

	public void update() {
		if(left) {
			dx -= moveSpeed;
			if(dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if(right) {
			dx += moveSpeed;
			if(dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		else
		{
			if(dx > 0) {
				dx -= stopSpeed;
				if(dx < 0) {
					dx = 0;
				}
			} 
			else if(dx < 0) {
				dx += stopSpeed;
				if(dx > 0) {
					dx = 0;
				}
			}
		}

		if(jumping) {
			dy = jumpStart;
			falling = true;
			jumping = false;
		}

		if(falling) {
			dy += gravity;
			if(dy > maxFallingSpeed) {
				dy = maxFallingSpeed;
			}
		} else {
			dy = 0;
		}

		int currCol = map.getColTile((int)x);
		int currRow = map.getRowTile((int)y);

		double toX = x + dx;
		double toY = y + dy;

		double tempX = x;
		double tempY = y;

		calculateCorners(x, toY);

		if(dy < 0) {
			if(topLeft || topRight) {
				dy = 0;
				tempY = currRow * map.getTileSize() + height / 2; // without the final thing you won't hit the "wall"
			} else {
				tempY += dy;
			}
		}
		if(dy > 0) {
			if(bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				tempY  = (currRow + 1) * map.getTileSize() - height / 2;
			} else {
				tempY += dy;
			}
		}

		calculateCorners(toX, y);

		if(dx < 0) {
			if(topLeft || bottomLeft) {
				dx = 0;
				tempX = currCol * map.getTileSize() + width / 2;
			} else {
				tempX += dx;
			}
		}
		else if(dx > 0) {
			if(topRight || bottomRight) {
				dx = 0;
				tempX = (currCol + 1) * map.getTileSize() - width / 2;
			} else {
				tempX += dx;
			}
		}

		if(!falling) {
			calculateCorners(x, y+1);
			if(!bottomLeft && !bottomRight) {
				falling = true;
			}
		}

		x = tempX;
		y = tempY;
	}

	private void calculateCorners(double x, double y) {
		int leftTile = map.getColTile((int) (x - width / 2));
		int rightTile = map.getColTile((int) (x + width / 2) - 1);
		int topTile = map.getRowTile((int) (y - height / 2));
		int bottomTile = map.getRowTile((int) (y + height / 2) - 1);

		topLeft = map.isBlocked(topTile, leftTile);
		topRight = map.isBlocked(topTile, rightTile);
		bottomLeft = map.isBlocked(bottomTile, leftTile);
		bottomRight = map.isBlocked(bottomTile, rightTile);
	}

	public void draw(Graphics g) {
		int tx = map.getX();
		int ty = map.getY();

		g.drawImage(image, (int) (tx + x - width / 2), (int) (tx + y - height / 2), 
			width, height, null);
		// a plain rectangle can also work, but it's a little bit slower
	}

	public void setLeft(boolean b) { left = b; } 	
	public void setRight(boolean b) { right = b; }
	public void setJumping() { if(!falling) jumping = true; } // prevents double jumps etc.

	public double getMoveSpeed() { return moveSpeed; }
	public double getX() { return x; }
	public double getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
}
