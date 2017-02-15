package fr.benoitsepe.colonie.zone;

import java.util.List;

import fr.benoitsepe.colonie.main.Coordonnees;
import fr.benoitsepe.colonie.ressources.Ressources;

public class PanneauSolaire extends Zone implements IZone {

	public PanneauSolaire(List<Coordonnees> coos) {
		super("Panneau Solaire", coos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliser(Ressources res) {
		// TODO Auto-generated method stub
		
	}

}
