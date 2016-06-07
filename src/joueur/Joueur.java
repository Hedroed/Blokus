package joueur;

import java.util.*;
import blokus.*;
import java.io.*;

/**
  * Le Joueur contient tout les parametres d'un joueur durant une partie de Blokus
  * Une liste de pieces, une couleur et un nom
  * Il permet de savoir quel joueur est en train de jouer.
  */
public class Joueur {

	private ArrayList<Piece> pieces;
	private int couleur;
	private String nom;
	private boolean peutJouer;
	
	private int score;
	
	/**
	 * Constructeur prend un nom et une couleur
	 * @param nom
	 * @param couleur
	 */
	public Joueur(String nom, int couleur) {
		if(!Bloc.estCouleur(couleur)){throw new IllegalArgumentException("Couleur incorrecte");}
		this.nom=nom;
		peutJouer=true;
		
		this.couleur=couleur;
		
		score = 0;
		
		creePieces();

	}
	
	/**
	  * Initalise les pieces en debut de partie
	  * est appele par le constructeur
	  */
	private void creePieces() {
		
		pieces=new ArrayList<Piece>();
		
		BufferedReader in=null;
		
		in = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("pieces/pieces")));
		
		String ligne=null;
		int i=0;
		int j=0;
		int nbPieces=0;

		int parent1=0;
		int parent2=0;
		
		
		try{
			ligne=in.readLine();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		nbPieces=Integer.parseInt(ligne);
		// System.out.println(nbPieces);
		
		while(j<nbPieces){
			boolean[][] coordonnees = new boolean[Piece.LARGEUR_DEFAUT][Piece.LONGUEUR_DEFAUT];
			try{
			ligne=in.readLine();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			parent1=parent2=-1;
			String[] parents= ligne.split(",");
			// System.out.println(ligne);
			
			i=0;
			while(i<Piece.LARGEUR_DEFAUT){
				try{
				ligne=in.readLine();
				}
				catch(IOException e){
					e.printStackTrace();
				}
				
				int k=0;
				while(k<Piece.LONGUEUR_DEFAUT){
					String s=ligne.substring(k,k+1);
					int l=Integer.parseInt(s);
					if(l==1){
						coordonnees[i][k]=true;
					}
					else{
						coordonnees[i][k]=false;
					}
					
					k++;
				}
				i++;
			}
			Piece p=null;
			parent1=Integer.parseInt(parents[0]);
			
			if((parent1-1)<0){
				p=new Piece(couleur,coordonnees,null);
			}
			else{
				p=new Piece(couleur,coordonnees,pieces.get(parent1-1));
			}
			
			if(parents.length==2){
				
				parent2=Integer.parseInt(parents[1]);
				
				p.setParent2(pieces.get(parent2-1));
			}
			
			pieces.add(p);
			j++;
		}
		
		try{
			in.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
	
	}
	
	/**
	  * Permet de savoir si ce joueur est une IA pour appeler la method placePiece de l'IA
	  * @return un boolean
	  */
	public boolean isIA() {

		return false;
	}
	
	/**
	*Retire une piece du jeu du joueur
	*@param p la piece a supprimer
	*/
	public void jouerPiece(Piece p) {
		if(p == null) {throw new IllegalArgumentException("Parametre null");}
		
		score += p.getLongueur();
		
		pieces.remove(p);
		
	}
	
	/**
	*Retourne la couleur du joueur
	*
	*/
	public int getCouleur(){
		return couleur;
	}
	
	/**
	*Retourne le jeu de pieces du joueur
	*
	*/
	public ArrayList<Piece> getPieces(){
		return pieces;
	}
	
	/**
	*Met les caracteristiques du joueur dans un String
	*
	*/
	public String toString(){
		String ret =(this.nom+" de couleur "+this.couleur);
		return ret;
	}
	
	/**
	*Retourne l'etat du joueur, si il peut encore placer une piece sur le plateau
	*
	*/
	public boolean peutJouer() {
		return peutJouer;
	}
	
	/**
	*Modifie l'etat peutJouer du joueur.
	*@param l'etat du joueur, true ou false
	*/
	public void setPeutJouer(boolean b) {
		peutJouer = b;
	}
	
	public int getScore() {
		return score;
	}
	
	public static void main(String[] args) {
		
		Joueur j = new Joueur("michel",Bloc.ROUGE);
		
		ArrayList<Piece> ps = j.getPieces();
		
		int i =0;
		for(Piece p:ps) {
			System.out.println("Piece "+i);
			System.out.println("Parent "+p.getParent()+", "+p.getParent2());
			System.out.println();
			i++;
		}
		
		
		
		
	}
}
