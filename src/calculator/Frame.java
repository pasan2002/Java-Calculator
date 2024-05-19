package calculator;

import javax.swing.*;

public class Frame extends JFrame{
	panel calcPanel;
	
	Frame(){
		calcPanel = new panel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Calculator");
		this.setResizable(false);
		this.add(calcPanel);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
