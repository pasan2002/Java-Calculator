package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class panel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 500;
    static final int SCREEN_HEIGHT = 500;
    JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, addition, subs, divide, multi, dot, equal, clear, delete;
    static JTextField display;
    String s0, s1, s2;
    char operator;

    panel() {
        s0 = s1 = s2 = ""; 
        
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setLayout(new BorderLayout());
        
        // Display
        display = new JTextField(20);
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 30));
        display.setPreferredSize(new Dimension(SCREEN_WIDTH, 100));
        this.add(display, BorderLayout.NORTH);
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 10, 10));
        
        // Initialize buttons
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        addition = new JButton("+");
        subs = new JButton("-");
        divide = new JButton("/");
        multi = new JButton("*");
        dot = new JButton(".");
        equal = new JButton("=");
        clear = new JButton("C");
        delete = new JButton("DEL");

        // Add buttons to panel with smaller size
        Dimension buttonSize = new Dimension(50, 50);
        JButton[] buttons = {b7, b8, b9, divide, b4, b5, b6, multi, b1, b2, b3, subs, b0, dot, equal, addition, clear, delete};
        for (JButton button : buttons) {
            button.setPreferredSize(buttonSize);
            button.addActionListener(this);
            button.setFont(new Font("Arial", Font.BOLD, 20)); 
            buttonPanel.add(button);
        }

        this.add(buttonPanel, BorderLayout.CENTER);
        this.setBackground(Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        
        if((s.charAt(0) >= '0' && s.charAt(0) <= '9') || s.charAt(0) == '.') {
            if(!s1.equals("")) {
                s2 = s2 + s;
            } else {
                s0 = s0 + s;
            }
            display.setText(s0 + s1 + s2);
        } else if(s.charAt(0) == 'C') {
            s0 = s1 = s2 = "";
            display.setText("");
        } else if (s.equals("DEL")) {
            String currentText = display.getText();
            if(currentText.length() > 0) {
                display.setText(currentText.substring(0, currentText.length() - 1));
                if (!s2.equals("")) {
                    s2 = s2.substring(0, s2.length() - 1);
                } else if (!s1.equals("")) {
                    s1 = "";
                } else if (!s0.equals("")) {
                    s0 = s0.substring(0, s0.length() - 1);
                }
            }
        } else if (s.equals("=")) {
            s2 = display.getText().substring(s0.length() + s1.length());
            double result = 0;
            if (operator == '+') result = (Double.parseDouble(s0) + Double.parseDouble(s2));
            else if (operator == '-') result = (Double.parseDouble(s0) - Double.parseDouble(s2));
            else if (operator == '*') result = (Double.parseDouble(s0) * Double.parseDouble(s2));
            else if (operator == '/') result = (Double.parseDouble(s0) / Double.parseDouble(s2));
            display.setText(String.valueOf(result));
            s0 = s1 = s2 = "";
        } else {
            if (!display.getText().equals("")) {
                s1 = s;
                operator = s.charAt(0);
                display.setText(s0 + s1);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        panel calcPanel = new panel();
        frame.add(calcPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
