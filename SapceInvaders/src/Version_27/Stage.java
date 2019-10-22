package Version_27;

import java.awt.image.ImageObserver;

public interface Stage extends ImageObserver{
	
	public static final int WIDTH = 640;
	public static final int HEIGHT = 480;
	public static final int speed = 10;
	public static final int PLAY_HEIGHT = 400;
	
	public SpriteCache getSpriteCache();
	public SoundCache getSoundCache();
	public Player getPlayer();
	
	public void addActor(Actor a);
	public void gameOver();
	
}
