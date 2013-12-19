package lo43_TicketToRide.engine.factory;

import lo43_TicketToRide.engine.partie.IA;

public class FactoryIA {
	public static enum NiveauIA{facile,normal,difficile}
	
	private static FactoryIA factory;

	  private FactoryIA(){
		  
	  }
	  
	  public static FactoryIA getInstance(){
		  if(factory == null)
			  factory = new FactoryIA();
		  return factory;
	  }
	  
	  public IA creerIA(NiveauIA niveauDifficulte) {
		  IA ret;
		  switch(niveauDifficulte){
		  case facile:
			  	ret = new IA("IA",NiveauIA.facile);
			  break;
		  case normal:
			  	ret = new IA("IA",NiveauIA.normal);
			  break;
		  case difficile:
			  	ret = new IA("IA",NiveauIA.difficile);
			  break;
		  	default:
		  		ret = new IA("IA",NiveauIA.facile);
		  }
		  return ret;
	  }
}
