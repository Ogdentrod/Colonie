package fr.benoitsepe.colonie.elements;

public class Porte extends Element{

	public Porte(int x, int y) {
		super("Porte", 2000, x, y, Etat.QUEUED);
		// TODO Auto-generated constructor stub
	}
	
	public Porte(int x, int y, Etat etat) {
		super("Porte", 2000, x, y, etat);
		// TODO Auto-generated constructor stub
	}

}
