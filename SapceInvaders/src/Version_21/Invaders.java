package Version_21;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
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
		this.player.setY(Stage.PLAY_HEIGHT - 2*this.player.getHeight());
		
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
	
	public void checkCollisions() {
		
		Rectangle playerBounds = player.getBounds();
		
		for(int i = 0; i < actors.size(); i++) {
			
			Actor a1 = (Actor)actors.get(i);
			Rectangle r1 = a1.getBounds();
			
			if( r1.intersects(playerBounds)) {
				player.collision(a1);
				a1.collision(player);
			}
			
			for(int j = 0; j < actors.size(); j++) {
				
				Actor a2 = (Actor)actors.get(j);
				Rectangle r2 = a2.getBounds();
				
				if(r1.contains(r2)) {
					a1.collision(a2);
					a2.collision(a1);
				}
				
			}
			
		}
		
	}
	
	public void paintShields(Graphics2D g) {
		
		g.setPaint(Color.red);
		g.fillRect(280, Stage.PLAY_HEIGHT, Player.MAX_SHIELDS, 30);
		
		g.setPaint(Color.blue);
		g.fillRect(280+Player.MAX_SHIELDS - player.getShields(), Stage.PLAY_HEIGHT, player.getShields(), 30);
		g.setFont(new Font("Arial", Font.BOLD, 20));
		
		g.setPaint(Color.green);
		g.drawString("Shields", 170, Stage.PLAY_HEIGHT + 20);
		
	}
	
	public void paintScore(Graphics2D g) {
		
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setPaint(Color.green);
		g.drawString("Score", 20, Stage.PLAY_HEIGHT + 20);
		
		g.setPaint(Color.red);
		g.drawString(player.getScore()+ "" , 100, Stage.PLAY_HEIGHT + 20);
		
	}
	
	public void paintAmmo(Graphics2D g) {
		
		int xBase = 280+Player.MAX_SHIELDS + 10;
		
		for( int i = 0; i < player.getClusterBombs(); i++) {
			
			BufferedImage bomb = spriteCache.getSprite("bombUL.gif");
			g.drawImage( bomb, xBase+(i*bomb.getWidth()), Stage.PLAY_HEIGHT, this);
			
		}
		
	}
	
	public void paintFps(Graphics2D g) {
		
		g.setFont(new Font("Arial", Font.BOLD, 12));
		g.setColor(Color.white);
		
		if(usedTime > 0) 
			g.drawString(String.valueOf(1000/usedTime) + " fps", Stage.WIDTH-50, Stage.PLAY_HEIGHT);
		else
			g.drawString("--- fps", Stage.WIDTH-50, Stage.PLAY_HEIGHT);
		
	}
	
	public void paintStatus(Graphics2D g) {
		
		paintScore(g);
		paintShields(g);
		paintAmmo(g);
		paintFps(g);
		
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
		
		paintStatus(g);
		
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
			
			checkCollisions();
			
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
