package ihm;

import javax.sound.sampled.*;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;

/**
*Classe qui permet de jouer des pistes audio
*/
public class PlaySound {
	
	private Clip clip;
	private FloatControl gainControl;
	
	/**
	*Constructeur de la classe PlaySound
	*@Param in l'InputStream du fichier son
	*/
	public PlaySound(InputStream in) throws IOException{
		
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(in));
		}
		catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		
		
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		
		// System.out.println("Volume entre "+gainControl.getMinimum()+" et "+gainControl.getMaximum());
		
	}
	
	/**
	*Constructeur de la classe PlaySound avec precision du volume
	*@Param in l'InputStream du fichier son
	*@Param volume, l'intensite sonore a laquelle va etre jouee la piste
	*/
	public PlaySound(InputStream in, float volume) throws IOException{
		this(in);
		setVolume(volume);
	}
	
	/**
	*Constructeur de la classe PlaySound
	*@Param url l'URL du fichier son
	*/
	public PlaySound(URL url) throws IOException{
		
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(url));
		}
		catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		catch(LineUnavailableException e) {
			e.printStackTrace();
		}
		
		
		gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		
		// System.out.println("Volume entre "+gainControl.getMinimum()+" et "+gainControl.getMaximum());
		
	}
	
	/**
	*Constructeur de la classe PlaySound avec precision du volume
	*@Param url l'URL du fichier son
	*@Param volume, l'intensite sonore a laquelle va etre jouee la piste
	*/
	public PlaySound(URL url, float volume) throws IOException{
		this(url);
		setVolume(volume);
	}
	
	/**
	*Modifie le volume de la piste audio
	*@Param volume, le nouveau volume
	*/
	public void setVolume(float volume) {
		if(volume > gainControl.getMinimum() && volume <= gainControl.getMaximum()) {
			gainControl.setValue(volume);
		}
		else {
			throw new IllegalArgumentException("Erreur :: volume incorrect");
		}
	}
	
	
	/**
	*Stoppe la diffusion de la piste audio et la reinitialise
	*/
	public void stop() {
		
		clip.stop();
		clip.setFramePosition(0);
		
	}
	
	/**
	*Met la diffusion de la piste audio en pause
	*/
	public void pause() {
		if(clip.isRunning()) {
			clip.stop();
		}
	}
	
	/**
	*Joue la piste audio
	*/
	public void play() {
		
		if(!clip.isRunning()) {
			clip.start();
		}
	}
	
	/**
	*Joue la piste audio en boucle
	*/
	public void playContinuously() {
		if(!clip.isRunning()) {
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