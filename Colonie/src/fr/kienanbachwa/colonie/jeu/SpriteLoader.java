package fr.kienanbachwa.colonie.jeu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.ResourceLoader;

/**
 *  @description Toutes les tiles d'une structure seront stockées dans une seule image au format png
 * 	Cette classe découpe ces images pour obtenir les tiles constituant les structures
 */
public class SpriteLoader {
	Image bigImg;
	Image[][] sprites;
	int sizeX;
	int sizeY;
	final int height = 32;	
	final int width = 32;
	
	public SpriteLoader(){
		
	}
	
	public Image[][] loadStructureSprites(String structName){
			try {
				bigImg = new Image("ressources/"+structName+".png");
			} catch (SlickException e) {
				if (bigImg==null){
					try {
						bigImg = new Image("ressources/brick.png");
					} catch (SlickException e1) {
						e1.printStackTrace();
					}
				}
			}
		
		sizeX=bigImg.getWidth()/width;
		sizeY=bigImg.getHeight()/height;
		sprites = new Image[sizeX][sizeY];
		
		for(int i = 0; i < sizeX; i++){
			for(int j = 0; j < sizeY; j++){
				sprites[i][j] = bigImg.getSubImage(i*width, j*height, width, height);
			}
		}
		
		return sprites;
	}
}
