package Version_01;

import javax.swing.JFrame;

public class Invaders {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	public Invaders() {
		
		JFrame ventana = new JFrame("Invaders");
		ventana.setBounds(0,0,WIDTH,HEIGHT);
		ventana.setVisible(true);
		
	}
	
	public static void main(String[] args) {
		Invaders inv = new Invaders();
		
	}
	
}
