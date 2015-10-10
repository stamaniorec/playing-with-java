import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.awt.*;

public class GUI implements ActionListener {
	private JFrame frame;
	private JButton buttonOne;
	private JButton buttonTwo;

	public GUI() {
		frame = new JFrame("My GUI!");
		frame.setVisible(true);
		frame.setSize(640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout(new FlowLayout());
	
		buttonOne = new JButton("Button 1");
		frame.add(buttonOne);
		buttonOne.addActionListener(this);

		buttonTwo = new JButton("Button 2");
		frame.add(BorderLayout.SOUTH, buttonTwo);
		buttonTwo.addActionListener(this);
	}

	//private class EventHandler implements ActionListener { 
		
	//} 

	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == buttonOne){
			JOptionPane.showMessageDialog(null, "You, sir, just clicked on button 1. :)");
		}
		else if(event.getSource() == buttonTwo){
			JOptionPane.showMessageDialog(null, "You, sir, just went clicky-clicky on button 2.");
		}
	}

	public static void main(String[] args){
		GUI gui = new GUI();	
	}

} 
