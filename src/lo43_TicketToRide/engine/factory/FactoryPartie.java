package lo43_TicketToRide.engine.factory;

import lo43_TicketToRide.engine.partie.Partie;


public class FactoryPartie {
	
  private static FactoryPartie factory;

  private FactoryPartie(){}
  
  public FactoryPartie getInstance(){
	  if(factory == null)
		  factory = new FactoryPartie();
	  return factory;
  }
  
  public Partie creerPartie(Integer nbJoueur) {
	  Partie partie = new Partie();
	  switch(nbJoueur){
	  case 2:
		  
		  break;
	  case 3:
		  
		  break;
	  case 4:
		  
		  break;
	  case 5:
		  
		  break;
	  }
  return null;
  }

}