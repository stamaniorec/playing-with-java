import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas {

	public static int WIDTH = 400;
	public static int HEIGHT = 400;

	private Thread gameThread;
	private JFrame frame;
	private boolean running;

	private TileMap map;
	private Player player;
	private Camera camera;

	private BufferedImage blackScreen;
	private int[] blackScreenPixels;

	private class GameLoop implements Runnable {
		public void run() {
			Timer timer = new Timer();
			
			while(running) {
				if(timer.update()) {
					update();
					render();
				}
			}
		}
	}

	private void update() {
		map.update();
		player.update(camera);
		camera.update(player);
	}

	private void render() {
		BufferStrategy buffStrategy = this.getBufferStrategy();
		if(buffStrategy == null) {
			createBufferStrategy(3); // triple buffering is the most efficient
			return;
		}
		Graphics g = buffStrategy.getDrawGraphics(); // linking the Graphics object with the buffer
		Graphics2D g2d = (Graphics2D) g;

		g.drawImage(blackScreen, 0, 0, WIDTH, HEIGHT, null);
		// using a rectangle instead of a BufferedImage results in very very very slow rendering... been there done that
		g2d.translate(-camera.getX(), -camera.getY());
		map.draw(g);
		player.draw(g);
		g2d.translate(camera.getX(), camera.getY());
		g.dispose(); // still works without it, but it's a good thing to have
		// the graphics have been buffered, so no need to keep them anymore
		buffStrategy.show(); // clearing the buffer, showing it to the screen
		// without it the screen is going to remain white
	}

	Game() {
		running = false;

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true); // without these two you have to explicitly click
		requestFocus(); // on the screen in order for keyboard input to work etc.

		frame = new JFrame("Game title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT); 
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

	public synchronized void start() {
		if(!running) {		
			running = true;
			frame.setVisible(true);

			map = new TileMap("map.txt", 32);
			player = new Player(map);
			camera = new Camera(map);

			addKeyListener(new KeyInputManager(player));

			blackScreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			blackScreenPixels = ((DataBufferInt) blackScreen.getRaster().getDataBuffer()).getData();

			for(int i = 0; i < blackScreenPixels.length; ++i) {
				blackScreenPixels[i] = 0x000000;
			}

			gameThread = new Thread(new GameLoop());
			gameThread.start();
		}
	}

	public synchronized void stop() {
		if(running) {		
			running = false;
			frame.setVisible(false);
			try {
				gameThread.join();
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String [] args) {
		Game game = new Game();
		game.frame.add(game);
		game.start();
	}
}
