package fr.kienanbachwa.colonie.graphics.hud.panels;

import org.lwjgl.input.Mouse;

import fr.kienanbachwa.colonie.graphics.hud.Hud;
import fr.kienanbachwa.colonie.graphics.hud.dialogue.DialogueBox;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.ButtonHud;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.StructButton;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Thing;
import fr.kienanbachwa.colonie.jeu.Component;

public class PanelGrid extends Panel {

	public int gridX;
	public int gridY;

	public int nbObjectsX = 8;
	public int nbObjectsY = 2;
	public int pages = 0;	// 0 = 1 page, 1 = 2 pages...etc
	
	public PanelGrid(int x, int y, int w, int h) {
		super(x, y, w, h);
		this.isGrid=true;
	}

	public void init() {
		super.init();
		((ButtonHud)array.get(0)).select();
	}

	public void update(int x, int y, int w, int h) {
		super.update();

		
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		int i = 0; //Position x dans la page
		int j = 0; //Position y dans la page
		
		for (Thing b : array) {			
			b.update(x + gridBorder/2 +1 + i*(Hud.hudTileSize + gridBorder), y + 2+ j*(Hud.hudTileSize + gridBorder) , Hud.hudTileSize-2, Hud.hudTileSize -2);
			
			i++;
			if(i>nbObjectsX){
				i=0;
				j++;
			}
			if(j>nbObjectsY){
				i=0;
				j=0;
			}
			
		}
	}
	
	public void render(){
		super.render();
		
		for(Thing b : array){
			if(((StructButton)b).hovered){
				DialogueBox.showInfo(((StructButton)b).getTypeStructure().name(), (int)(Mouse.getX()/Component.scale) +4, (int)(Component.height-(Mouse.getY()/Component.scale)));
			}
		}
		
	}

}
