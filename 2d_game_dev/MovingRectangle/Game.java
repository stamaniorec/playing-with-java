import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.image.DataBufferInt;
import javax.swing.JFrame;

public class Game extends Canvas {

	public static int WIDTH = 500;
	public static int HEIGHT = 500;

	private boolean running;
	private Thread gameThread;
	private Timer timer;
	private Player player;
	
	private Graphics g;
	private BufferedImage blackScreen;

	public Game() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		running = false;
	}

	private class GameLoop implements Runnable {
		public void run() {
			timer = new Timer(60);
			while(running) {
				if(timer.update()) {
					update();
					render();
				}
			}
		}
	}

	private void update() {
		player.update();
	}

	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();

		g.drawImage(blackScreen, 0, 0, null);

		g.setColor(Color.RED);
		g.drawString("Hello World!", 100, 100);

		player.draw(g);

		g.dispose();
		bs.show();
	}

	public synchronized void start() {
		if(!running) {
			running = true;

			blackScreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			int[] pixels = ((DataBufferInt) blackScreen.getRaster().getDataBuffer()).getData();
			for(int i : pixels) {
				i = 0x000000;
			}

			setFocusable(true);
			requestFocus();

			player = new Player();
			addKeyListener(new PlayerKeyListener(player));

			gameThread = new Thread(new GameLoop());
			gameThread.start();
		}
	}

	public synchronized void stop() {
		if(running) {
			try {
				gameThread.join();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Spaceshooter-ish game");
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setFocusable(true);
		frame.requestFocus();

		Game game = new Game();
		frame.add(game);
		frame.pack();

		game.start();
	}

}
