package Version_10;


import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Invaders extends Canvas {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int speed = 10;
	
	
	public HashMap sprites;
	public int posX, posY, vX;
	
	public BufferedImage buffer;
	
	public Invaders() {
		
		this.sprites = new HashMap();
		
		this.posX = this.WIDTH/2;
		
		this.posY = this.HEIGHT/2;
		
		this.vX = 2;
		this.buffer = new  BufferedImage(WIDTH,HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		JFrame ventana = new JFrame("Invaders");
		JPanel panel = (JPanel)ventana.getContentPane();
		
		setBounds(0,0,WIDTH,HEIGHT);
		
		panel.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		panel.setLayout(null);
		panel.add(this);
		
		ventana.setBounds(0,0,WIDTH,HEIGHT);
		ventana.setVisible(true);
		ventana.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}
		});
		
		ventana.setIgnoreRepaint(true);
		ventana.setResizable(false);
		
	}
	
	public BufferedImage loadImage(String nombre) {
		URL url=null;
		try {
			url = getClass().getResource(nombre);
			return ImageIO.read(url);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("No se pudo cargar la imagen " + nombre +" de "+url);
			System.out.println("El error fue : "+e.getClass().getName()+" "+e.getMessage());
			System.exit(0);
			return null;
		}
	}
	
	public BufferedImage getSprite(String nombre){
		BufferedImage img = (BufferedImage)this.sprites.get(nombre);
		
		if(img == null) {
			img = loadImage("../res/" + nombre);
			this.sprites.put(nombre, img);
		}
		
		return img;
		
	}
	
	public void paintWorld() {
		Graphics g = this.buffer.getGraphics();
		g.setColor(getBackground());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(getSprite("bicho.gif"), this.posX, this.posY, this);
	}
	
	public void paint(Graphics g) {
		g.drawImage(this.buffer,0,0,this);
	}
	public void update(Graphics g) {}
	
	public void updateWorld() {
		this.posX += this.vX;
		if(this.posX < 0 || this.posX > this.WIDTH) this.vX = -this.vX;
	}
	
	public void game() {
		while(isVisible()) {
			updateWorld();
			paintWorld();
			paint(getGraphics());
		}
		
		try {
			Thread.sleep(this.speed);
		} catch (InterruptedException e) {}
	}
	
	public static void main(String[] args) {
		Invaders inv = new Invaders();
		inv.game();
	}
}
