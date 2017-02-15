package fr.kienanbachwa.colonie.graphics.hud.panels;

import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Thing;

public class PanelVertical extends Panel{

	public PanelVertical (int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	public void update(int x, int y, int w, int h){
		this.x = x;		
		this.y = y;
		this.w=w;
		this.h=h;
		
		for(Thing b : array){
			b.update(x, y + array.indexOf(b)*(h/array.size()), w, h/array.size());
		}
	}

}
