package fr.benoitsepe.colonie.main;

import fr.benoitsepe.colonie.structures.exterieur.Eolienne;
import fr.benoitsepe.colonie.structures.exterieur.PanneauSolaire;
import fr.benoitsepe.colonie.structures.interieur.Refectoire;
import fr.benoitsepe.colonie.structures.interieur.Sas;
import fr.benoitsepe.colonie.structures.interieur.UsineOxygene;
import fr.kienanbachwa.colonie.jeu.Component;
import fr.kienanbachwa.colonie.jeu.Game;

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
			structCree.x = posX;
			structCree.y = posY;

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

	public void render(){
		int xMin = (int)(-Game.xScroll/Structure.tileSize);
		int yMin = (int)(-Game.yScroll/Structure.tileSize);
		
		int xMax = (((int)(-Game.xScroll/Structure.tileSize) + (Component.width/Structure.tileSize) + 1) >= structures.length) ?  structures.length : (int)(-Game.xScroll/Structure.tileSize) + (Component.width/Structure.tileSize) + 1;
		int yMax = (((int)(-Game.yScroll/Structure.tileSize) + (Component.height/Structure.tileSize) + 1) >= structures[0].length) ? structures[0].length : (int)(-Game.yScroll/Structure.tileSize) + (Component.height/Structure.tileSize) + 1;
		
		for (int x=xMin; x<xMax;x++) {
			for (int y=yMin; y<yMax; y++) {
				structures[x][y].render(x, y);
			}
		}	
	}
	
	
	public void update() {
		for (Structure sousTab[] : structures) {
			for (Structure str : sousTab) {
				if(str != null)
					str.utiliser(res);
			}
		}
	}
	
}
