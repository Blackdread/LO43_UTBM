package lo43_TicketToRide.engine.partie;

import java.io.Serializable;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class Challenge implements Serializable{

	private static final long serialVersionUID = -2546823565778080558L;

	protected int points;

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

	/**
	 * Cherche a rejoindre les deux villes par les routes posseder par le joueur qui a cette couleur
	 * @param carte la carte qui contient les villes et routes
	 * @param colorOfPlayer couleur du joueur
	 * @return true si reussi
	 */
	public boolean isChallengeSucceeded(final CarteJeu carte, final int colorOfPlayer){
		return carte.isPossibleToJoinTwoVille(depart, arrivee, colorOfPlayer, null);
	}
	
	public int getPoints() {
		return points;
	}

	public Ville getArrivee() {
		return arrivee;
	}

	public Ville getDepart() {
		return depart;
	}
	
	

}