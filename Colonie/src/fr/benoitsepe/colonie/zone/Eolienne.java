package fr.benoitsepe.colonie.zone;

import java.util.List;

import fr.benoitsepe.colonie.main.Coordonnees;
import fr.benoitsepe.colonie.ressources.Ressources;

public class Eolienne extends Zone implements IZone{

	public Eolienne(List<Coordonnees> coos) {
		super("Eolienne", coos);
	}

	@Override
	public void utiliser(Ressources res) {
		// TODO Auto-generated method stub
		res.setElec(res.getElec() + 1);
	}


}
