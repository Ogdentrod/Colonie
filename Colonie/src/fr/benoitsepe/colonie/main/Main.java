package fr.benoitsepe.colonie.main;

import org.lwjgl.Sys;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
 
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// Je t'ai mis un exemple de création d'une éolienne, lance le programme pour voir
		
		Gestion moteur = new Gestion(); // Init moteur
		
		
		moteur.creerStruct(TypeStructures.EOLIENNE, 0, 0); // Tu peux en créer plusieurs si tu veux, j'ai pas encore implémenté les autres structures
		moteur.creerStruct(TypeStructures.PANNEAU_SOLAIRE, 0, 0);
		moteur.creerStruct(TypeStructures.REFECTOIRE, 0, 0);
        System.out.println("Hello LWJGL " + Sys.getVersion() + "!");	
        }
}
