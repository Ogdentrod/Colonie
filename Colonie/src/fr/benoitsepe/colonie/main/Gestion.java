package fr.benoitsepe.colonie.main;

import fr.benoitsepe.colonie.structures.exterieur.Eolienne;
import fr.benoitsepe.colonie.structures.exterieur.PanneauSolaire;
import fr.benoitsepe.colonie.structures.interieur.Refectoire;
import fr.benoitsepe.colonie.structures.interieur.Sas;
import fr.benoitsepe.colonie.structures.interieur.UsineOxygene;

/**
 * @author Benoît
 * @description Classe permettant de construire la structure en consommant les
 *              ressources
 */
public class Gestion {

	Ressources res;
	Structure[][] structures;

	public Gestion(int sizeX, int sizeY) {
		this.res = new Ressources();
		structures = new Structure[sizeX][sizeY];

/*
		for (int i = 0; i < structures.length; i++) {
			for (int j = 0; j < structures[i].length; j++) {
				structures[i][j] = new Vide();
			}
		}
*/
		Tick thread = new Tick(this.structures, this.res);
		thread.start();
	}

	public void creerStruct(TypeStructures struct, int posX, int posY) {

		if (verifRessources(res, struct)) {
			Ressources.utiliserRessources(res, struct.getRes());
			Structure structCree;

			switch (struct) {
			case EOLIENNE:
				structCree = new Eolienne();
				break;
			case PANNEAU_SOLAIRE:
				structCree = new PanneauSolaire();
				break;
			case REFECTOIRE:
				structCree = new Refectoire();
				break;
			case SAS:
				structCree = new Sas();
				break;
			case USINE_OXYGENE:
				structCree = new UsineOxygene();
				break;
			default:
				System.out.println("ERREUR : Structure non implémentée !");
				structCree = null;
				break;

			}

			
			structures[posY][posX] = structCree;

			Affichage.afficherStruct(structCree); // instruction temporaire pour
													// affichage
			Affichage.afficherRessource(res);
		} else {
			System.out.println("Vous n'avez pas assez de ressources :(");
		}

	}

	public boolean verifRessources(Ressources res, TypeStructures typeStruct) {
		Ressources besoin = typeStruct.getRes();

		if (res.canBuy(besoin)) {
			return true;
		} else {
			return false;
		}

	}

	public Structure[][] getStructures() {
		return structures;
	}

	public class Tick extends Thread {

		Structure[][] structures;
		Ressources res;

		public Tick(Structure[][] structures, Ressources res) {
			this.structures = structures;
			this.res = res;
		}

		public void run() {
			while (true) {
				for (Structure sousTab[] : structures) {
					for (Structure str : sousTab) {
						if(str != null)
							str.utiliser(res);
					}
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
