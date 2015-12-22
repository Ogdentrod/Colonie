package fr.benoitsepe.colonie.structures;

import java.util.List;

import fr.benoitsepe.colonie.main.Coordonnees;
import fr.benoitsepe.colonie.main.IStructure;
import fr.benoitsepe.colonie.ressources.Ressources;

public class PanneauSolaire extends Structure implements IStructure {

	public PanneauSolaire(List<Coordonnees> coos) {
		super("Panneau Solaire", coos);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void utiliser(Ressources res) {
		// TODO Auto-generated method stub
		
	}

}
