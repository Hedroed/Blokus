package ihm;

import blokus.*;

import java.awt.*;


public class PieceGraphique {
	
	private boolean[][] position;
	
	private int couleur;
	
	private Image blocImage;
	
	public PieceGraphique(boolean[][] pos, int couleur) {
		
		this.position = pos;
		
		this.couleur = couleur;
		
		if(couleur == Bloc.ROUGE) {
			blocImage = Fenetre.loadImage("partie/rouge.png");
		}
		else if(couleur == Bloc.VERT) {
			blocImage = Fenetre.loadImage("partie/vert.png");
		}
		else if(couleur == Bloc.BLEU) {
			blocImage = Fenetre.loadImage("partie/jaune.png");
		}
		else if(couleur == Bloc.JAUNE) {
			blocImage = Fenetre.loadImage("partie/bleu.png");
		}
		
	}
	
	public void draw(Graphics g, int x, int y,int t) {
		
		int taille = t/Piece.TAILLE_TABLEAU;
		
		for(int i=0; i<Piece.TAILLE_TABLEAU;i++) {
			for(int j=0; j<Piece.TAILLE_TABLEAU;j++) {
				if(position[i][j]) {
				
					g.drawImage(blocImage,x+(j*taille),y+(i*taille),taille,taille,null);
					
				}
			}
		}
		
	}
}