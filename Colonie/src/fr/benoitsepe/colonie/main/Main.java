package fr.benoitsepe.colonie.main;

import static org.lwjgl.glfw.GLFW.glfwGetVersionString;

import org.lwjgl.Sys;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		// Je t'ai mis un exemple de cr�ation d'une �olienne, lance le programme pour voir
		
		Gestion moteur = new Gestion(); // Init moteur
		
		
		moteur.creerStruct(TypeStructures.EOLIENNE); // Tu peux en cr�er plusieurs si tu veux, j'ai pas encore impl�ment� les autres structures
        
        System.out.println("Hello LWJGL " + Sys.getVersion() + "!");	
        System.out.println(glfwGetVersionString());
        
        
       
        
    
	}
}
