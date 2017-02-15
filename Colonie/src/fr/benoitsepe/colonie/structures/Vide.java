package fr.benoitsepe.colonie.structures;

public class Vide extends Structure {

	public Vide(int x, int y) {
		super("Vide", 0, x, y, Etat.QUEUED);
		// TODO Auto-generated constructor stub
	}
	
	public Vide(int x, int y, Etat etat) {
		super("Vide", 0, x, y, etat);
		// TODO Auto-generated constructor stub
	}

}
