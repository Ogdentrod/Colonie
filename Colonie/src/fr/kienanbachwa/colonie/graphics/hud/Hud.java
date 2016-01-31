package fr.kienanbachwa.colonie.graphics.hud;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Mouse;

import fr.benoitsepe.colonie.ressources.TypeRessources;
import fr.benoitsepe.colonie.structures.TypeStructures;
import fr.benoitsepe.colonie.zone.TypeZones;
import fr.kienanbachwa.colonie.graphics.Color;
import fr.kienanbachwa.colonie.graphics.Renderer;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.OngletButton;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.ResourceDisplayer;
import fr.kienanbachwa.colonie.graphics.hud.panelObjects.StructButton;
import fr.kienanbachwa.colonie.graphics.hud.panels.Panel;
import fr.kienanbachwa.colonie.graphics.hud.panels.PanelGrid;
import fr.kienanbachwa.colonie.graphics.hud.panels.PanelHorizontal;
import fr.kienanbachwa.colonie.graphics.hud.panels.PanelVertical;
import fr.kienanbachwa.colonie.jeu.Component;

public class Hud {
	
	List<StructButton> buttons = new ArrayList<StructButton>();
	List<TypeRessources> ressources = new ArrayList<TypeRessources>();
	List<TypeZones> zones = new ArrayList<TypeZones>();


	public static boolean mouseOnHud;
	public static TypeStructures elementClicked = TypeStructures.BATIMENT;
	
	int x,y,w,h;
	PanelGrid panStructures;
	PanelGrid panZones;
	PanelVertical panResources;
	PanelHorizontal panOnglets;
	
	OngletButton structPage;
	OngletButton zonesPage;
	private boolean debug = true;;
	
	public static int hudTileSize = 16;
	
	
	public Hud(){
		w=Component.width;
		h=(Component.height/16)*4;
		
		panStructures = new PanelGrid(x,y-10,w,h);
		panZones = new PanelGrid(x,y-10,w,h);
		panResources = new PanelVertical(x+panStructures.w,y,16,16);
		panOnglets = new PanelHorizontal(x,y,w,h-(Hud.hudTileSize+2)*2);

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
		
		panStructures.init();
		panZones.init();
		
		this.debug();


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
		
		panStructures.update(x, y+h-(Hud.hudTileSize+1)*2, (Hud.hudTileSize+2)*14, h- (h-(Hud.hudTileSize+1)*2));
		panZones.update(x, y+h-(Hud.hudTileSize+1)*2, (Hud.hudTileSize+2)*14, h - (h-(Hud.hudTileSize+1)*2) );
		
		panOnglets.update(x, y, (Hud.hudTileSize+1)*14, h-(Hud.hudTileSize+1)*2);
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
		
		Renderer.renderQuadSimple(x, y, w, h, Color.RED);

		if(debug){
			Renderer.renderQuadSimple(x, y, w, 1, Color.RED);
			Renderer.renderQuadSimple(x, y, 1, h, Color.RED);
			Renderer.renderQuadSimple(x+w, y, 1, h, Color.RED);
			Renderer.renderQuadSimple(x, y+h, w, 1, Color.RED);
		}
		
	}
	
	public void debug(){
		debug = true;
	}
		

}