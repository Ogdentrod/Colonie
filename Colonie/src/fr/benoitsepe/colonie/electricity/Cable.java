package fr.benoitsepe.colonie.electricity;

public class Cable extends Elec {
	
	protected double resistanceLigne;
	protected double section;
	protected double intensiteMax;
	
	
	public Cable(double resistanceLigne) {
		super();
		this.resistanceLigne = resistanceLigne;
	}
	
	public double transmettre(Cable cable_precedent) {
		
		double resistanceInterne = resistanceLigne / section;
		intensite = cable_precedent.getIntensite() - tension / resistanceInterne;
		
		return intensite;
	}

}
