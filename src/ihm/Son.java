package ihm;

import java.util.HashMap;
import java.io.*;
import java.net.URL;

public class Son {

	private float volume;
	
	private HashMap<String,PlaySound> sons;
	
	private boolean ambiance;
	
	public Son() {
		
		sons = new HashMap<String,PlaySound>();
		
		volume = -10f;
		
		ambiance = false;
	}
	
	public Son(boolean ambiance) {
		
		sons = new HashMap<String,PlaySound>();
		
		volume = -10f;
		
		this.ambiance = ambiance;
	}
	
	/**
	 * 
	 * @param nom
	 */
	public void play(String nom) {
		PlaySound son = sons.get(nom);
		if(son != null && !son.isRunning()) {
			if(ambiance) {
				stopAll();
				son.playContinuously();
			}
			else {
				son.stop();
				son.play();
			}
			
		}
	}
	
	public void stopAll() {
		for(PlaySound s : sons.values()) {
			s.stop();
		}
	}
	
	/**
	 * 
	 * @param nom
	 */
	public void stop(String nom) {
		PlaySound son = sons.get(nom);
		if(son != null) {
			son.stop();
		}
	}
	
	/**
	 * 
	 * @param nom
	 */
	public void pause(String nom) {
		PlaySound son = sons.get(nom);
		if(son != null) {
			son.pause();
		}
	}
	
	/**
	 * 
	 * @param v
	 */
	public void setVolume(float f) {
		this.volume = f;
		
		for(PlaySound s : sons.values()) {
			
			s.setVolume(f);
			
		}
	}

	/**
	 * 
	 * @param nom
	 */
	public void add(String nom, String path) {
		
		try {
			URL url = ClassLoader.getSystemResource(path);
			
			sons.put(nom,new PlaySound(url,volume));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}