package fr.benoitsepe.colonie.structures;

public class Porte extends Batiment{

	public Porte(int x, int y) {
		super("Porte", x, y, Etat.QUEUED);
		// TODO Auto-generated constructor stub
	}
	
	public Porte(int x, int y, Etat etat) {
		super("Porte", x, y, etat);
		// TODO Auto-generated constructor stub
	}

}
