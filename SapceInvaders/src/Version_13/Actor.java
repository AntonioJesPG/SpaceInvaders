package Version_13;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Actor {

	protected int x,y;
	protected int width, height;
	protected String spriteName;
	protected Stage stage;
	protected SpriteCache spriteCache;
	
	public Actor(Stage s) {
		this.stage = s;
		this.spriteCache = s.getSpriteCache();
	}
	
	public void paint(Graphics2D g) {
		g.drawImage( this.spriteCache.getSprite(this.spriteName),this.x,this.y, this.stage);
	}
	
	public int getX() { return this.x; }
	public void setX(int x) { this.x = x; }
	
	public int getY() { return this.y; }
	public void setY(int y) { this.y = y; }
	
	public String getSpriteName() { return this.spriteName; }
	public void setSpriteName(String n) {
		
		this.spriteName = n;
		BufferedImage image = this.spriteCache.getSprite(this.spriteName);
		this.height = image.getHeight();
		this.width = image.getWidth();
	}
	
	public int getHeight() { return this.height; }
	public void setHeight(int i) { this.height = i; }
	
	public int getWidth() { return this.width; }
	public void setWidth(int i) { this.width = i; }
	
	public void act() {}
	
}
