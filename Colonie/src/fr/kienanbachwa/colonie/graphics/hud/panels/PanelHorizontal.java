package fr.kienanbachwa.colonie.graphics.hud.panels;

import fr.kienanbachwa.colonie.graphics.Color;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.OngletButton;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Thing;

public class PanelHorizontal extends Panel{

	public PanelHorizontal(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void update(int x, int y, int w, int h){
		this.x = x;		
		this.y = y;
		this.w=w;
		this.h=h;

		for(Thing b : array){
			super.update();
			b.update(x +1 + array.indexOf(b)* (w/array.size()), y+1, w / array.size() -1, h -1);

//			if( ((OngletButton)b).isClicked() ){
//				for(Thing c : array){
//					if(c != b)
//					((OngletButton)c).deselect();
//				}
//			}	
			
		}
		
	}
	
	public void render(){
		Renderer.renderQuadSimple(x, y, w, h, Color.WHITE);
		super.render();
		
		for(Thing b : array){
			Renderer.renderQuadSimple(x+(w/array.size())*array.indexOf(b) , y, 1, h+5, Color.BLACK);
		}
		
	}
}
