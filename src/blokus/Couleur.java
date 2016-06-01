package blokus;

/**
  * Definie une couleur graphique, elle permet aussi de determiner la couleur d'un joueur ou d'un bloc
  */
public final class Couleur {
	
	public static final String BLEU = "bleu";
	public static final String ROUGE = "rouge";
	public static final String VERT = "vert";
	public static final String JAUNE = "jaune";
	
	public static boolean estCouleur(String s) {
		boolean ret = false;
		
		if(s != null) {
			
			if(s.equals(BLEU)) {ret = true;}
			else if(s.equals(ROUGE)) {ret = true;}
			else if(s.equals(VERT)) {ret = true;}
			else if(s.equals(JAUNE)) {ret = true;}
			
		}
		
		return ret;
	}

}