package fr.kienanbachwa.colonie.graphics.things;

import fr.kienanbachwa.colonie.graphics.Font;

public class OngletButton extends Button{

	boolean isSelected=false;
	Font font;
	String name;
	
	public OngletButton(String name){
		super();
		try {
			font = new Font("res/stan0753.ttf", 6);
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.name=name;
	}
	
	public void update(int x, int y, int w, int h){
		super.update(x, y, w, h);
		if(clicked){
			this.select();
		}
	}
	
	public void render(){
		super.render();
		font.drawText(name, x, y);
	}
	
	public void select(){
		isSelected=true;
	}
	
	public boolean isSelected(){
		return isSelected;
	}
}
