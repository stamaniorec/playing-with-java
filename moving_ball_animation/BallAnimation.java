import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class BallAnimation { 
	private JFrame frame;
	private Ball ball;
	private final int frameWidth = 300;
	private final int frameHeight = 300;
	private JButton button;

	public BallAnimation() { 
		frame = new JFrame("A ball. That moves. Wow.");
		frame.setSize(frameWidth, frameHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	

		ball = new Ball();
		frame.add(BorderLayout.CENTER, ball);

		button = new JButton("Start animation");
		frame.add(BorderLayout.SOUTH, button);
		button.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent event){
				ball.startAnimation();
			}
		});		
	} 

	public static void main(String[] args) {
		new BallAnimation();
	}

}
