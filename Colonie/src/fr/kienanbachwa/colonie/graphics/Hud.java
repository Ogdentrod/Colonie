package fr.kienanbachwa.colonie.graphics;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import fr.benoitsepe.colonie.ressources.TypeRessources;
import fr.benoitsepe.colonie.structures.TypeStructures;
import fr.benoitsepe.colonie.zone.TypeZones;
import fr.kienanbachwa.colonie.graphics.things.OngletButton;
import fr.kienanbachwa.colonie.graphics.things.ResourceDisplayer;
import fr.kienanbachwa.colonie.graphics.things.StructButton;
import fr.kienanbachwa.colonie.jeu.Component;

public class Hud {
	
	List<StructButton> buttons = new ArrayList<StructButton>();
	List<TypeRessources> ressources = new ArrayList<TypeRessources>();
	List<TypeZones> zones = new ArrayList<TypeZones>();


	public static boolean mouseOnHud;
	public static TypeStructures elementClicked = TypeStructures.BATIMENT;
	
	int x,y,w,h;
	Panneau panStructures;
	Panneau panZones;
	Panneau panResources;
	Panneau panOnglets;
	OngletButton structPage;
	OngletButton zonesPage;
	
	
	public Hud(){
		w=Component.width;
		h=(Component.height/16)*4;
		panStructures = new Panneau(x,y-10,w,h,1);
		panZones = new Panneau(x,y-10,w,h,1);
		panResources = new Panneau(x+panStructures.w,y,16,16,2);
		panOnglets = new Panneau(x,y,w,10,2);

		w=Component.width;
		h=(Component.height/16)*4;
		panStructures = new Panneau(x,y-10,w,h,1);
		panZones = new Panneau(x,y-10,w,h,1);
		panResources = new Panneau(x+panStructures.w,y,16,16,3);
		panOnglets = new Panneau(x,y,w,10,2);

		structPage = new OngletButton("Structures");
		zonesPage = new OngletButton("Zones");

		structPage.select();
	}
	
	public void init(){
		structPage.init();
		zonesPage.init();

		for(TypeStructures e : TypeStructures.values()){
			buttons.add(new StructButton(e));				//Boutons d'élements
		}
		
		for(TypeRessources res : TypeRessources.values()){
			panResources.add(new ResourceDisplayer(res));
		}
		
		for(TypeZones zone : TypeZones.values()){
			zones.add(zone);
		}
		
		for(StructButton e : buttons){
			panStructures.add(e);
		}
		
		panOnglets.add(structPage);
		panOnglets.add(zonesPage);
		
		panResources.init();

	}
	
	public void update(){
		h=Component.height/4;
		x = 0;		
		y =Component.height-h;	
		w=Component.width;

		
		if(Mouse.getX() > x*Component.scale && Mouse.getX()<(x+w)*Component.scale && Mouse.getY() < (Component.height-y)*Component.scale && Mouse.getY()> (Component.height-y-h)*Component.scale){
			mouseOnHud=true;
		}else{
			mouseOnHud=false;
		}		
		
		panStructures.update(x,(int)(y+(30/Component.scale)),w/4*3,(int)(h-(30/Component.scale)));
		panZones.update(x,(int)(y+(30/Component.scale)),w/4*3,(int)(h-(30/Component.scale)));
		panOnglets.update(x, y, w/4*3, 10);
		panResources.update(x+panStructures.w, y, w-panStructures.w, h);
	}
	
	public void render(){
		Renderer.renderQuad(x, y, w, h, new float[]{0,0,1,0.6f});
		if(structPage.isSelected()){
			panStructures.render();
		}
		if(zonesPage.isSelected()){
			panZones.render();
		}
		
		panResources.render();
		panOnglets.render();
	}
		

}