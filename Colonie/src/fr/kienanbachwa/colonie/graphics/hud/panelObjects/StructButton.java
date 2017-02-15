package fr.kienanbachwa.colonie.graphics.hud.panelObjects;

import fr.benoitsepe.colonie.structures.TypeStructures;
import fr.kienanbachwa.colonie.graphics.Color;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.Texture;
import fr.kienanbachwa.colonie.graphics.hud.Hud;
import fr.kienanbachwa.colonie.graphics.hud.dialogue.DialogueBox;

public class StructButton extends ButtonHud{

	Texture texture;
	private TypeStructures typeStructure;
	
	public StructButton(TypeStructures e){
		super();
		setTypeStructure(e);
		this.texture=e.getTexture();
	}
	
	public void update(int x, int y, int w, int h){
		super.update(x, y, w, h);
		if(clicked){
			Hud.elementClicked=this.getTypeStructure();
		}
	}
	
	public void render(){

		super.render();

		
		if(isSelected){
			Renderer.renderQuadSimple(x - 1, y -1 , w+1, 1, Color.DARKERRED);
			Renderer.renderQuadSimple(x + w , y-1, 1, h+1, Color.DARKRED);
			Renderer.renderQuadSimple(x-1, y+h, w+2, 1, Color.DARKRED);
			Renderer.renderQuadSimple(x-1 , y-1, 1, h+1, Color.DARKERRED);
			
		}
	}
	
	public void setTexture(Texture texture){
		this.texture=texture;
	}
	
	public Texture getTexture(){
		return texture;
	}

	public TypeStructures getTypeStructure() {
		return typeStructure;
	}

	public void setTypeStructure(TypeStructures typeStructure) {
		this.typeStructure = typeStructure;
	}
}
