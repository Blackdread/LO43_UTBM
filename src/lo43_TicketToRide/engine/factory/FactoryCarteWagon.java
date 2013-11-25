package lo43_TicketToRide.engine.factory;

import lo43_TicketToRide.engine.partie.CarteWagon;
import lo43_TicketToRide.engine.partie.CarteWagonJoker;
import lo43_TicketToRide.utils.Colors;

public class FactoryCarteWagon {

  private static FactoryCarteWagon factory;

  private FactoryCarteWagon(){}
  
  public FactoryCarteWagon getInstance(){
	  if(factory == null)
		  factory = new FactoryCarteWagon();
	  return factory;
  }
  
  public CarteWagon creerCarte(CarteType type) {
	  switch(type){
	  case Pink:
		return new CarteWagon(Colors.PINK);
	  case White:
		return new CarteWagon(Colors.BLANC);
	  case Blue:
		return new CarteWagon(Colors.BLUE);
	  case Yellow:
		return new CarteWagon(Colors.YELLOW);
	  case Orange:
		return new CarteWagon(Colors.ORANGE);
	  case Black:
		  return new CarteWagon(Colors.NOIR);
	  case Red:
		  return new CarteWagon(Colors.RED);
	  case Green:
		  return new CarteWagon(Colors.GREEN);
	  case Joker:
		  return new CarteWagonJoker();
	  default:
		  return new CarteWagonJoker(); 
	  }
  }

}