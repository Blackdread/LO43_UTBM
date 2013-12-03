package lo43_TicketToRide.engine;

public final class Regles {

	public static final int NB_MAX_JOUEUR = 5;
	public static final int NB_MAX_CARTE_RETOURNEE = 5;
	
	public static final int NB_MAX_CARTE_RETOURNEE_PIOCHER_PAR_TOUR = 2;
	public static final int NB_MAX_CARTE_DECK_PIOCHER_PAR_TOUR = 2;
	
	/**
	 *  Ne prends pas en compte le joker (locomotive)
	 */
	public static final int NB_CARTE_COULEUR_DIFFERENTE = 8;
	
	public static final int NB_CARTE_WAGON_PAR_COULEUR = 12;
	public static final int NB_CARTE_WAGON_JOKER_POUR_UNE_PARTIE = 14;
	/**
	 * Prends en compte le nombre JOKER
	 */
	public static final int NB_TOTAL_CARTE_WAGON_POUR_UNE_PARTIE = NB_CARTE_WAGON_PAR_COULEUR * 
			NB_CARTE_COULEUR_DIFFERENTE + NB_CARTE_WAGON_JOKER_POUR_UNE_PARTIE;
	
	
	/**
	 * 
	 */
	public static final int NB_WAGON_PAR_JOUEUR = 5;
	
	
	public static final int NB_CARTE_CHALLENGE = 30;
	
	
	public static final int NB_CARTE_CHALLENGE_PAR_JOUEUR_DEBUT_PARTIE = 3;
	public static final int NB_CARTE_WAGON_PAR_JOUEUR_DEBUT_PARTIE = 4;
}
