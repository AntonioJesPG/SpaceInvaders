package Version_12;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
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
	
	public long usedTime;
	
	public BufferStrategy strategy;
	public BufferedImage buffer;
	
	public Invaders() {
		
		this.sprites = new HashMap();
		
		this.posX = this.WIDTH/2;
		
		this.posY = this.HEIGHT/2;
		
		this.vX = 2;
		
		JFrame ventana = new JFrame("Invaders");
		JPanel panel = (JPanel)ventana.getContentPane();
		
		setBounds(0,0,this.WIDTH,this.HEIGHT);
		
		panel.setPreferredSize(new Dimension(this.WIDTH,this.HEIGHT));
		panel.setLayout(null);
		panel.add(this);
		
		ventana.setBounds(0,0,this.WIDTH,this.HEIGHT);
		ventana.setVisible(true);
		ventana.addWindowListener( new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}
		});
		
		ventana.setResizable(false);
		
		createBufferStrategy(2);
		this.strategy = getBufferStrategy();
		
		requestFocus();
	}
	
	public BufferedImage loadImage(String nombre) {
		URL url=null;
		try {
			url = getClass().getResource(nombre);
			return ImageIO.read(url);
		} catch (Exception e) {
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
		
		Graphics g = this.strategy.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(getSprite("bicho.gif"), this.posX, this.posY, this);
		
		g.setColor(Color.white);
		
		if (usedTime > 0)
			g.drawString(String.valueOf(1000/usedTime) + " fps",0,this.HEIGHT-50);
		else
			g.drawString("--- fps", 0, this.HEIGHT-50);
		
		this.strategy.show();
		
	}
	
	public void paint(Graphics g) {
	}
	public void update(Graphics g) {}
	
	public void updateWorld() {
		this.posX += this.vX;
		if(this.posX < 0 || this.posX > this.WIDTH) this.vX = -this.vX;
	}
	
	public void game() {
		
		this.usedTime = 1000;
		while(isVisible()) {
			long startTime = System.currentTimeMillis();
			updateWorld();
			paintWorld();
			paint(getGraphics());
			this.usedTime = System.currentTimeMillis() - startTime;
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
