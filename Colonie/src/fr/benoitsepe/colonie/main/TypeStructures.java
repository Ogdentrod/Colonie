package fr.benoitsepe.colonie.main;

public enum TypeStructures {
	EOLIENNE(new Ressources(0, 0, 4, 0));/*
	PANNEAU_SOLAIRE(new Ressources(water, oxygen, iron, wood)),
	OXYGENE(new Ressources(water, oxygen, iron, wood)),
	REFECTOIRE(new Ressources(water, oxygen, iron, wood)),
	SAS(new Ressources(water, oxygen, iron, wood)),
	CONNEXION(new Ressources(water, oxygen, iron, wood)),
	COULOIR(new Ressources(water, oxygen, iron, wood));*/
	
	private Ressources res;
	
	private TypeStructures(Ressources res) {
		this.res = res;
	}
	
	public Ressources getRes() {
		return res;
	}
	
	
	
}
