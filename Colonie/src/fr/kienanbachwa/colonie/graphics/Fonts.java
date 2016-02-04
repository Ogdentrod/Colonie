package fr.kienanbachwa.colonie.graphics;

import java.awt.Font;
import java.io.InputStream;

import fr.kienanbachwa.colonie.graphics.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Fonts {
	
	Font awtFont;
	String path;
	float size;

	public Fonts(String path, float size) {
		this.path=path;
		this.size=size;
	}



	public TrueTypeFont getFont() {
		try {
			InputStream inputStream = ResourceLoader.getResourceAsStream(path);
			awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(size);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new TrueTypeFont(awtFont, true);		
	}
}
