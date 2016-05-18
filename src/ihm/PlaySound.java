package ihm;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


/**
*Classe qui permet de jouer des pistes audio
*/
public class PlaySound {
	
	private Clip clip;
	private FloatControl gainControl;
	
	/**
	*Constructeur de la classe PlaySound
	*@Param s, le nom de la piste audio
	*/
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
	
	/**
	*Constructeur de la classe PlaySound avec precision du volume
	*@Param s, le nom de la piste audio
	*@Param volume, l'intensite sonore a laquelle va etre jouee la piste
	*/
	public PlaySound(String s, int volume) {
		this(s);
		gainControl.setValue((float)volume);
	}
	
	/**
	*Modifie le volume de la piste audio
	*@Param volume, le nouveau volume
	*/
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
	
	
	/**
	*Stoppe la diffusion de la piste audio
	*/
	public void stop() {
		if(clip.isRunning()) {
			clip.stop();
		}
	}
	
	/**
	*Joue la piste audio
	*/
	public void play() {
		
		if(clip != null) {
			stop();
			clip.setFramePosition(0);
			clip.start();
		}
	}
	
	/**
	*Joue la piste audio en boucle
	*/
	public void playContinuously() {
		if(clip != null && !clip.isRunning()) {
			stop();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();
		}
	}
	
	/**
	*ferme le lecteur du clip
	*/
	public void close() {
		if(clip != null) {
			clip.close();
		}
	}
	
	/**
	*Retourne vrai si la piste est en train d'etre jouee, faux sinon
	*/
	public boolean isRunning() {
		return clip.isRunning();
	}
}