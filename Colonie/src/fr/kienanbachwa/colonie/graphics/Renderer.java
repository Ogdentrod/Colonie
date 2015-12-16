package fr.kienanbachwa.colonie.graphics;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {
	public static void quadData(int x, int y, int w, int h, float[] color){
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(0,0); glVertex2f(x,y);
		glTexCoord2f(1,0); glVertex2f(x+w,y);
		glTexCoord2f(1,1); glVertex2f(x+w,y+h);
		glTexCoord2f(0,1); glVertex2f(x,y+h);
	}
	
	public static void renderQuad(int x, int y, int w, int h, float[] color){
		glBegin(GL_QUADS);
			Renderer.quadData(x, y, w, h, color);
		glEnd();
	}
	
	
}
