package fr.benoitsepe.colonie.structures;

import java.util.List;

import fr.benoitsepe.colonie.main.Coordonnees;
import fr.benoitsepe.colonie.main.IStructure;
import fr.benoitsepe.colonie.ressources.Ressources;

public class Eolienne extends Structure implements IStructure{

	public Eolienne(List<Coordonnees> coos) {
		super("Eolienne", coos);
	}

	@Override
	public void utiliser(Ressources res) {
		// TODO Auto-generated method stub
		res.setElec(res.getElec() + 1);
	}


}
