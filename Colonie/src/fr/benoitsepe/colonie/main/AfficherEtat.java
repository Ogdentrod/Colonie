package fr.benoitsepe.colonie.main;

public class AfficherEtat {
	
	public static void afficherEtat(Structure struct) {
		System.out.println();
		System.out.println(struct.getNom());
		System.out.println(" Etat = " + struct.getEtat());
		System.out.println(" Maintenance = " + struct.getMaintenance() + "%");
	}
}
