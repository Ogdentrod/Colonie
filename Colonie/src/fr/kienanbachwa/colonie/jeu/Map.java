package fr.kienanbachwa.colonie.jeu;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.Layer;

import fr.benoitsepe.colonie.main.Structure;

public class Map {
	/** The width of the tiles used on the map */
	protected int tileWidth;
	/** The height of the tiles used on the map */
	protected int tileHeight;
	/** The width of the map */
	protected int width;
	/** The height of the map */
	protected int height;
	
	public Map(){
		Structure[][] map = new Structure[5][5];
	}
	
	public void render(int x, int y, int sx, int sy, int width, int height, Graphics g){
		g.drawString("Hello", 20, 20);
	}
/*	
	public void render(int x, int y, int sx, int sy, int width, int height,
			boolean lineByLine) {
		switch (orientation) {
		case ORTHOGONAL:
			for (int ty = 0; ty < height; ty++) {
				for (int i = 0; i < layers.size(); i++) {
					Layer layer = (Layer) layers.get(i);
					layer.render(x, y, sx, sy, width, ty, lineByLine,
							tileWidth, tileHeight);
				}
			}
			break;
		case ISOMETRIC:
			renderIsometricMap(x, y, sx, sy, width, height, null, lineByLine);
			break;
		default:
			// log error or something
		}
	}
*/	
	}
