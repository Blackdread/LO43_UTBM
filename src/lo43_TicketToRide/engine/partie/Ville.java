package lo43_TicketToRide.engine.partie;

/**
 * Represente une UV
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class Ville {
  
  protected String nomUV;

  
  public Ville(String nom){
	  
  }
  
  public Ville(Ville copy){
	  nomUV = copy.nomUV;
  }

	public String getNomUV() {
		return nomUV;
	}
  
  
  
}