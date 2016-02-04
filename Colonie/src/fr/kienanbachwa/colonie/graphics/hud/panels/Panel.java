package fr.kienanbachwa.colonie.graphics.hud.panels;

import java.util.ArrayList;

import fr.kienanbachwa.colonie.graphics.Color;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.OngletButton;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Thing;
import fr.kienanbachwa.colonie.graphics.hud.Hud;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Button;


public class Panel {
	
	public int x,y,w,h;			//Position x, y, largeur et hauteur
	ArrayList<Thing> array = new ArrayList<Thing>();	//Liste contenant tous les objets du panneau
	Texture texture;
	protected boolean isGrid = false;
	protected int gridBorder = 2;

	
	public Panel(int x, int y, int w, int h){
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		
		this.texture=Texture.loadTexture("hudText");
	}
	
	public void init(){
		for(Thing b : array){
			b.init();
		}
	}
	
	public void update(){
		for(Thing b : array){
			if( ((Button)b).isClicked() ){
				for(Thing c : array){
					if(c != b)
					((Button)c).deselect();
				}
			}
		}
	}
	
	public void render(){
		this.texture.bind();
		Renderer.renderQuad(x, y, w, h, Color.WHITE);
		this.texture.unbind();
		
		if(isGrid){
			int j = 0;
			while( (j+gridBorder) < h){
				Renderer.renderQuad(x , y+j, w, gridBorder/2, new float[]{0.2f,0.2f,0.2f,1} );
				Renderer.renderQuad(x , y+j+Hud.hudTileSize+gridBorder/2, w, gridBorder/2, new float[]{0.8f,0.8f,0.8f,1} );
				j += (Hud.hudTileSize + gridBorder);
			}
			
			int i = 0;
			while( (i+gridBorder) < w){
				Renderer.renderQuad(x+i, y, gridBorder/2, h, new float[]{0.2f,0.2f,0.2f,1} );
				Renderer.renderQuad(x+i+Hud.hudTileSize+gridBorder/2 , y, gridBorder/2, h, new float[]{0.8f,0.8f,0.8f,1} );
				i += (Hud.hudTileSize + gridBorder);
			}
		}
		
		Renderer.renderQuadSimple(x, y, w, 1, Color.BLACK);
		Renderer.renderQuadSimple(x, y, 1, h, Color.BLACK);
		Renderer.renderQuadSimple(x+w, y, w, 1, Color.BLACK);
		Renderer.renderQuadSimple(x, y+h, 1, h, Color.BLACK);

		for(Thing b : array){			
			b.render();
		}
	}
	
	public void add(Thing o){
		array.add(o);
	}
}
