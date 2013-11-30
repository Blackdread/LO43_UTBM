package lo43_TicketToRide.engine.factory;

import lo43_TicketToRide.engine.partie.CarteJeu;
import lo43_TicketToRide.engine.partie.Route;
import lo43_TicketToRide.engine.partie.RouteDouble;
import lo43_TicketToRide.engine.partie.Ville;
import lo43_TicketToRide.utils.Colors;

/**
 *  @Annotation Toujours essayer de metter ville1 tel que ville1.x <= ville2.x
   * Tres important
 * @author Yoann CAPLAIN
 *
 */
public class FactoryCarteJeu {

  private static FactoryCarteJeu factory;

  private FactoryCarteJeu(){
	  
  }
  
  public static FactoryCarteJeu getInstance(){
	  if(factory == null)
		  factory = new FactoryCarteJeu();
	  return factory;
  }
  
  /**
   * Pourra contenir un switch pour avoir plusieurs carte mais on en fait une
   * (on peut aller plus loin en faisant un editeur puis serialiser le tout)
   * @Annotation Toujours essayer de metter ville1 tel que ville1.x <= ville2.x
   * Tres important
   * @return
   */
  public CarteJeu creerCarteJeu() {
	  CarteJeu ret = new CarteJeu();
	  //switch(numCarte){
  	  
	 
	  //default:
	  ret.ajouterVille(new Ville("LO41"));
	  ret.ajouterVille(new Ville("LO43"));
	  ret.ajouterVille(new Ville("LO44"));
	  ret.ajouterVille(new Ville("RE43"));
	  ret.ajouterVille(new Ville("RE42"));
	  
	  ret.ajouterVille(new Ville("LO41"));
	  ret.ajouterVille(new Ville("LO41"));
	  ret.ajouterVille(new Ville("LO41"));
	  ret.ajouterVille(new Ville("LO41"));
	  ret.ajouterVille(new Ville("LO41"));
	  
	  ret.ajouterVille(new Ville("XE03"));
	  ret.ajouterVille(new Ville("XC00"));
	  ret.ajouterVille(new Ville("X00"));
	  
	  int xx = 4;
	  ret.setVillePos(0, 10*xx, 50);
	  ret.setVillePos(1, 40*xx, 20);
	  ret.setVillePos(2, 100*xx, 20);
	  ret.setVillePos(3, 160*xx, 20);
	  ret.setVillePos(4, 220*xx, 20);
	  
	  ret.setVillePos(5, 40*xx, 250);
	  ret.setVillePos(6, 100*xx, 250);
	  ret.setVillePos(7, 160*xx, 250);
	  ret.setVillePos(8, 220*xx, 250);
	  
	  ret.setVillePos(9, 40*xx, 440);
	  ret.setVillePos(10, 100*xx, 440);
	  ret.setVillePos(11, 160*xx, 440);
	  ret.setVillePos(12, 220*xx, 440);
	  
	  ret.ajouterRoute(new Route(ret.getVilleAt(0), ret.getVilleAt(1), Colors.getColorId(CarteType.Blue), 4));
	  ret.ajouterRoute(new RouteDouble(ret.getVilleAt(1), ret.getVilleAt(2), Colors.getColorId(CarteType.Red), 3,0));
	  //ret.ajouterRoute(new Route(ret.getVilleAt(2), ret.getVilleAt(3), Colors.getColorId(CarteType.Joker), 2));
	  ret.ajouterRoute(new Route(ret.getVilleAt(2), ret.getVilleAt(3), Colors.getColorId(CarteType.Green), 2));
	  ret.ajouterRoute(new RouteDouble(ret.getVilleAt(3), ret.getVilleAt(4), Colors.getColorId(CarteType.White), 6,0));
	  ret.ajouterRoute(new Route(ret.getVilleAt(3), ret.getVilleAt(8), Colors.getColorId(CarteType.Yellow), 5));
	  
	  //ret.ajouterRoute(new Route(ret.getVilleAt(4), ret.getVilleAt(8), Colors.getColorId(CarteType.Joker), 3));
	  ret.ajouterRoute(new Route(ret.getVilleAt(4), ret.getVilleAt(8), Colors.getColorId(CarteType.White), 3));
	  ret.ajouterRoute(new Route(ret.getVilleAt(8), ret.getVilleAt(12), Colors.getColorId(CarteType.Pink), 2));
	  ret.ajouterRoute(new RouteDouble(ret.getVilleAt(12), ret.getVilleAt(11), Colors.getColorId(CarteType.Blue), 7, 0));
	  //ret.ajouterRoute(new Route(ret.getVilleAt(11), ret.getVilleAt(7), Colors.getColorId(CarteType.Joker), 1));
	  ret.ajouterRoute(new Route(ret.getVilleAt(11), ret.getVilleAt(7), Colors.getColorId(CarteType.Pink), 1));
	  ret.ajouterRoute(new Route(ret.getVilleAt(7), ret.getVilleAt(6), Colors.getColorId(CarteType.Green), 2));
	  
	  ret.ajouterRoute(new RouteDouble(ret.getVilleAt(6), ret.getVilleAt(2), Colors.getColorId(CarteType.Blue), 5,0));
	  ret.ajouterRoute(new Route(ret.getVilleAt(11), ret.getVilleAt(10), Colors.getColorId(CarteType.White), 3));
	  ret.ajouterRoute(new Route(ret.getVilleAt(10), ret.getVilleAt(9), Colors.getColorId(CarteType.Green), 1));
	  // La route qui suit n'etait pas bien afficher car ville1 et ville2 etaient inverses
	  ret.ajouterRoute(new Route(ret.getVilleAt(5), ret.getVilleAt(10), Colors.getColorId(CarteType.Black), 2));
	  ret.ajouterRoute(new Route(ret.getVilleAt(9), ret.getVilleAt(5), Colors.getColorId(CarteType.Red), 3));
	  
	  ret.ajouterRoute(new Route(ret.getVilleAt(5), ret.getVilleAt(1), Colors.getColorId(CarteType.Black), 4));
	  
	//}
	  return ret;
  }

}