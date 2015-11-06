package fr.kienanbachwa.colonie.jeu;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/*
 * http://slick.ninjacave.com/javadoc/ -> org.newdawn.slick.tiled
 * http://www.shionn.org/slick2d-lesson-2-map-display
 */

public class MapLoader{
	TiledMap map;

	public MapLoader(){
		try {
			this.map = new TiledMap("ressources/TestMap.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public TiledMap getMap() {
		return map;
	}
	
}
