package fr.kienanbachwa.colonie.graphics;

import java.util.Random;

public class Tile {

	int tileSize = 32;
	Random random;
	public Tile(){
		random = new Random();
	}
	
	
	public void render(int x, int y){
		
		float[] color = new float[]{random.nextFloat(), random.nextFloat(), random.nextFloat(), 1.0f};
		Renderer.renderQuad(x, y, tileSize, tileSize, color);
		
	}
}
