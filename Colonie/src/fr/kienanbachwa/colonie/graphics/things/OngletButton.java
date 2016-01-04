package fr.kienanbachwa.colonie.graphics.things;

public class OngletButton extends Button{

	boolean isSelected=false;
	String name;
	
	public OngletButton(String name){
		super();
		System.out.println("a");
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
	}
	
	public void select(){
		isSelected=true;
	}
	
	public boolean isSelected(){
		return isSelected;
	}
}
