package Version_13;

public class Monster extends Actor{
	
	protected int vx;
	
	public Monster(Stage stage) {
		
		super(stage);
		setSpriteName("bicho.gif");
		
	}
	
	public void act() {
		
		this.x += this.vx;
		
		if ( x < 0 || x > Stage.WIDTH) { this.vx = -this.vx; }
		
	}
	
	public int getVx() { return this.vx; }
	public void setVx(int i) { this.vx = i; }
	
}
