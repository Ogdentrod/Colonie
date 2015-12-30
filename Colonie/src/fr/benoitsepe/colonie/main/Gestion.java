package fr.benoitsepe.colonie.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.lwjgl.input.Mouse;

import fr.benoitsepe.colonie.elements.Batiment;
import fr.benoitsepe.colonie.elements.Element;
import fr.benoitsepe.colonie.elements.Etat;
import fr.benoitsepe.colonie.elements.Mur;
import fr.benoitsepe.colonie.elements.Porte;
import fr.benoitsepe.colonie.elements.Sol;
import fr.benoitsepe.colonie.elements.TypeElements;
import fr.benoitsepe.colonie.elements.Vide;
import fr.benoitsepe.colonie.ressources.Ressources;
import fr.benoitsepe.colonie.structures.Structure;
import fr.benoitsepe.colonie.structures.TypeStructures;
import fr.kienanbachwa.colonie.graphics.Hud;
import fr.kienanbachwa.colonie.graphics.Renderer;
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
	private boolean clicked;
	private int dx2;
	private int dy2;
	private int dx1;
	private int dy1;
	List<Element> selectedTiles = new ArrayList<Element>();
	private int xMin;
	private int yMin;
	private int yMax;
	private int xMax;
	private LinkedBlockingQueue<Element> queue;
	private long time;
	private Element enConstruction = null;
	

	public Gestion(int sizeX, int sizeY) {
		elems = new Element[sizeX][sizeY];
		
		queue = new LinkedBlockingQueue<Element>();
		time = 0;
		
		res.setIron(100000);
		res.setElec(100000);
		res.setIron_ore(100000);
		res.setOxygen(100000);
		res.setWater(100000);

		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				elems[i][j] = new Vide(i, j);
				elems[i][j].setEtat(Etat.OPERATIONNAL);
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
					elemCree = new Porte(posX, posY);
					break;
				default:
					elemCree = new Vide(posX, posY);
					break;
			}
			elems[posX][posY] = elemCree;

			queue.offer(elemCree); // ajout de l'élément à la liste de construction
			
			
			
			
			// On actualise tous les murs/sols
			for(int i = 0; i < elems.length; i++){
				for(int j = 0; j < elems[i].length; j++){
					if (elems[i][j] instanceof Batiment) {
						elems[i][j] = SolOuMur(i, j);
					}
				}
			}


			//Affichage.afficherStruct(elemCree); // instruction temporaire pour
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
//		xMin = (int) (-Game.xScroll / Structure.tileSize);
//		yMin = (int) (-Game.yScroll / Structure.tileSize);
//
//		xMax = (((int) (-Game.xScroll / Structure.tileSize) + (Component.width / Structure.tileSize)+ 1) >= elems.length) ? elems.length : (int) (-Game.xScroll / Structure.tileSize) + (Component.width / Structure.tileSize) + 2;
//		yMax = (((int) (-Game.yScroll / Structure.tileSize) + (Component.height / Structure.tileSize)+ 1) >= elems[0].length) ? elems[0].length : (int) (-Game.yScroll / Structure.tileSize) + (Component.height / Structure.tileSize) + 2;

		xMin = (int) (-Game.xScroll / Structure.tileSize);
		yMin = (int) (-Game.yScroll / Structure.tileSize);

		xMax = (((int) (-Game.xScroll / Structure.tileSize) + (Component.width / Structure.tileSize)+ 1) >= elems.length) ? elems.length : (int) (-Game.xScroll / Structure.tileSize) + (Component.width / Structure.tileSize) + 2;
		yMax = (((int) (-Game.yScroll / Structure.tileSize) + (Component.height / Structure.tileSize)+ 1) >= elems[0].length) ? elems[0].length : (int) (-Game.yScroll / Structure.tileSize) + (Component.height / Structure.tileSize) + 2;

		
		for (int x = xMin; x < xMax; x++) {
			for (int y = yMin; y < yMax; y++) {
				if (elems[x][y] != null){
					elems[x][y].render(x, y);
				}
			}
		}
		
		renderSelectedTiles();

	}
	
	public void renderSelectedTiles(){
		if(Mouse.isButtonDown(0) && !Hud.mouseOnHud){			
			for(Element e : selectedTiles){
				Hud.elementClicked.getTexture().bind();
				Renderer.renderQuad(e.getX()*16, e.getY()*16, 16, 16, new float[]{0,0,1,0.5f});
				Hud.elementClicked.getTexture().unbind();
			}
		}
	}

	public void update() {
		
		
		// CONSTRUCTION
		if(!queue.isEmpty() || enConstruction != null) {
			
			// On a toujours pas commencé la construction (élément ajouté à l'instant)
			if(enConstruction == null) {
				time = System.currentTimeMillis();
				enConstruction = queue.poll();
				elems[enConstruction.getX()][enConstruction.getY()].setEtat(Etat.CONSTRUCTION);
			} else {
				if(System.currentTimeMillis() - time > enConstruction.getTempsConstruction()) { // Si le temps de construction est fini
					elems[enConstruction.getX()][enConstruction.getY()].setEtat(Etat.OPERATIONNAL);
					enConstruction = null;
				}
			}
			
			
		}

		/*
		 * for (Structure sousTab[] : structures) { for (Structure str :
		 * sousTab) { if(str != null) str.utiliser(res); } }
		 */
		
		if(Mouse.isButtonDown(0) && !Hud.mouseOnHud && !clicked){
			dx2 = Game.mouseXGrid;
			dy2 = Game.mouseYGrid;
		}else{	
			dx1 = Game.mouseXGrid;
			dy1 = Game.mouseYGrid;
		}
		
		if(Mouse.isButtonDown(0) && !Hud.mouseOnHud){
			clicked=true;
			selectTiles();

		}else{
			clicked=false;
			dx2 = Game.mouseXGrid;
			dy2 = Game.mouseYGrid;
			
			for(Element e : selectedTiles){
				creerElem(Hud.elementClicked, e.getX(), e.getY());
			}
			
			selectedTiles.clear();
		}
		
	}
	
	private void selectTiles(){
		selectedTiles.clear();
		try {
			if(!selectedTiles.contains(elems[dx2][dy2])) selectedTiles.add(elems[dx2][dy2]);
			
			for(int j=dy1; j!=dy2; j+= ( (dy1-dy2 > 0) ? -1 : 1) ){
				if(!selectedTiles.contains(elems[dx2][j])) selectedTiles.add(this.elems[dx2][j]);
			}
	
	
			
			for(int i=dx1; i<dx2 || i>dx2; i+= ( (dx1-dx2 > 0) ? -1 : 1) ){		//BOUCLE FOR AVEC OPERATEUR TERNAIRE AIIIIGHT
	
				if(!selectedTiles.contains(elems[i][dy2])) selectedTiles.add(this.elems[i][dy2]);
					for(int j=dy1; j<dy2 || j>dy2; j+= ( (dy1-dy2 > 0) ? -1 : 1) ){
						if(!selectedTiles.contains(elems[i][j])) selectedTiles.add(this.elems[i][j]);
				}
				
			}
		} catch(java.lang.ArrayIndexOutOfBoundsException e) {
			
		}
	}

	private Element SolOuMur(int x, int y) {
		
		boolean batiment;
		if (elems[x][y] instanceof Batiment && elems[x][y].getEtat() == Etat.OPERATIONNAL) {
			batiment = true;
		} else {
			batiment = false;
		}
		
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
			if (batiment) {
				return new Mur(x, y, Etat.OPERATIONNAL);
			} else {
				return new Mur(x, y);
			}
		}
		
		// Si le bloc en question est entouré de batiment, alors c'est un sol, sinon un mur
		if (hg instanceof Batiment && h instanceof Batiment 
				&& hd instanceof Batiment && g instanceof Batiment 
				&& bg instanceof Batiment && b instanceof Batiment 
				&& bd instanceof Batiment && d instanceof Batiment) {
			if (batiment) {
				return new Sol(x, y, elems[x][y].getEtat());
			} else {
				return new Sol(x, y);
			}
			
		} else {
			if (batiment) {
				return new Mur(x, y, elems[x][y].getEtat());
			} else {
				return new Mur(x, y);
			}
		}
	}
	

}
