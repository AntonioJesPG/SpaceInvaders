package Version_28;

public class Laser extends Actor{
	
	protected static final int BULLET_SPEED = 3;

	public Laser(Stage stage) {
		
		super(stage);
		setSpriteNames( new String[] {"disparo0.gif","disparo1.gif","disparo2.gif"});
		setFrameSpeed(10);
		
	}
	
	public void act() {
		
		super.act();
		this.y += BULLET_SPEED;
		
		if(y > Stage.PLAY_HEIGHT)
			remove();
	}
	
}
