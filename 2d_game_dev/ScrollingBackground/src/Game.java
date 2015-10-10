import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class Game extends Canvas {

	private static final long serialVersionUID = 1L;

	public final static int WIDTH = 1200;
	public final static int HEIGHT = 500;
	
	private boolean isRunning;
	private Thread gameThread;
	
	private Timer timer;
	
	private BufferedImage renderer;
	private Background background;
	private Camera camera;
	
	private class GameLoop implements Runnable {
		public void run() {
			timer = new Timer();
			
			while(isRunning) {
				if(timer.update()) {
					update();
					render();	
				}
			}
		}
		
	}
	
	private void update() {
		
		camera.update();

	}
	
	private void render() {
		BufferStrategy bufferStrategy = getBufferStrategy();
		if(bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bufferStrategy.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g.drawImage(renderer, 0, 0, WIDTH, HEIGHT,  null);
		
		g2d.translate(-camera.getX(), 0);
		background.draw(g);
		g2d.translate(camera.getX(), 0);
		
		g.dispose();
		bufferStrategy.show();
	}
	
	public void start() {
		isRunning = true;
		
		renderer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		Graphics g = renderer.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		setSize(WIDTH, HEIGHT);
		
		camera = new Camera();
		
		// throws exceptions if not full path...
		background = new Background("../res/388949.jpg", camera);
		
		gameThread = new Thread(new GameLoop(), "Game Loop");
		gameThread.start();
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Scrolling Background");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Game game = new Game();
		frame.add(game);
		frame.setSize(Game.WIDTH, Game.HEIGHT);
		
		game.start();
	}
}
