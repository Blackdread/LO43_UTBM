package lo43_TicketToRide.engine.partie;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class Challenge {
	
	protected Integer points;

	protected Ville arrivee;
	protected Ville depart;
	
	public Challenge(int points, Ville arrivee, Ville depart) {
		this.points = points;
		this.arrivee = arrivee;
		this.depart = depart;
	}
	
	public Challenge(Challenge copy){
		points = copy.points;
		arrivee = new Ville(copy.arrivee);
		depart = new Ville(copy.depart);
	}
	

}