package controlleur;

import java.awt.event.*;
import javax.swing.*;

import ihm.*;

/**
  *Renvoie sur la page souhaitee en fonction du clic sur un bouton
  *@author LAY
  */
public class SelecteurControlleur implements ActionListener{
	
	private SelecteurPartie p;
	
	public SelecteurControlleur(SelecteurPartie p){
		this.p = p;
	}

	public void actionPerformed(ActionEvent e) {
		
		int i = Integer.parseInt(e.getActionCommand());
		p.clicSelecteur(i);
		
	}
	
	
}