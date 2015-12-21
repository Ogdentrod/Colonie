package fr.benoitsepe.colonie.structures;

import fr.benoitsepe.colonie.main.IStructure;
import fr.benoitsepe.colonie.main.Ressources;

public class Eolienne extends Structure implements IStructure{

	public Eolienne() {
		super("Eolienne");
	}

	@Override
	public void utiliser(Ressources res) {
		// TODO Auto-generated method stub
		res.setElec(res.getElec() + 1);
	}


}
