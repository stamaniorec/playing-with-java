import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

public class Game extends Canvas {

	public static int WIDTH = 300;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int SCALE = 3;

	private Thread thread;
	private JFrame frame;
	private boolean running;

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

	}

	private void render() {
		BufferStrategy buffStrategy = this.getBufferStrategy();
		if(buffStrategy == null) {
			createBufferStrategy(3); // triple buffering is the most efficient
			return;
		}
		Graphics g = buffStrategy.getDrawGraphics(); // linking the Graphics object with the buffer
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.dispose(); // still works without it, but it's a good thing to have
		// the graphics have been buffered, so no need to keep them anymore
		buffStrategy.show(); // clearing the buffer, showing it to the screen
		// without it the screen is going to remain white
	}

	Game() {
		running = false;

		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame("Game title");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH * SCALE, HEIGHT * SCALE); 
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
	}

	public synchronized void start() {
		if(!running) {		
			running = true;
			frame.setVisible(true);
			thread = new Thread(new GameLoop());
			thread.start();
		}
	}

	public synchronized void stop() {
		if(running) {		
			running = false;
			frame.setVisible(false);
			try {
				thread.join();
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
