package fr.benoitsepe.colonie.structures;

public class Mur extends Batiment{

	public Mur(int x, int y) {
		super("Mur", x, y, Etat.QUEUED);
		// TODO Auto-generated constructor stub
	}
	
	public Mur(int x, int y, Etat etat) {
		super("Mur", x, y, etat);
		// TODO Auto-generated constructor stub
	}

}
