import java.awt.Graphics;

public class Player {

	private int x;
	private int y;
	private boolean live;
	private int score;
	private int velocity;

	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	public Player() {
		x = Game.WIDTH / 2;
		y = Game.HEIGHT / 2;
		live = true;
		score = 0;
		velocity = 5;

		up = down = left = right = false;
	}

	public void update() {
		if(up) {
			 y -= velocity;
		} else if(down) {
			y += velocity;
		}

		if(left) {
			x -= velocity;
		} else if(right) {
			x += velocity;
		}
	}

	public void draw(Graphics g) {
		g.fillRect(x, y, 20, 20);
	}

	public int getVelocity() { return velocity; }
	public void setLeft(boolean b) { left = b; }
	public void setRight(boolean b) { right = b; }
	public void setUp(boolean b) { up = b; }
	public void setDown(boolean b) { down = b; }

}
