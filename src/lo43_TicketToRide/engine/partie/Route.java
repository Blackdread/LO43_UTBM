package lo43_TicketToRide.engine.partie;


/**
 * 
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class Route {
  

	protected Integer nbWagonNecessaire;

	protected int couleurNecessaireRoute;

	protected Joueur possederPar;
	protected Ville ville1;
	protected Ville ville2;
  
	public Route(){
		
	}
	
	public Route(Route copy) {
		this.nbWagonNecessaire = copy.nbWagonNecessaire;
		this.couleurNecessaireRoute = copy.couleurNecessaireRoute;
		this.possederPar = copy.possederPar;
		this.ville1 = new Ville(copy.ville1);
		this.ville2 = new Ville(copy.ville2);
	}


	public void ajouterPossesseur(Joueur joueur) {
		
	}

}