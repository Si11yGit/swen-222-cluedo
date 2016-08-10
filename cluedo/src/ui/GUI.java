package ui;

import javax.swing.JButton;
import javax.swing.JFrame;

import board.Board;

public class GUI {
	JFrame frame;
	JButton b1, b2, b3;

	public GUI() {
		frame = new JFrame();
		b1 = new JButton("");

	}

	public static void main(String[] args) {
        new GUI();
    }
}
