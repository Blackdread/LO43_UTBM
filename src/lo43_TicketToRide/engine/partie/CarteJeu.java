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
	
	/**
	 * Retourne la route la plus proche du x,y
	 * @param x
	 * @param y
	 * @return a copy sinon null si distance superieur a 30.0f
	 */
	synchronized public Route getRouteLaPlusProcheDuPoint(final int x,final int y){
		//int i=0;
		float min=9999999.0f, calc = 999999.0f;
		Route tmp = null;
		for(Route v : routes){
			if(v != null){
				calc = v.distance(x, y);
				if(calc < min && calc < 30.0f){
					min = calc;
					//i+=1;
					tmp=v;
					//System.out.println("Routes cliquer: "+v.getNomVille1()+" "+v.getNomVille2()+" i"+i);
				}
			}
		}
		return tmp;
		//return routes.get(i);
		/*
		if(i<0)
			return null;
		
		if(routes.get(i) instanceof RouteDouble)
			return new RouteDouble((RouteDouble)(routes.get(i)));
		return new Route(routes.get(i));
		//*/
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