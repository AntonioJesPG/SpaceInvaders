package Version_18;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Invaders extends Canvas implements Stage, KeyListener {
	
	private long usedTime;
	
	private BufferStrategy strategy;
	
	private SpriteCache spriteCache;
	private ArrayList actors;
	
	private Player player;
	
	public Invaders() {
		
		this.spriteCache = new SpriteCache();
		
		JFrame ventana = new JFrame("Invaders");
		JPanel panel = (JPanel)ventana.getContentPane();
		
		setBounds(0,0,Stage.WIDTH,Stage.HEIGHT);
		
		panel.setPreferredSize(new Dimension(Stage.WIDTH,Stage.HEIGHT));
		panel.setLayout(null);
		panel.add(this);
		
		ventana.setBounds(0,0,Stage.WIDTH,Stage.HEIGHT);
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
		
		addKeyListener(this);
		
	}
	
	public void initWorld() {
		
		this.actors = new ArrayList();
		
		for (int i = 0; i < 10 ; i++) {
			Monster m = new Monster(this);
			m.setX((int)(Math.random()*Stage.WIDTH));
			m.setY ( i*20 );
			m.setVx( ( int)(Math.random()*20-10) );
			
			this.actors.add(m);
		}
		
		this.player = new Player(this);
		this.player.setX(Stage.WIDTH/2);
		this.player.setY(Stage.HEIGHT - 2*this.player.getHeight());
		
	}
	
	public void addActor(Actor a) {
	
		this.actors.add(a);
		
	}
	
	public void updateWorld() {
		
		int i = 0;
		
		while ( i < this.actors.size()) {
			
			Actor m = (Actor)this.actors.get(i);
			if(m.isMarkedForRemoval()) {
				this.actors.remove(i);
			} else {
				m.act();
				i++;
			}
		
		}
		
		this.player.act();
	}
	
	public void paintWorld() {
		
		Graphics2D g = (Graphics2D)this.strategy.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for ( int i = 0 ; i < actors.size(); i++) {
			Actor m = (Actor)actors.get(i);
			m.paint(g);
		}
		
		player.paint(g);
		
		g.setColor(Color.white);
		
		if (usedTime > 0)
			g.drawString(String.valueOf(1000/usedTime) + " fps",0,Stage.HEIGHT-50);
		else
			g.drawString("--- fps", 0, Stage.HEIGHT-50);
		
		this.strategy.show();
		
	}
	
	public SpriteCache getSpriteCache() { return this.spriteCache; }
	
	public void keyPressed(KeyEvent e) {
		player.keyPressed(e);
	}
	
	public void keyReleased(KeyEvent e) {
		player.keyReleased(e);
	}
	
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void game() {
		
		this.usedTime = 1000;
		initWorld();
		
		while(isVisible()) {
			long startTime = System.currentTimeMillis();
			updateWorld();
			paintWorld();
			this.usedTime = System.currentTimeMillis() - startTime;
		try {
			Thread.sleep(speed);
		} catch (InterruptedException e) {}
		}
	}
	
	public static void main(String[] args) {
		Invaders inv = new Invaders();
		inv.game();
	}
}
