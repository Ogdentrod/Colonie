package fr.benoitsepe.colonie.main;

import org.lwjgl.input.Mouse;

import fr.benoitsepe.colonie.elements.Mur;
import fr.benoitsepe.colonie.elements.Sol;
import fr.benoitsepe.colonie.elements.Vide;
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
	Element[][] elems;

	public Gestion(int sizeX, int sizeY) {
		this.res = new Ressources();
		elems = new Element[sizeX][sizeY];
		
		res.setIron(100000);
		res.setElec(100000);
		res.setIron_ore(100000);
		res.setOxygen(100000);
		res.setWater(100000);
		
		for(int i=0;i<sizeX;i++){
			for(int j=0;j<sizeY;j++){
				this.creerElem(TypeElements.VIDE, i, j);
			}
		}
		
	}
	
	public void creerElem(TypeElements elem, int posX, int posY) {

		if (verifRessources(res, elem)) {
			Ressources.utiliserRessources(res, elem.getRes());
			Element elemCree;

			switch (elem) {
			case MUR:
				elemCree = new Mur();
				break;
			case SOL:
				elemCree = new Sol();
				break;

			default:
				elemCree = new Vide();
				break;

			}

			
			elems[posX][posY] = elemCree;
			elemCree.x = posX;
			elemCree.y = posY;

			Affichage.afficherStruct(elemCree); // instruction temporaire pour
													// affichage
			Affichage.afficherRessource(res);
		} else {
			System.out.println("Vous n'avez pas assez de ressources :(");
		}

	}

	// CHANGEMENTS EN COURS
	@Deprecated
	public void creerStruct(TypeStructures struct, int posX, int posY) {

		if (verifRessources(res, struct)) {
			Ressources.utiliserRessources(res, struct.getRes());
			Structure structCree;

			switch (struct) {
			case EOLIENNE:
				structCree = new Eolienne();
				break;
			case PANNEAUSOLAIRE:
				structCree = new PanneauSolaire();
				break;
			case REFECTOIRE:
				structCree = new Refectoire();
				break;
			case SAS:
				structCree = new Sas();
				break;
			case USINEOXYGENE:
				structCree = new UsineOxygene();
				break;
			default:
				System.out.println("ERREUR : Structure non implémentée !");
				structCree = null;
				break;

			}

			
			structures[posX][posY] = structCree;
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
	
	public boolean verifRessources(Ressources res, TypeElements elem) {
		Ressources besoin = elem.getRes();

		if (res.canBuy(besoin)) {
			return true;
		} else {
			return false;
		}

	}

	public Element[][] getElements() {
		return elems;
	}

	public void render(){
		int xMin = (int)(-Game.xScroll/Structure.tileSize);
		int yMin = (int)(-Game.yScroll/Structure.tileSize);
		
		int xMax = (((int)(-Game.xScroll/Structure.tileSize) + (Component.width/Structure.tileSize) + 1) >= elems.length) ?  elems.length : (int)(-Game.xScroll/Structure.tileSize) + (Component.width/Structure.tileSize) + 2;
		int yMax = (((int)(-Game.yScroll/Structure.tileSize) + (Component.height/Structure.tileSize) + 1) >= elems[0].length) ? elems[0].length : (int)(-Game.yScroll/Structure.tileSize) + (Component.height/Structure.tileSize) + 2;
		
		for (int x=xMin; x<xMax;x++) {
			for (int y=yMin; y<yMax; y++) {
				if(elems[x][y]!=null) elems[x][y].render(x, y);
			}
		}	
	}
	
	
	public void update() {
		
		/*
		for (Structure sousTab[] : structures) {
			for (Structure str : sousTab) {
				if(str != null)
					str.utiliser(res);
			}
		}
		*/
		
		res.setIron(1000);
		res.setElec(1000);
		res.setIron_ore(1000);
		res.setOxygen(1000);
		res.setWater(1000);
		
		if(Mouse.isButtonDown(0)){
			System.out.println("Creation d'un mur à ("+Game.mouseXGrid+";"+Game.mouseYGrid+")");
			this.creerElem(TypeElements.MUR, Game.mouseXGrid, Game.mouseYGrid);
		}
		if(Mouse.isButtonDown(1)){
			this.creerElem(TypeElements.SOL, Game.mouseXGrid, Game.mouseYGrid);
		}
		
	}
	
}
