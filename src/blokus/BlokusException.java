package blokus;
/**
*L'exception specifique au programme
*@extends RuntimeException
**/
public class BlokusException extends RuntimeException {
	
	public BlokusException(String s) {
		super(s);
	}
}