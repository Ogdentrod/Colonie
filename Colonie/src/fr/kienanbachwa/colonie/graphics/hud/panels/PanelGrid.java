package fr.kienanbachwa.colonie.graphics.hud.panels;

import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.hud.Hud;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Thing;

public class PanelGrid extends Panel {

	public int gridX;
	public int gridY;
	public int gridBorder = 2;

	public int nbObjectsX = 8;
	public int nbObjectsY = 2;
	public int pages = 0;	// 0 = 1 page, 1 = 2 pages...etc
	
	public PanelGrid(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void init() {
		super.init();
	}

	public void update(int x, int y, int w, int h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		int i = 0; //Position x dans la page
		int j = 0; //Position y dans la page
		int p = 0; //Page
		
		nbObjectsX = (w - Hud.hudTileSize) / Hud.hudTileSize;
		nbObjectsY = (h - Hud.hudTileSize) / Hud.hudTileSize;
		
		for (Thing b : array) {			
			b.update(x + gridBorder/2 + i*(Hud.hudTileSize + gridBorder), y + j*(Hud.hudTileSize + gridBorder) , Hud.hudTileSize, Hud.hudTileSize);
			
			i++;
			if(i>nbObjectsX){
				i=0;
				j++;
			}
			if(j>nbObjectsY){
				i=0;
				j=0;
				p++;
			}
			
		}
	}
	
	public void render(){
		super.render();
		
		int j = 0;
		while( (j+gridBorder+Hud.hudTileSize) < h){
			Renderer.renderQuad(x , y+j, w, gridBorder/2, new float[]{0.2f,0.2f,0.2f,1} );
			Renderer.renderQuad(x , y+j+Hud.hudTileSize+gridBorder/2, w, gridBorder/2, new float[]{0.8f,0.8f,0.8f,1} );
			j += (Hud.hudTileSize);
		}
		
		int i = 0;
		while( (i+gridBorder+Hud.hudTileSize) < w){
			Renderer.renderQuad(x+i , y, gridBorder/2, h, new float[]{0.2f,0.2f,0.2f,1} );
			Renderer.renderQuad(x+i+Hud.hudTileSize+gridBorder/2 , y, gridBorder/2, h, new float[]{0.8f,0.8f,0.8f,1} );
			i += (Hud.hudTileSize + gridBorder);
		}
	}

}
