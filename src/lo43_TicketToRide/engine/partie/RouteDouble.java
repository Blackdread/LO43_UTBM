package lo43_TicketToRide.engine.partie;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class RouteDouble extends Route {

  protected int couleurNecessaireRoute2;

  protected Joueur possederPar2;

  public RouteDouble(Ville v1, Ville v2, int color, int wagon, int color2) {
		super(v1, v2, color, wagon);
		couleurNecessaireRoute2 = color2;
	}
  
  @Override
  public boolean ajouterPossesseur(Joueur joueur) {
	  if(super.possederPar == null)
		  return super.ajouterPossesseur(joueur);
	  else
		  if(possederPar2 == null){
				possederPar = joueur;
				return true;
			}
	  return false;
  }
 
  /**
   * Surcharge la methode mere et ne regarde pas si la 1ere est possede
   */
  @Override
  public boolean isRoutePosseder(){
		if(possederPar2 == null)
			return false;
		return true;
	}

}