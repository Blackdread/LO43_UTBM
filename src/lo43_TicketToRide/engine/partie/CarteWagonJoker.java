package lo43_TicketToRide.engine.partie;

import lo43_TicketToRide.utils.Colors;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 14 11 2013
 * 
 */
public class CarteWagonJoker extends CarteWagon {
	
	private static final long serialVersionUID = 3393642683536023035L;

	public CarteWagonJoker(){
		super.color = Colors.GRIS;
	}
	
	public CarteWagonJoker(CarteWagonJoker copy){
		super(copy);
	}
	
}