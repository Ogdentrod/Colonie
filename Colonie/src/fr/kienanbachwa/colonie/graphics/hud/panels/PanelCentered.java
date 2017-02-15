package fr.kienanbachwa.colonie.graphics.hud.panels;

import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Thing;
import fr.kienanbachwa.colonie.jeu.Component;

public class PanelCentered extends Panel{

	public PanelCentered(int x, int y, int w, int h) {
		super(x, y, w, h);
	}

	public void update(int x, int y,int w,int h){	
		this.x = x;		
		this.y = y;
		this.w=w;
		this.h=h;

		for(Thing b : array){
			b.update(x + (w/array.size()/2)*(array.indexOf(b)*2+1)-(int)(24/Component.scale), y+(h/2)-(int)(24/Component.scale), (int)(48/Component.scale), (int)(48/Component.scale));
		}

	}

}
