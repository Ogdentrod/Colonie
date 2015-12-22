package fr.benoitsepe.colonie.main;

import org.lwjgl.input.Mouse;

import fr.benoitsepe.colonie.elements.Batiment;
import fr.benoitsepe.colonie.elements.Element;
import fr.benoitsepe.colonie.elements.Mur;
import fr.benoitsepe.colonie.elements.Porte;
import fr.benoitsepe.colonie.elements.Sol;
import fr.benoitsepe.colonie.elements.TypeElements;
import fr.benoitsepe.colonie.elements.Vide;
import fr.benoitsepe.colonie.ressources.Ressources;
import fr.benoitsepe.colonie.structures.Structure;
import fr.benoitsepe.colonie.structures.TypeStructures;
import fr.kienanbachwa.colonie.jeu.Component;
import fr.kienanbachwa.colonie.jeu.Game;

/**
 * @author Benoît
 * @description Classe permettant de construire la structure en consommant les
 *              ressources
 */
public class Gestion {

	public static Ressources res = new Ressources();
	Element[][] elems;

	public Gestion(int sizeX, int sizeY) {
		elems = new Element[sizeX][sizeY];

		res.setIron(100000);
		res.setElec(100000);
		res.setIron_ore(100000);
		res.setOxygen(100000);
		res.setWater(100000);

		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				this.creerElem(TypeElements.VIDE, i, j);
			}
		}

	}

	public void creerElem(TypeElements elem, int posX, int posY) {

		if (verifRessources(res, elem)) {
			Ressources.utiliserRessources(res, elem.getRes());
			Element elemCree;

			switch (elem) {

			case BATIMENT:
				elemCree = SolOuMur(posX, posY);
				break;
			case PORTE:
				elemCree = new Porte();
				break;
			default:
				elemCree = new Vide();
				break;

			}

			elems[posX][posY] = elemCree;
			elemCree.setX(posX);
			elemCree.setY(posY);
			
			// On actualise tous les murs/sols
			if (elemCree instanceof Batiment) {
				for(int i = 0; i < elems.length; i++){
					for(int j = 0; j < elems[i].length; j++){
						if (elems[i][j] instanceof Batiment) {
							elems[i][j] = SolOuMur(i, j);
						}
					}
				}
			}

			Affichage.afficherStruct(elemCree); // instruction temporaire pour
												// affichage

		} else {
			System.out.println("Vous n'avez pas assez de ressources :(");
		}

	}

	// CHANGEMENTS EN COURS
	/*
	public void creerStruct(TypeStructures struct, List<Coordonnees> coos) {

		if (coos.size() >= 4) {

			Structure structCree;

			switch (struct) {
			case EOLIENNE:
				structCree = new Eolienne(coos);
				break;
			case PANNEAUSOLAIRE:
				structCree = new PanneauSolaire(coos);
				break;
			case REFECTOIRE:
				structCree = new Refectoire(coos);
				break;
			case SAS:
				structCree = new Sas(coos);
				break;
			case USINEOXYGENE:
				structCree = new UsineOxygene(coos);
				break;
			default:
				System.out.println("ERREUR : Structure non implémentée !");
				structCree = null;
				break;

			}

			structures[posX][posY] = structCree;
			structCree.setX(posX);
			structCree.setY(posY);

			Affichage.afficherStruct(structCree); // instruction temporaire pour
													// affichage
		} else {
			System.out.println("Pas assez de cases");
		}

	}
	*/
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

	public void render() {
		int xMin = (int) (-Game.xScroll / Structure.tileSize);
		int yMin = (int) (-Game.yScroll / Structure.tileSize);

		int xMax = (((int) (-Game.xScroll / Structure.tileSize) + (Component.width / Structure.tileSize)
				+ 1) >= elems.length) ? elems.length
						: (int) (-Game.xScroll / Structure.tileSize) + (Component.width / Structure.tileSize) + 2;
		int yMax = (((int) (-Game.yScroll / Structure.tileSize) + (Component.height / Structure.tileSize)
				+ 1) >= elems[0].length) ? elems[0].length
						: (int) (-Game.yScroll / Structure.tileSize) + (Component.height / Structure.tileSize) + 2;

		for (int x = xMin; x < xMax; x++) {
			for (int y = yMin; y < yMax; y++) {
				if (elems[x][y] != null)
					elems[x][y].render(x, y);
			}
		}
	}

	public void update() {

		/*
		 * for (Structure sousTab[] : structures) { for (Structure str :
		 * sousTab) { if(str != null) str.utiliser(res); } }
		 */

		if (Mouse.isButtonDown(0)) {
			System.out.println("Creation d'un mur à (" + Game.mouseXGrid + ";" + Game.mouseYGrid + ")");
			this.creerElem(TypeElements.BATIMENT, Game.mouseXGrid, Game.mouseYGrid);
		}
		if (Mouse.isButtonDown(1)) {

		}

	}

	private Element SolOuMur(int x, int y) {
		Element hg, h, hd, g, bg, b, bd, d;
		/*
		 * HG - H - HD
		 * G  - x - D
		 * BG - B - BD
		 */
		try {
			hg = elems[x-1][y-1];
			h = elems[x][y-1];
			hd = elems[x+1][y-1];
			g = elems[x-1][y];
			bg = elems[x-1][y+1];
			b = elems[x][y+1];
			bd = elems[x+1][y+1];
			d = elems[x+1][y];
		} catch(java.lang.IndexOutOfBoundsException e) {
			return new Mur();
		}
		
		// Si le bloc en question est entouré de batiment, alors c'est un sol, sinon un mur
		if (hg instanceof Batiment && h instanceof Batiment 
				&& hd instanceof Batiment && g instanceof Batiment 
				&& bg instanceof Batiment && b instanceof Batiment 
				&& bd instanceof Batiment && d instanceof Batiment) {
			
			return new Sol();
			
		} else {
			return new Mur();
		}
	}
	

}
