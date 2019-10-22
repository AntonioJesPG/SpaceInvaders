package Version_27;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class SoundCache extends ResourceCache{
	
	protected Object loadResource(URL url) {
		return Applet.newAudioClip(url);
	}
	
	public AudioClip getAudioClip(String name) {
		return (AudioClip)getResource(name);
	}
	
	public void playSound(final String name) {
		getAudioClip(name).play();
	}
	
	public void loopSound(final String name) {
		getAudioClip(name).loop();
	}
	
}
