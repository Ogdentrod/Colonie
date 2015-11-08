package fr.kienanbachwa.colonie.jeu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteLoader {
	BufferedImage bigImg;
	BufferedImage[][] sprites;
	int sizeX;
	int sizeY;
	final int height = 32;
	final int width = 32;
	
	public SpriteLoader(){
		
	}
	
	public BufferedImage[][] loadStructureSprites(String structName){
		try {
			bigImg = ImageIO.read(new File("ressources/"+structName+".png"));
		} catch (IOException e) {
			try {
				bigImg = ImageIO.read(new File("ressources/brick.png"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		sizeX=bigImg.getWidth()/width;
		sizeY=bigImg.getHeight()/height;
		sprites = new BufferedImage[sizeX][sizeY];
		
		for(int i = 0; i < sizeX; i++){
			for(int j = 0; j < sizeY; j++){
				sprites[i][j] = bigImg.getSubimage(i*width, j*height, width, height);
			}
		}
		
		return sprites;
	}
}
