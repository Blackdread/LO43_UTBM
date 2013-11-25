package lo43_TicketToRide.engine.partie;

import java.util.Vector;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class CarteJeu {


  /**
   * 
   * @element-type Ville
   */
	protected Vector<Ville>  villes = new Vector<Ville>();
   /**
   * 
   * @element-type Route
   */
	protected Vector<Route>  routes = new Vector<Route>();

  
	synchronized public void ajouterVille(Ville ville){
		villes.add(ville);
	}
	
	synchronized public void ajouterRoute(Route route){
		routes.add(route);
	}
	
	/**
	 * 
	 * @return a copy
	 */
	synchronized public Vector<Route> getRoutes(){
		return new Vector<Route>(routes);
	}
	/**
	 * 
	 * @return a copy
	 */
	synchronized public Vector<Ville> getVilles(){
		return new Vector<Ville>(villes);
	}
	
	synchronized public Route getRouteAt(int pos){
		if(pos >= 0 && pos < routes.size())
			return routes.get(pos);
		return null;
	}
	
	synchronized public Ville getVilleAt(int pos){
		if(pos >= 0 && pos < villes.size())
			return villes.get(pos);
		return null;
	}
  
}