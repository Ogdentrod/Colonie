package fr.benoitsepe.colonie.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.lwjgl.input.Mouse;

import fr.benoitsepe.colonie.personnages.Ouvrier;
import fr.benoitsepe.colonie.ressources.Ressources;
import fr.benoitsepe.colonie.structures.Batiment;
import fr.benoitsepe.colonie.structures.Etat;
import fr.benoitsepe.colonie.structures.Mur;
import fr.benoitsepe.colonie.structures.Porte;
import fr.benoitsepe.colonie.structures.Sol;
import fr.benoitsepe.colonie.structures.Structure;
import fr.benoitsepe.colonie.structures.TypeStructures;
import fr.benoitsepe.colonie.structures.Vide;
import fr.benoitsepe.colonie.zone.Eolienne;
import fr.benoitsepe.colonie.zone.PanneauSolaire;
import fr.benoitsepe.colonie.zone.Refectoire;
import fr.benoitsepe.colonie.zone.Sas;
import fr.benoitsepe.colonie.zone.TypeZones;
import fr.benoitsepe.colonie.zone.UsineOxygene;
import fr.benoitsepe.colonie.zone.Zone;
import fr.kienanbachwa.colonie.graphics.hud.Hud;
import fr.kienanbachwa.colonie.graphics.hud.dialogue.DialogueConfirm;
import fr.kienanbachwa.colonie.jeu.Game;

/**
 * @author Benoît
 * @description Classe permettant de construire la structure en consommant les
 *              ressources
 */
public class Gestion {
	
	public static Ressources res = new Ressources();
	
	Structure[][] structs;
	Zone[][] zones;
	
	List<Ouvrier> ouvriersOccupes;
	private LinkedBlockingQueue<Ouvrier> ouvriersLibre;
	
	private boolean clicked;
	private int dx2;
	private int dy2;
	private int dx1;
	private int dy1;
	List<Structure> selectedTiles = new ArrayList<Structure>();	//Structures sélectionnées 

	private LinkedBlockingQueue<Structure> queue;

	

	public Gestion(int sizeX, int sizeY) {
		structs = new Structure[sizeX][sizeY];
		zones = new Zone[sizeX][sizeY];
		
		ouvriersOccupes = new ArrayList<Ouvrier>();
		ouvriersLibre = new LinkedBlockingQueue<Ouvrier>();
		
		 // AJOUT DE D'UN OUVRIER POUR LES CONSTRUCTION TEMPORAIREMENT
		for(int i = 0; i<10; i++)
			ouvriersLibre.offer(new Ouvrier());
	
		
		queue = new LinkedBlockingQueue<Structure>();
		
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

	public void creerStruct(TypeStructures struct, int posX, int posY) {
		if (struct != TypeStructures.valueOf(structs[posX][posY].getNom().toUpperCase())) { 
			if (!(struct == TypeStructures.BATIMENT && (TypeStructures.valueOf(structs[posX][posY].getNom().toUpperCase()) == TypeStructures.MUR || TypeStructures.valueOf(structs[posX][posY].getNom().toUpperCase()) == TypeStructures.SOL))) {
				
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
							if (structs[i][j] instanceof Sol || structs[i][j] instanceof Mur) {
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
		}
	}


	public void creerZone(TypeZones zone, List<Coordonnees> coos) {

		if (coos.size() >= 4) {

			Zone zoneCree;

			switch (zone) {
			case EOLIENNE:
				zoneCree = new Eolienne(coos);
				break;
			case PANNEAUSOLAIRE:
				zoneCree = new PanneauSolaire(coos);
				break;
			case REFECTOIRE:
				zoneCree = new Refectoire(coos);
				break;
			case SAS:
				zoneCree = new Sas(coos);
				break;
			case USINEOXYGENE:
				zoneCree = new UsineOxygene(coos);
				break;
			default:
				System.out.println("ERREUR : Zone non implémentée !");
				zoneCree = null;
				break;

			}
			
			for (Coordonnees coo : coos) {
				zones[coo.getX()][coo.getY()] = zoneCree;
			}

		} else {
			System.out.println("Pas assez de cases");
		}

	}

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

	public Zone[][] getZones() {
		return zones;
	}



	public void update() {
		
		// Nouveau systéme de construction avec ouvriers
		// /!\ RISQUE DE BUG
		
		if (!queue.isEmpty() && !ouvriersLibre.isEmpty()) {
			Ouvrier constructeur = ouvriersLibre.poll();
			Structure aConstruire = queue.poll();
			constructeur.setOccupation(aConstruire);
			ouvriersOccupes.add(constructeur);
			structs[aConstruire.getX()][aConstruire.getY()].setEtat(Etat.CONSTRUCTION);
		}
		
		for (Iterator<Ouvrier> iter = ouvriersOccupes.listIterator(); iter.hasNext();) {
			Ouvrier ouvr = iter.next();
			if (ouvr.utiliser()) {
				structs[ouvr.getOccupation().getX()][ouvr.getOccupation().getY()].setEtat(Etat.OPERATIONNAL);
				ouvr.setOccupation(null);
				ouvriersLibre.offer(ouvr);
				iter.remove();	
			}
		}
		
		
//	ANCIEN SYSTEME DE CONSTRUCTION
//		if(!queue.isEmpty() || enConstruction != null) {
//			
//			// On a toujours pas commencé la construction (élément ajouté à l'instant)
//			if(enConstruction == null) {
//				time = System.currentTimeMillis();
//				enConstruction = queue.poll();
//				structs[enConstruction.getX()][enConstruction.getY()].setEtat(Etat.CONSTRUCTION);
//			} else {
//				if(System.currentTimeMillis() - time > enConstruction.getTickConstruction()) { // Si le temps de construction est fini
//					structs[enConstruction.getX()][enConstruction.getY()].setEtat(Etat.OPERATIONNAL);
//					enConstruction = null;
//				}
//			}
//			
//			
//		}
		
		

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
			
			//ConfirmationConstruction confirm = new ConfirmationConstruction(selectedTiles);
			//confirm.start();
			

			
			//selectedTiles.clear();
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

	public List<Structure> getSelectedTiles() {
		return selectedTiles;
	}

	public void setSelectedTiles(List<Structure> selectedTiles) {
		this.selectedTiles = selectedTiles;
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
	
//	public class ConfirmationConstruction extends Thread {
//		
//		private List<Structure> selectedTiles;
//
//		 
//		public ConfirmationConstruction(List<Structure> selectedTiles) {
//			this.selectedTiles = selectedTiles;
//		}
//		public void run() {
//		    boolean construire = DialogueBox.showConfirm("Voulez vous construire ça ? (à améliorer)", 0, 0);
//		    if (construire) {
//		    	for(Structure e : selectedTiles){
//					creerStruct(Hud.elementClicked, e.getX(), e.getY());
//				}
//		    }
//		}
//	}
	

}
