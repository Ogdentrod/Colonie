package fr.kienanbachwa.colonie.jeu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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
	Image grass;
	
	public Map(){
			try {
				grass = new Image("ressources/grass.png");
			} catch (SlickException e) {
				e.printStackTrace();
			}

		Structure[][] map = new Structure[100][100];
	}
	
	/**
	 * Affiche une partie de la map (de [minX;minY] à [maxX;maxY]
	 * width et height: taille des tiles sur le rendu
	 **/
	public void render(int minX, int minY, int maxX, int maxY, int width, int height, Graphics g){
		renderGround(minX, minY, maxX, maxY, width, height, g);
	}

	public void renderStructures(int minX, int minY, int maxX, int maxY, int width, int height, Graphics g){
	    for(int i = minX ; i<maxX; i++){
			for(int j = minY; j<maxY; j++){
				
			}
		}
	}
	
	public void renderGround(int minX, int minY, int maxX, int maxY, int width, int height, Graphics g){
	    for(int i = minX ; i<maxX; i++){
			for(int j = minY; j<maxY; j++){
				//g.drawImage(grass, i, j, i, j, i+width, j+height);
				grass.draw(i, j);
			}
		}
	}
	
	}
