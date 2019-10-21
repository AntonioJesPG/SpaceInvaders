package Version_16;

public class Player extends Actor{
	protected int vx;
	protected int vy;
	
	public Player(Stage stage) {
		super(stage);
		setSpriteNames (new String[] {"nave.gif"});
	}
	
	public void act() {
		
		super.act(); 
		
		this.x += this.vx;
		this.y += this.vy;
		
		if( this.x < 0 || this.x > Stage.WIDTH)
			this.vx = - this.vx;
		if( this.y < 0 || this.y > Stage.HEIGHT)
			this.vy = - this.vy;
	}
	
	public int getVx() { return this.vx; }
	public void setVx( int i) { this.vx = i; }
	
	public int getVy() { return this.vy; }
	public void setVy( int i) { this.vy = i; }
	
}
