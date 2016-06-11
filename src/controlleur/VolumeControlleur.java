package controlleur;

import ihm.*;
import java.awt.event.*;
import javax.swing.*;

public class VolumeControlleur extends MouseAdapter{
	
	private FenetreOptions p;
	
	public VolumeControlleur(FenetreOptions pan){
		p=pan;
	}
	
	public void mousePressed(MouseEvent e){
		if(e.getX() >= p.getCoordBar() && e.getX() <= p.getCoordBar()+p.getTailleBar()){
			if(e.getY() >= (Fenetre.HEIGHT*0.360) && e.getY() <= (int)(Fenetre.HEIGHT*0.430)){
				p.setMusique((e.getX()-20-p.getCoordBar())/(p.getTailleBar()/100));
				System.out.println("Volume musique: "+p.getMusique());
			}
			if(e.getY() >= (Fenetre.HEIGHT*0.595) && e.getY() <= (int)(Fenetre.HEIGHT*0.655)){
				p.setSon((e.getX()-20-p.getCoordBar())/(p.getTailleBar()/100));
				System.out.println("Volume son :"+p.getSon());
			}
		}
		// System.out.println((e.getX()-p.getCoordBar())/(p.getTailleBar()/100));
		
		
	}

	public void mouseDragged(MouseEvent e){
		mousePressed(e);
	}
}