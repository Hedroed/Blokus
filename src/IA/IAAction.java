/**
*Ce package contient tous les objets necessaires au fonctionnement des IA
**/
package IA;

import blokus.Piece;


/**
*Message de l'IA transmi au jeu qui decrit le coup a faire.
**/
public class IAAction {
	
	/**
	*la piece a placer
	**/
	private Piece p;
	/**
	*la coordonnee x ou on doit placer la piece
	**/
	private int posX;
	/**
	*la coordonnee y ou on doit placer la piece
	**/
	private int posY;
	/**
	*la piece du jeu, temoin pour eviter les erreurs de referencement.
	**/
	private Piece pieceOriginale;
	
	/**
	*Constructeur de la classe IAAction
	*@param p la piece a placer
	*@param x la coordonnee x ou on place la piece
	*@param x la coordonnee y ou on place la piece
	**/
	public IAAction(Piece p, int x, int y) {
		
		this.p = p;
		this.posX = x;
		this.posY = y;
		pieceOriginale=p;
		
	}
	/**
	*Constructeur de la classe IAAction avec un temoin
	*@param p la piece a placer
	*@param x la coordonnee x ou on place la piece
	*@param x la coordonnee y ou on place la piece
	*@param pieceOriginale le temoin
	**/
	public IAAction(Piece p,int x, int y, Piece pieceOriginale){
		this.p = p;
		this.posX = x;
		this.posY = y;
		this.pieceOriginale=pieceOriginale;
	}
	/**
	*Modifie la piece a placer
	*@param p la nouvelle piece
	**/
	public void setPiece(Piece p) {
		this.p = p;
	}
	/**
	*Modifie la coordonnee x de la piece a placer
	*@param x la nouvelle coordonnee
	**/
	public void setX(int x) {
		this.posX = x;
	}
	/**
	*Modifie la coordonnee y de la piece a placer
	*@param y la nouvelle coordonnee
	**/
	public void setY(int y) {
		this.posY = y;
	}
	/**
	*Accesseur de la piece
	*@return la piece
	**/
	public Piece getPiece() {
		return this.p;
	}
	
	/**
	*Accesseur de la coordonnee x
	*@return la coordonnee x
	**/
	public int getX() {
		return this.posX;
	}
	/**
	*Accesseur de la coordonnee y
	*@return la coordonnee y
	**/
	public int getY() {
		return this.posY;
	}
	/**
	*Met les infos de l'IAAction sous la forme d'une phrase
	*@return un String contenant les infos de l'objet
	**/
	public String toString(){
		return "Piece "+p+" x: "+posX+" y: "+posY;
	}
	/**
	*Accesseur de la pieceOriginale
	*@return la pieceOriginale
	**/
	public Piece getPieceOriginale(){
		return pieceOriginale;
	}
	/**
	*Modifie la pieceOriginale
	*@param p la nouvelle pieceOriginale
	**/
	public void setPieceOriginale(Piece p){
		pieceOriginale=p;
	}
}