package Version_22;

public class Monster extends Actor{
	
	protected int vx;
	
	public Monster(Stage stage) {
		
		super(stage);
		setSpriteNames(new String[] {"bicho.gif","bicho0.gif","bicho1.gif", "bicho2.gif"});
		
		setFrameSpeed(35);
		
	}
	
	public void act() {
		
		super.act();
		
		this.x += this.vx;
		
		if ( x < 0 || x > Stage.WIDTH) { this.vx = -this.vx; }
		
	}
	
	public int getVx() { return this.vx; }
	public void setVx(int i) { this.vx = i; }
	
	public void collision(Actor a) {
		
		if( a instanceof Bullet || a instanceof Bomb) {
			
			int newScore = stage.getPlayer().getScore();
			stage.getPlayer().setScore(newScore + 20);
			remove();
			
		}
	}
	
}
