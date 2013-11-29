package lo43_TicketToRide.engine.partie;

import java.util.Vector;

import org.newdawn.slick.Graphics;

import lo43_TicketToRide.engine.IRenderable;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class CarteJeu implements IRenderable{


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

  
	@Override
	public void render(Graphics g,final int deltaX,final int deltaY) {
		for(Route v : routes)
			if(v != null)
				v.render(g, deltaX, deltaY);
		
		for(Ville v : villes)
			if(v != null)
				v.render(g, deltaX, deltaY);
		//System.out.println("Fin render CarteJeu\n\n");
	}
	
	synchronized public void ajouterVille(Ville ville){
		villes.add(ville);
	}
	
	synchronized public void ajouterRoute(Route route){
		routes.add(route);
	}
	
	synchronized public int getRouteSize(){
		return routes.size();
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

	synchronized public void setVillePos(final int pos,final int x,final int y){
		if(pos >= 0 && pos < villes.size()){
			villes.get(pos).setLocation(x, y);
		}
	}
  
}