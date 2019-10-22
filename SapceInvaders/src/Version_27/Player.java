package Version_27;

import java.awt.event.KeyEvent;

public class Player extends Actor{
	
	public static final int MAX_SHIELDS = 200;
	public static final int MAX_BOMBS = 5;
	
	protected static final int PLAYER_SPEED = 4;
	protected int vx;
	protected int vy;
	private boolean up,down,left,right;
	
	private int clusterBombs;
	private int score;
	private int shields;
	
	public Player(Stage stage) {
		super(stage);
		setSpriteNames (new String[] {"nave.gif"});
		
		this.clusterBombs = 5;
		this.shields = MAX_SHIELDS;
		
		this.score = 0;
		
	}
	
	public void act() {
		
		super.act(); 
		
		this.x += this.vx;
		this.y += this.vy;
		
		if(this.x < 0)
			this.x = 0;
		if(this.x > Stage.WIDTH - getWidth())
			x = Stage.WIDTH - getWidth();
		
		if(this.y < 0)
			this.y = 0;
		if(this.y > Stage.HEIGHT - getHeight())
			this.y = Stage.HEIGHT - getHeight();
		
		
	}
	
	public int getVx() { return this.vx; }
	public void setVx( int i) { this.vx = i; }
	
	public int getVy() { return this.vy; }
	public void setVy( int i) { this.vy = i; }
	
	public int getScore() { return this.score; }
	public void setScore( int i) { this.score = i; }
	public void addScore(int i) { this.score += i; }
	
	public int getShields() { return this.shields; }
	public void setShields(int i) { this.shields = i; }
	public void addShields(int i) { 
		
		this.shields += i; 
		if (shields > MAX_SHIELDS) shields = MAX_SHIELDS;
		
	}
	
	public int getClusterBombs() { return this.clusterBombs; }
	
	protected void updateSpeed() {
		
		this.vx=0; this.vy=0;
		if(this.down) this.vy = PLAYER_SPEED;
		if(this.up) this.vy = -PLAYER_SPEED;
		if(this.left) this.vx = -PLAYER_SPEED;
		if(this.right) this.vx = PLAYER_SPEED;
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
			case KeyEvent.VK_DOWN : this.down = false;break;
			case KeyEvent.VK_UP : this.up = false;break;
			case KeyEvent.VK_LEFT : this.left = false;break;
			case KeyEvent.VK_RIGHT : this.right = false;break;
		
		}
		
		updateSpeed();
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		
			case KeyEvent.VK_DOWN : this.down = true;break;
			case KeyEvent.VK_UP : this.up = true;break;
			case KeyEvent.VK_LEFT : this.left = true;break;
			case KeyEvent.VK_RIGHT : this.right = true;break;
		
			case KeyEvent.VK_SPACE : fire(); break;
			case KeyEvent.VK_B : fireCluster(); break;
			
		}
		
		updateSpeed();
		
	}
	
	public void fire() {
		
		Bullet b = new Bullet(stage);
		b.setX(x);
		b.setY(y - b.getHeight());
		stage.addActor(b);
		
		stage.getSoundCache().playSound("missile.wav");
		
	}
	
	public void fireCluster() {
		
		if(clusterBombs == 0) 
			return;
		
		clusterBombs --;
		stage.addActor( new Bomb(stage, Bomb.UP_LEFT, x,y));
		stage.addActor( new Bomb(stage, Bomb.UP, x,y));
		stage.addActor( new Bomb(stage, Bomb.UP_RIGHT, x,y));
		stage.addActor( new Bomb(stage, Bomb.LEFT, x,y));
		stage.addActor( new Bomb(stage, Bomb.RIGHT, x,y));
		stage.addActor( new Bomb(stage, Bomb.DOWN_LEFT, x,y));
		stage.addActor( new Bomb(stage, Bomb.DOWN, x,y));
		stage.addActor( new Bomb(stage, Bomb.DOWN_RIGHT, x,y));
		
	}
	
	public void collision(Actor a) {
		if( a instanceof Monster) {
			
			a.remove();
			addScore(40);
			addShields(-40);	
			
		}
		
		if ( a instanceof Laser) {
			
			a.remove();
			addShields(-10);
			
		}
		
		if(getShields() <  0)
			stage.gameOver();
	}
	
}
