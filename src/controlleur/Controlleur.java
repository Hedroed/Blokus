package controlleur;

import java.awt.event.*;
import javax.swing.*;

import ihm.*;

/**
  *Renvoie sur la page souhaitee en fonction du clic sur un bouton
  *@author LAY
  */
public class Controlleur implements ActionListener{
	
	private Fenetre f;
	
	public Controlleur(Fenetre f){
		this.f = f;
	}

	public void actionPerformed(ActionEvent arg0) {
		
		System.out.println(arg0.paramString());
		
		JButton o=(JButton)arg0.getSource();
		
		String s=o.getActionCommand();
		
		if(s.equals(Fenetre.MENU)){
			f.setFenetreActive(Fenetre.MENU);
		}
		if(s.equals(Fenetre.SELECTEUR)){
			f.setFenetreActive(Fenetre.SELECTEUR);
		}
		if(s.equals(Fenetre.PARTIE)){
			f.setFenetreActive(Fenetre.PARTIE);
		}
		if(s.equals(Fenetre.OPTIONS)){
			f.setFenetreActive(Fenetre.OPTIONS);
		}
		if(s.equals(Fenetre.CHARGEUR)){
			f.setFenetreActive(Fenetre.CHARGEUR);
		}
		if(s.equals("quitter")) {
			f.quitter();
		}
		if(s.equals("retourOptions")){
			
			f.nouvelleResolution();
			f.setFenetreActive(Fenetre.MENU);
		}
		
	}
	
	
}