package fr.benoitsepe.colonie.zone;

import java.util.List;

import fr.benoitsepe.colonie.main.Coordonnees;
import fr.benoitsepe.colonie.ressources.Ressources;

public class UsineOxygene extends Zone implements IZone{

	public UsineOxygene(List<Coordonnees> coos) {
		super("Usineoxygene", coos);
	}

	/* (non-Javadoc)
	 * @see fr.benoitsepe.colonie.main.IStructure#utiliser(fr.benoitsepe.colonie.main.Ressources)
	 * Methode obligatoire à toutes les structures non passives
	 */
	@Override
	public void utiliser(Ressources res) {
		res.setWater(res.getWater() - 1);
		res.setOxygen(res.getOxygen() + 2);
		
	}


}
