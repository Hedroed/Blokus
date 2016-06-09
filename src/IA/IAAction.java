package IA;

import blokus.Piece;

public class IAAction {
	
	private Piece p;
	
	private int posX;
	
	private int posY;
	
	public IAAction(Piece p, int x, int y) {
		
		this.p = p;
		this.posX = x;
		this.posY = y;
		
	}
	
	public void setPiece(Piece p) {
		this.p = p;
	}
	
	public void setX(int x) {
		this.posX = x;
	}
	
	public void setY(int y) {
		this.posY = y;
	}
	
	public Piece getPiece() {
		return this.p;
	}
	
	public int getX() {
		return this.posX;
	}
	
	public int getY() {
		return this.posY;
	}
	
	
}