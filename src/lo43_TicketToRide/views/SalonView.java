package lo43_TicketToRide.views;

import lo43_TicketToRide.engine.Game;

/**
 * C'est cense etre la vue ou les joueurs sont lorsqu'ils attendent les joueurs de rejoindre la partie mais je
 * ne pense pas garder ca car pas envie de faire l'interface donc je vais utiliser le MainMultiView
 * @author Yoann CAPLAIN
 *
 */
public class SalonView extends View {

	@Override
	public void initResources() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getID() {
		return Game.SALON_VIEW_ID;
	}

}
