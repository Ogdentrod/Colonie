package fr.kienanbachwa.colonie.jeu;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import fr.kienanbachwa.colonie.graphics.Hud;

import static org.lwjgl.opengl.GL11.*;

public class Component {
	
	public static float scale = 3;
	public static int width = (int) (1024 / scale);
	public static int height = (int) (576 / scale);
	public boolean running = false;
	
	DisplayMode mode = new DisplayMode((int)(width * scale), (int)(height * scale));
	int time = 0;
	
	public static boolean tick = false;
	public static boolean render = false;
	
	public static String title = "TEH BEST GAME EVAH";
	
	Game game;
	private int wheel;
	Hud hud;
	
	public Component(){
		display();
		game = new Game();
		hud = new Hud();
	}
	
	public void display(){
		try {
			Display.setDisplayMode(mode);
			Display.setResizable(true);
			Display.setFullscreen(false);
			Display.setTitle(title);
			Display.create();
			
			view2D(width, height);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	private void view2D(int width, int height) {
		glViewport(0, 0, (int)(width * scale), (int)(height * scale));
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluOrtho2D(0, width, height, 0);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		
		glEnable(GL_TEXTURE_2D);
		
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public void start(){
		running=true;
		loop();
	}
	public void tick(){
		wheel = Mouse.getDWheel();
		if((Keyboard.isKeyDown(Keyboard.KEY_ADD) || wheel>0) && scale<10)Component.scale+=0.1f;
		if((Keyboard.isKeyDown(Keyboard.KEY_SUBTRACT) || wheel<0) && Component.scale>1) Component.scale-=0.1f;
		
		time++;
		game.update();
		hud.update();		
	}
	
	public void loop(){
		
		game.init();
		hud.init();
		
		long timer = System.currentTimeMillis();
		
		long before = System.nanoTime();
		double elapsed = 0;
		double nanoSeconds = 1000000000.0/60.0;
		
		int ticks = 0;
		int frames = 0;
		
		while(running){
			if(Display.isCloseRequested()) stop();
			Display.update();
			
			width = (int)(Display.getWidth() / scale);
			height = (int)(Display.getHeight() / scale);
			
			tick = false;
			render = false;
			
			long now = System.nanoTime();
			elapsed = now-before;
			
			if(elapsed > nanoSeconds){
				before += nanoSeconds;
				tick = true;
				ticks++;
			}else{
				render = true;
				frames++;
			}
			
			if(tick) tick();
			if(render) render();
			
			if(System.currentTimeMillis() - timer > 1000){
				timer +=1000;
				Display.setTitle("ticks:"+ticks+" FPS:"+frames+" | "+title);
				ticks = 0;
				frames = 0;
			}
		}
		exit();
	}
	
	public void render(){
		view2D(width , height);
		glClear(GL_COLOR_BUFFER_BIT);
		game.render();
		//view2D(width*scale, height*scale);
		hud.render();
	}
	
	public void stop(){
		running=false;
	}
	
	public void exit(){
		Display.destroy();
		System.exit(0);
	}

	
	
}
