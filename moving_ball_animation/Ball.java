import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Ball extends JPanel implements ActionListener { 
	private int x; 
	private int y; 
	private final int ballRadius = 40;
	Timer timer = new Timer(3, this);

	public Ball() { 
		x = 0;
		y = 0;
		//super.setBounds(x, y, 100, 100);
	}
	
	public void startAnimation() {
		timer.start();
	}

	public void paintComponent(Graphics g) { 
		super.paintComponent(g);
		g.setColor(Color.WHITE);		
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		g.setColor(Color.GREEN);
		g.fillOval(x, y, ballRadius, ballRadius);
	}

	public void actionPerformed(ActionEvent e) {
		if(y < 205) {
			x++;
			y++;
			repaint();
		} else {
			JOptionPane.showMessageDialog(null, "Done!");
			System.exit(0);
		}
		
		
	}

} 
