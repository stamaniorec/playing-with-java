import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GameStarter {

	private class WaitingForExit implements Runnable, KeyListener {

		private boolean running;

		public WaitingForExit() {
			running = true;
			//addKeyListener(this);
		}

		public void run() {
			while(true) {
				if(!running) {
					System.out.println("Quitting...");
					System.exit(0);
				}
			}
		}

		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == KeyEvent.VK_Q) {
				running = false;
			}
		}

		public void keyTyped(KeyEvent e) {}
		public void keyReleased(KeyEvent e) {}
	}

	public GameStarter() {
		Game game = new Game();

		Thread quit = new Thread(new WaitingForExit());
		quit.start();		

		game.start();
	}

	public static void main(String[] args) {
		GameStarter game = new GameStarter();
	}
}
