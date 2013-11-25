package lo43_TicketToRide.engine.factory;

import lo43_TicketToRide.engine.partie.CarteJeu;
import lo43_TicketToRide.engine.partie.Route;
import lo43_TicketToRide.engine.partie.RouteDouble;
import lo43_TicketToRide.engine.partie.Ville;

public class FactoryCarteJeu {

  private static FactoryCarteJeu factory;

  private FactoryCarteJeu(){
	  
  }
  
  public FactoryCarteJeu getInstance(){
	  if(factory == null)
		  factory = new FactoryCarteJeu();
	  return factory;
  }
  
  /**
   * Pourra contenir un switch pour avoir plusieurs carte mais on en fait une
   * (on peut aller plus loin en faisant un editeur puis serialiser le tout)
   * @return
   */
  public CarteJeu creerCarteJeu() {
	  //switch(carte){
  	  //}
	  CarteJeu ret = new CarteJeu();
	  
	  for(int i=0;i<13;++i)
		  ret.ajouterVille(new Ville("ville"+i));
	  
	  ret.ajouterRoute(new Route(ret.getVilleAt(0), ret.getVilleAt(1), 0, 4));
	  ret.ajouterRoute(new RouteDouble(ret.getVilleAt(1), ret.getVilleAt(2), 0, 3,0));
	  ret.ajouterRoute(new Route(ret.getVilleAt(2), ret.getVilleAt(3), 0, 2));
	  ret.ajouterRoute(new RouteDouble(ret.getVilleAt(3), ret.getVilleAt(4), 0, 6,0));
	  ret.ajouterRoute(new Route(ret.getVilleAt(3), ret.getVilleAt(8), 0, 5));
	  
	  ret.ajouterRoute(new Route(ret.getVilleAt(4), ret.getVilleAt(8), 0, 3));
	  ret.ajouterRoute(new Route(ret.getVilleAt(8), ret.getVilleAt(12), 0, 2));
	  ret.ajouterRoute(new RouteDouble(ret.getVilleAt(12), ret.getVilleAt(11), 0, 7, 0));
	  ret.ajouterRoute(new Route(ret.getVilleAt(11), ret.getVilleAt(7), 0, 1));
	  ret.ajouterRoute(new Route(ret.getVilleAt(7), ret.getVilleAt(6), 0, 2));
	  
	  ret.ajouterRoute(new RouteDouble(ret.getVilleAt(6), ret.getVilleAt(2), 0, 5,0));
	  ret.ajouterRoute(new Route(ret.getVilleAt(11), ret.getVilleAt(10), 0, 3));
	  ret.ajouterRoute(new Route(ret.getVilleAt(10), ret.getVilleAt(9), 0, 1));
	  ret.ajouterRoute(new Route(ret.getVilleAt(10), ret.getVilleAt(5), 0, 2));
	  ret.ajouterRoute(new Route(ret.getVilleAt(9), ret.getVilleAt(5), 0, 3));
	  
	  ret.ajouterRoute(new Route(ret.getVilleAt(5), ret.getVilleAt(1), 0, 4));
	  
	  
	  return ret;
  }

}