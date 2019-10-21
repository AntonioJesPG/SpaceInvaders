package Version_16;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Actor {

	protected int x,y;
	protected int width, height;
	protected String[] spriteNames;
	protected int currentFrame;
	
	protected int frameSpeed;
	protected int t;
	
	protected Stage stage;
	protected SpriteCache spriteCache;
	
	public Actor(Stage s) {
		this.stage = s;
		this.spriteCache = s.getSpriteCache();
		this.currentFrame = 0;
		this.frameSpeed = 1;
		this.t=0;
	}
	
	public void paint(Graphics2D g) {
		g.drawImage( this.spriteCache.getSprite(this.spriteNames[currentFrame]),this.x,this.y, this.stage);
	}
	
	public int getX() { return this.x; }
	public void setX(int x) { this.x = x; }
	
	public int getY() { return this.y; }
	public void setY(int y) { this.y = y; }
	
	public int getFrameSpeed() { return this.frameSpeed; }
	public void setFrameSpeed( int i) { this.frameSpeed = i; }
	
	public void setSpriteNames(String [] names) {
		
		this.spriteNames = names;
		this.height = 0;
		this.width = 0;
		for( int i = 0; i < names.length; i++) {
		BufferedImage image = this.spriteCache.getSprite(this.spriteNames[i]);
		this.height = Math.max(height, image.getHeight());
		this.width = Math.max(width, image.getWidth());
		}
	}
	
	public int getHeight() { return this.height; }
	public void setHeight(int i) { this.height = i; }
	
	public int getWidth() { return this.width; }
	public void setWidth(int i) { this.width = i; }
	
	public void act() {
		
		this.t++;
		
		if( this.t % this.frameSpeed == 0) {
			this.t = 0;
			this.currentFrame = ( this.currentFrame + 1 ) % this.spriteNames.length;
		}
		
	}
	
}
