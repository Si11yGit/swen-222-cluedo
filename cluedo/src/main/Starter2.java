package main;

import java.awt.EventQueue;

import ui.Frame;
/**
 * Starter for GUI intialization
 * @author Ben
 *
 */
public class Starter2 {
	static boolean gameFinished = false;
	static private Frame frame;
	public static void main(String[] args) {
				EventQueue.invokeLater(new Runnable(){
					public void run(){
						try{
							frame = new Frame();
							frame.setVisible(true);
						}catch(Exception e){
							
						}
					}
				});
	}
}

