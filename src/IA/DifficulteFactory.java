package IA;

/**
  * Génére un objet Difficulte a partir d'une chaine de charactere
  */
public class DifficulteFactory {
	
	public static final int FACILE = 0;
	public static final int MOYEN = 1;
	public static final int DUR = 2;
	public static final int EXPERT = 3;
	
	/**
	 * Créé un objet Difficulte
	 * @param niveau le niveau
	 * @return un oject Difficulte correspondant au niveau 
	 */
	public static Difficulte getDifficulte(int niveau) {
		Difficulte ret = null;
		
		switch(niveau) {
			case FACILE :   ret = new Facile();
							break;
			case MOYEN :   ret = new Moyen();
							break;
			case DUR :   ret = new Dur();
							break;
			case EXPERT :   ret = new Expert();
							break;
		}
		
		return ret;
	}

}