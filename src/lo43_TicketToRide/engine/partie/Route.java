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
  
	public Route(Ville v1, Ville v2, int color, int wagon){
		nbWagonNecessaire = wagon;
		couleurNecessaireRoute = color;
		ville1 = v1;
		ville2 = v2;
	}
	
	public Route(Route copy) {
		this.nbWagonNecessaire = copy.nbWagonNecessaire;
		this.couleurNecessaireRoute = copy.couleurNecessaireRoute;
		this.possederPar = copy.possederPar;
		this.ville1 = new Ville(copy.ville1);
		this.ville2 = new Ville(copy.ville2);
	}


	public boolean ajouterPossesseur(Joueur joueur) {
		if(possederPar == null){
			possederPar = joueur;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return true si un joueur a possede la route
	 */
	public boolean isRoutePosseder(){
		if(possederPar == null)
			return false;
		return true;
	}

}