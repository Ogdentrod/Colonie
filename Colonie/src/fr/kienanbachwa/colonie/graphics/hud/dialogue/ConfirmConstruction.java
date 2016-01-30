package fr.kienanbachwa.colonie.graphics.hud.dialogue;

import fr.benoitsepe.colonie.ressources.Ressources;
import fr.kienanbachwa.colonie.graphics.Texture;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.Button;

public class ConfirmConstruction extends DialogueBox{
	
	
	
	public ConfirmConstruction(int x, int y, int w, int h, Ressources res, int cost){
		super(x, y, w, h);
		
		Button confirm = new Button();
		Button cancel = new Button();
		
		Texture confirmTexture = Texture.loadTexture("check");
		Texture cancelTexture = Texture.loadTexture("cross");
		
		confirm.setTexture(confirmTexture);
		cancel.setTexture(cancelTexture);
		
		this.add(confirm);
		this.add(cancel);
	}
	
	
	
	
	
}
