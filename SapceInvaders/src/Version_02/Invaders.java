package Version_02;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

public class Invaders {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public Invaders() {
		
		JFrame ventana = new JFrame("Invaders");
		ventana.setBounds(0,0,WIDTH,HEIGHT);
		ventana.setVisible(true);
		
		
		ventana.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	public static void main(String[] args) {
		Invaders inv = new Invaders();
		
	}
	
}
