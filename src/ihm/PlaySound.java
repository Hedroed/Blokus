package ihm;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class PlaySound {
	
	private Clip clip;
	private FloatControl gainControl;
	
	public PlaySound(String s) {
		
		File file = new File(s);
		
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(file));
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		gainControl.setValue(-20f);
	}
	
	public PlaySound(String s, int volume) {
		this(s);
		gainControl.setValue((float)volume);
	}
	
	public void setVolume(int volume) {
		// System.out.println("vol :"+volume);
		if(volume > -60 && volume <= 5) {
			gainControl.setValue((float)volume);
			this.playContinuously();
		}
		else if(volume == -60) {
			clip.stop();
		}
		else {
			System.out.println("Erreur :: volume incorrect");
		}
	}
	
	public void stop() {
		if(clip.isRunning()) {
			clip.stop();
		}
	}
	
	public void play() {
		
		if(clip != null) {
			stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}
	
	public void playContinuously() {
		if(clip != null && !clip.isRunning()) {
			stop();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		}
	}
	
	public void close() {
		if(clip != null) {
			clip.close();
		}
	}
	
	public boolean isRunning() {
		return clip.isRunning();
	}
}