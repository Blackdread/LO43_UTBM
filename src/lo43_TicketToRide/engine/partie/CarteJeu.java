package lo43_TicketToRide.engine.partie;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import org.newdawn.slick.Graphics;

import lo43_TicketToRide.engine.IRenderable;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class CarteJeu implements IRenderable, Serializable{

	private static final long serialVersionUID = -1724428464393291062L;
	/**
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
	 * Cherche a rejoindre les deux villes par les routes posseder par le joueur qui a cette couleur.
	 * C'est une fonction recursive qui chercher a relier les deux villes, ne passe jamais par la meme ville
	 * @param a ville de depart
	 * @param b ville arrivee
	 * @param villeVisitee A mettre a null sur le premier appel
	 * @return
	 */
	public boolean isPossibleToJoinTwoVille(final Ville a, final Ville b, final int colorOfPlayer, Vector<Ville> villeVisitee){
		if(villeVisitee == null)
			villeVisitee = new Vector<Ville>();
		
		if(a.equals(b))
			return true;
		// TODO pas sur que ce soit juste l''algo ecrit ici
		ArrayList<Boolean> results = new ArrayList<Boolean>();
		
		for(Route v : getRouteRelieAVille(a))
			if(v != null){
				if(!a.equals(v.ville1))
					if(!villeVisitee.contains(v.ville1))//{ // si pas deja visitee
						continue;
				if(!a.equals(v.ville2))
					if(!villeVisitee.contains(v.ville2))
						continue;
					
					villeVisitee.add(a);
					if(v.possederPar != null){
						if(v.possederPar.color == colorOfPlayer){
							if(a.equals(v.ville1))
								results.add(isPossibleToJoinTwoVille(v.ville2,b,colorOfPlayer,villeVisitee));
							else
								results.add(isPossibleToJoinTwoVille(v.ville1,b,colorOfPlayer,villeVisitee));
						}
					}
					if(v instanceof RouteDouble)
						if(((RouteDouble)v).possederPar2 != null){
							if(((RouteDouble)v).possederPar2.color == colorOfPlayer){
								if(a.equals(v.ville1))
									results.add(isPossibleToJoinTwoVille(v.ville2,b,colorOfPlayer,villeVisitee));
								else
									results.add(isPossibleToJoinTwoVille(v.ville1,b,colorOfPlayer,villeVisitee));
							}
						}
				//}
			}
		for(Boolean k : results)
			if(k == true)
				return true;
		return false;
	}
	
	/**
	 * Retourne une liste des routes relier a la ville passee en parametre
	 * @param ville
	 * @return
	 */
	private Vector<Route> getRouteRelieAVille(Ville ville){
		Vector<Route>  tmp = new Vector<Route>();
		for(Route v : routes)
			if(v != null)
				if(v.ville1.equals(ville) || v.ville2.equals(ville))
					tmp.add(v);
		return tmp;
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
	
	/**
	 * Pour connaitre les routes que le joueur peut prendre avec ses cartes (et route non prise)
	 * @param joueur
	 * @return
	 */
	synchronized public Vector<Route> getRoutePossibleDePrendre(Joueur joueur){
		Vector<Route>  tmp = new Vector<Route>();
		for(Route v : routes)
			if(v != null ){
				if(v instanceof Route){
					if(v.isRoutePosseder())
						continue;
					if(v.nbWagonNecessaire <= joueur.compterNbCarteDeTelleCouleur(v.couleurNecessaireRoute))
						tmp.add(v);
				}
				if(v instanceof RouteDouble){
					if(((RouteDouble)v).possederPar2 != null)
						continue;
					if(v.nbWagonNecessaire <= joueur.compterNbCarteDeTelleCouleur(v.couleurNecessaireRoute))
						tmp.add(v);
				}
			}
		return tmp;
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