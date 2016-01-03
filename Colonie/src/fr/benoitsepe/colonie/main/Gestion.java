package fr.benoitsepe.colonie.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.lwjgl.input.Mouse;

import fr.benoitsepe.colonie.ressources.Ressources;
import fr.benoitsepe.colonie.structures.Batiment;
import fr.benoitsepe.colonie.structures.Structure;
import fr.benoitsepe.colonie.structures.Etat;
import fr.benoitsepe.colonie.structures.Mur;
import fr.benoitsepe.colonie.structures.Porte;
import fr.benoitsepe.colonie.structures.Sol;
import fr.benoitsepe.colonie.structures.TypeStructures;
import fr.benoitsepe.colonie.structures.Vide;
import fr.benoitsepe.colonie.zone.Zone;
import fr.benoitsepe.colonie.zone.TypeZones;
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
	Structure[][] structs;
	private boolean clicked;
	private int dx2;
	private int dy2;
	private int dx1;
	private int dy1;
	List<Structure> selectedTiles = new ArrayList<Structure>();
	private int xMin;
	private int yMin;
	private int yMax;
	private int xMax;
	private LinkedBlockingQueue<Structure> queue;
	private long time;
	private Structure enConstruction = null;
	

	public Gestion(int sizeX, int sizeY) {
		structs = new Structure[sizeX][sizeY];
		
		queue = new LinkedBlockingQueue<Structure>();
		time = 0;
		
		res.setIron(100000);
		res.setElec(100000);
		res.setIron_ore(100000);
		res.setOxygen(100000);
		res.setWater(100000);

		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				structs[i][j] = new Vide(i, j);
				structs[i][j].setEtat(Etat.OPERATIONNAL);
			}
		}

	}

	public void creerElem(TypeStructures struct, int posX, int posY) {

		if (verifRessources(res, struct)) {
			Ressources.utiliserRessources(res, struct.getRes());
			Structure structCree;

			switch (struct) {
				case BATIMENT:
					structCree = SolOuMur(posX, posY);
					break;
				case PORTE:
					structCree = new Porte(posX, posY);
					break;
				default:
					structCree = new Vide(posX, posY);
					break;
			}
			structs[posX][posY] = structCree;

			queue.offer(structCree); // ajout de l'élément à la liste de construction
			
			
			
			
			// On actualise tous les murs/sols
			for(int i = 0; i < structs.length; i++){
				for(int j = 0; j < structs[i].length; j++){
					if (structs[i][j] instanceof Batiment) {
						structs[i][j] = SolOuMur(i, j);
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
	public boolean verifRessources(Ressources res, TypeZones typeZone) {
		Ressources besoin = typeZone.getRes();

		if (res.canBuy(besoin)) {
			return true;
		} else {
			return false;
		}

	}

	public boolean verifRessources(Ressources res, TypeStructures struct) {
		Ressources besoin = struct.getRes();

		if (res.canBuy(besoin)) {
			return true;
		} else {
			return false;
		}

	}

	public Structure[][] getStructures() {
		return structs;
	}

	public void render() {
//		xMin = (int) (-Game.xScroll / Structure.tileSize);
//		yMin = (int) (-Game.yScroll / Structure.tileSize);
//
//		xMax = (((int) (-Game.xScroll / Structure.tileSize) + (Component.width / Structure.tileSize)+ 1) >= elems.length) ? elems.length : (int) (-Game.xScroll / Structure.tileSize) + (Component.width / Structure.tileSize) + 2;
//		yMax = (((int) (-Game.yScroll / Structure.tileSize) + (Component.height / Structure.tileSize)+ 1) >= elems[0].length) ? elems[0].length : (int) (-Game.yScroll / Structure.tileSize) + (Component.height / Structure.tileSize) + 2;

		xMin = (int) (-Game.xScroll / Zone.tileSize);
		yMin = (int) (-Game.yScroll / Zone.tileSize);

		xMax = (((int) (-Game.xScroll / Zone.tileSize) + (Component.width / Zone.tileSize/Game.zoom)+ 3) >= structs.length) ? structs.length : (int) (-Game.xScroll / Zone.tileSize) + (int)(Component.width / Zone.tileSize/Game.zoom) + 3;
		yMax = (((int) (-Game.yScroll / Zone.tileSize) + (Component.height / Zone.tileSize/Game.zoom)+ 3) >= structs[0].length) ? structs[0].length : (int) (-Game.yScroll / Zone.tileSize) + (int)(Component.height / Zone.tileSize/Game.zoom) + 2;

		
		for (int x = xMin; x < xMax; x++) {
			for (int y = yMin; y < yMax; y++) {
				if (structs[x][y] != null){
					structs[x][y].render(x, y);
					if (structs[x][y].getEtat() == Etat.QUEUED || structs[x][y].getEtat() == Etat.CONSTRUCTION) {
						Etat.valueOf(structs[x][y].getEtat().toString()).getTexture().bind();
						Renderer.renderQuad(x*16, y*16, 16, 16, new float[]{1,1,1,1});
						Etat.valueOf(structs[x][y].getEtat().toString()).getTexture().unbind();

					}
				}
			}
		}
		
		renderSelectedTiles();

	}
	
	public void renderSelectedTiles(){
		if(Mouse.isButtonDown(0) && !Hud.mouseOnHud){			
			for(Structure e : selectedTiles){
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
				structs[enConstruction.getX()][enConstruction.getY()].setEtat(Etat.CONSTRUCTION);
			} else {
				if(System.currentTimeMillis() - time > enConstruction.getTempsConstruction()) { // Si le temps de construction est fini
					structs[enConstruction.getX()][enConstruction.getY()].setEtat(Etat.OPERATIONNAL);
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
			
			for(Structure e : selectedTiles){
				creerElem(Hud.elementClicked, e.getX(), e.getY());
			}
			
			selectedTiles.clear();
		}
		
	}
	
	private void selectTiles(){
		selectedTiles.clear();
		try {
			if(!selectedTiles.contains(structs[dx2][dy2])) selectedTiles.add(structs[dx2][dy2]);
			
			for(int j=dy1; j!=dy2; j+= ( (dy1-dy2 > 0) ? -1 : 1) ){
				if(!selectedTiles.contains(structs[dx2][j])) selectedTiles.add(this.structs[dx2][j]);
			}
	
	
			
			for(int i=dx1; i<dx2 || i>dx2; i+= ( (dx1-dx2 > 0) ? -1 : 1) ){		//BOUCLE FOR AVEC OPERATEUR TERNAIRE AIIIIGHT
	
				if(!selectedTiles.contains(structs[i][dy2])) selectedTiles.add(this.structs[i][dy2]);
					for(int j=dy1; j<dy2 || j>dy2; j+= ( (dy1-dy2 > 0) ? -1 : 1) ){
						if(!selectedTiles.contains(structs[i][j])) selectedTiles.add(this.structs[i][j]);
				}
				
			}
		} catch(java.lang.ArrayIndexOutOfBoundsException e) {
			
		}
	}

	private Structure SolOuMur(int x, int y) {
		
		boolean batiment;
		if (structs[x][y] instanceof Batiment && structs[x][y].getEtat() == Etat.OPERATIONNAL) {
			batiment = true;
		} else {
			batiment = false;
		}
		
		Structure hg, h, hd, g, bg, b, bd, d;
		/*
		 * HG - H - HD
		 * G  - x - D
		 * BG - B - BD
		 */
		try {
			hg = structs[x-1][y-1];
			h = structs[x][y-1];
			hd = structs[x+1][y-1];
			g = structs[x-1][y];
			bg = structs[x-1][y+1];
			b = structs[x][y+1];
			bd = structs[x+1][y+1];
			d = structs[x+1][y];
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
				return new Sol(x, y, structs[x][y].getEtat());
			} else {
				return new Sol(x, y);
			}
			
		} else {
			if (batiment) {
				return new Mur(x, y, structs[x][y].getEtat());
			} else {
				return new Mur(x, y);
			}
		}
	}
	

}
