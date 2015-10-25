package fr.kienanbachwa.colonie.affichage;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Fenetre extends BasicGame
{
	public GameContainer gc;
	String texte = "Surprise!";
	Image background;
	Image playerImage;
	private MapLoader mapLoader;
	
	private float y;
	private float delta = 5;
	private float x;
	
	private boolean[] direction = new boolean[4];
	
	public Fenetre(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		this.gc=gc;
		background = new Image("ressources/cage.jpg");
		playerImage = new Image("ressources/cage.png");
		mapLoader = new MapLoader();
		System.out.println(mapLoader.getMap().getTileId(1,1,0));
		
	}

	public void keyReleased(int key, char c){
		if (Input.KEY_ESCAPE==key){
			gc.exit();
		}
		
		 switch (key) {
		 
	        case Input.KEY_UP:    this.direction[0] = false; break;
	        case Input.KEY_LEFT:  this.direction[1] = false; break;
	        case Input.KEY_DOWN:  this.direction[2] = false; break;
	        case Input.KEY_RIGHT: this.direction[3] = false; break;
	    }
	}
	
	public void keyPressed(int key, char c){
			if(key==14){
				if(texte.length()>0)
				texte=texte.substring(0,texte.length()-1);
			}else{
				texte=texte+c;
			}
			
			 switch (key) {
			 
		        case Input.KEY_UP:    this.direction[0] = true; break;
		        case Input.KEY_LEFT:  this.direction[1] = true; break;
		        case Input.KEY_DOWN:  this.direction[2] = true; break;
		        case Input.KEY_RIGHT: this.direction[3] = true; break;
		    }
	}
	
	
	@Override
	public void update(GameContainer gc, int i) throws SlickException {
		if(this.direction[0]){
			this.y -= .1f * delta;
		}
		if(this.direction[1]){
			this.x -= .1f * delta;
		}
		if(this.direction[2]){
			this.y += .1f * delta;
		}
		if(this.direction[3]){
			this.x += .1f * delta;
		}
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
		/*
		background.draw(0,0,640,480);
		g.setColor(Color.red);
		g.scale(1.5f,1.5f);
		g.drawString(texte, 180, 80);
		*/
		mapLoader.getMap().render(0, 0);
		g.drawImage(playerImage, x, y);
	}
}