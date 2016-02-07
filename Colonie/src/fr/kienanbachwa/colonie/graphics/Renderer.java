package fr.kienanbachwa.colonie.graphics;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.opengl.GL11;

public class Renderer {
	
	public static void quadData(int x, int y, int w, int h, float[] color){
		glColor4f(color[0], color[1], color[2], color[3]);
		glTexCoord2f(0,0); glVertex2f(x,y);
		glTexCoord2f(1,0); glVertex2f(x+w,y);
		glTexCoord2f(1,1); glVertex2f(x+w,y+h);
		glTexCoord2f(0,1); glVertex2f(x,y+h);
	}
	
	public static void quadDataSimple(int x, int y, int w, int h, float[] color){
		glColor4f(color[0], color[1], color[2], color[3]);
		glVertex2f(x,y);
		glVertex2f(x+w,y);
		glVertex2f(x+w,y+h);
		glVertex2f(x,y+h);
	}
	
	public static void renderQuadSimple(int x, int y, int w, int h, float[] color){
		GL11.glDisable(GL_TEXTURE_2D);

		glBegin(GL_QUADS);
			Renderer.quadDataSimple(x, y, w, h, color);
		glEnd();
		
		GL11.glEnable(GL_TEXTURE_2D);

	}
	
	public static void renderQuad(int x, int y, int w, int h, float[] color){
		Texture.loadTexture("Cage").bind();
		glBegin(GL_QUADS);
			Renderer.quadData(x, y, w, h, color);
		glEnd();
		Texture.loadTexture("Cage").unbind();
	}
	
}
