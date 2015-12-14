package fr.kienanbachwa.colonie.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
	public static void quadData(int x, int y, int w, int h, float[] color){
		glColor4f(color[0], color[1], color[2], color[3]);
		glVertex2f(x,y);
		glVertex2f(x+w,y);
		glVertex2f(x+w,y+h);
		glVertex2f(x,y+h);
	}
	
	public static void renderQuad(int x, int y, int w, int h, float[] color){
		glBegin(GL_QUADS);
			Renderer.quadData(x, y, w, h, color);
		glEnd();
	}
	
	
}
