package lo43_TicketToRide.engine.partie;

import java.io.Serializable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import lo43_TicketToRide.engine.IRenderable;
import lo43_TicketToRide.utils.Colors;


/**
 * Je suis pas designer... (voir render)
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class Route implements IRenderable, Serializable{
  
	/**
	 * 
	 */
	private static final long serialVersionUID = -6066784805826120870L;
	protected static final int WIDTH_MIN_ROUTE = 10;
	protected static final int WIDTH_MAX_ROUTE = 30;

	protected int nbWagonNecessaire;

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
	
	public int getCouleurNecessaireRoute() {
		return couleurNecessaireRoute;
	}

	public Route(Route copy) {
		this.nbWagonNecessaire = copy.nbWagonNecessaire;
		this.couleurNecessaireRoute = copy.couleurNecessaireRoute;
		if(copy.possederPar != null)
			this.possederPar = new Joueur(copy.possederPar);
		this.ville1 = new Ville(copy.ville1);
		this.ville2 = new Ville(copy.ville2);
	}
	
	@Override
	public void render(Graphics g,final int deltaX,final int deltaY) {
		Rectangle tmp = new Rectangle(0,0,calculerLongueurDesRectangles(),20);
		Rectangle tmpJoueur = new Rectangle(0,0,calculerLongueurDesRectangles(),20);
		// tmp.transform(Transform.createRotateTransform(calculerAngleEntreDeuxVilles()));
		//g.rotate(ville1.x+deltaX, ville1.y+deltaY, Game.rot);
		g.rotate(ville1.x+deltaX, ville1.y+deltaY, 360-calculerAngleEntreDeuxVilles());
		for(int i=0;i<nbWagonNecessaire;++i){
			//TODO A verifier
			//*
			
			if(ville1.x <= ville2.x){
				tmp.setLocation(ville1.x+deltaX+tmp.getWidth()*i+2*i, ville1.y+deltaY);
				tmpJoueur.setLocation(ville1.x+deltaX+tmp.getWidth()*i+2*i, ville1.y+deltaY);
				//if(0 == i)
				//	System.out.println("1<r:"+nbWagonNecessaire+" "+ville1.nomUV+" "+ville2.nomUV);
			}else{
				// TODO ATTENTION
				tmp.setLocation(ville2.x+deltaX+tmp.getWidth()*i+2*i, ville2.y+deltaY);
				tmpJoueur.setLocation(ville2.x+deltaX+tmp.getWidth()*i+2*i, ville2.y+deltaY);
				//if(0 == i)
				//	System.out.println("2>r:"+nbWagonNecessaire+" "+ville1.nomUV+" "+ville2.nomUV);
			}
			//*/
			
			//g.rotate(ville1.getX(), ville1.getY(), calculerAngleEntreDeuxVilles());
			
			//g.rotate(tmp.getX()+tmp.getWidth()/2, tmp.getY()+tmp.getHeight()/2, calculerAngleEntreDeuxVilles());
			
			//g.fill(tmp.transform(Transform.createRotateTransform(calculerAngleEntreDeuxVilles()*180.0f)));
			//g.fill(tmp.transform(Transform.createRotateTransform((float) Math.toRadians(calculerAngleEntreDeuxVilles()))));
			g.setColor(Colors.getColor(couleurNecessaireRoute));
			g.fill(tmp);
			if(possederPar != null){
				g.setColor(Colors.getColor(possederPar.color));
				g.fill(tmpJoueur);
				g.setColor(Color.black);
				g.draw(tmpJoueur);
			}
			//if(0 == i)
			//	g.drawString("r:"+nbWagonNecessaire+" "+ville1.nomUV+" "+(360-calculerAngleEntreDeuxVilles()), tmp.getX()-30, tmp.getY()-22);
			
		}
		g.resetTransform();
		//System.out.println("route rotate:"+calculerAngleEntreDeuxVilles()+" num: "+ville1.getNomUV()+" "+ville2.getNomUV());
	}
	
	protected final int calculerLongueurDesRectangles(){
		//int deltaY = (int) Math.pow(ville1.getY() - ville2.getY(), 2);
		//int deltaX = (int) Math.pow(ville1.getX() - ville2.getX(), 2);
		
		int hypo = (int) Math.hypot(ville1.getX() - ville2.getX(),ville1.getY() - ville2.getY());
		return hypo / nbWagonNecessaire;
	}
	
	public String getNomVille1(){
		return ville1.nomUV;
	}
	public String getNomVille2(){
		return ville2.nomUV;
	}
	
	/**
	 * Faudra revoir le calcul des angles, j'ai repris ce code d'un ancien projet tres vieux, donc 
	 * je sais pas s'il est vraiment juste, c'est presque juste mais il est fait pour des rotations d'images (du a slick)
	 * @return
	 */
	protected final float calculerAngleEntreDeuxVilles(){
		
		//TODO A verifier
		//int deltaY = (int) (ville1.getY() - ville2.getY());
		
		int deltaX = (int) (ville1.getX() - ville2.getX());
		float hypo = (float) Math.hypot(ville1.getX() - ville2.getX(),ville1.getY() - ville2.getY());
		
		//return (float) Math.acos(deltaX/hypo);
		//return (float) (Math.toDegrees(Math.acos(deltaX/hypo)));
		//*
		if(ville1.getX() >= ville2.getX() && ville1.getY() >= ville2.getY()){
			return (float)(Math.toDegrees(Math.acos( deltaX/hypo )));
		}else if(ville1.getX() >= ville2.getX() && ville1.getY() <= ville2.getY()){
			return -((float)(Math.toDegrees(Math.acos( deltaX/hypo ))));
		}else if(ville1.getX() <= ville2.getX() && ville1.getY() >= ville2.getY()){
			return (float)(180 - (Math.toDegrees(Math.acos( deltaX/hypo ))));
		}else{
			// Normalement, si ce cas se produit, c'est qu'il y a une bonne raison et on a alors x > destX et y < destY
			return (float)(180 + Math.abs((Math.toDegrees(Math.acos( deltaX/hypo )))));
		}
		//*/
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
	 * @return true si un joueur possede la route
	 */
	public boolean isRoutePosseder(){
		if(possederPar == null)
			return false;
		return true;
	}
	
	/**
	 * Retourne la distance du point (x,y) par rapport a la Line de cette route
	 * Get the shortest distance from a point to this line
	 * @param x
	 * @param y
	 * @return The distance from the line to the point
	 */
	public float distance(final int x,final int y){
		return (new Line(ville1.x,ville1.y,ville2.x,ville2.y)).distance(new Vector2f(x,y));
	}
	
	public int getNbWagonNecessaire() {
		return nbWagonNecessaire;
	}

	/**
	 * On ne verifie pas la personne qui possede la route
	 */
	@Override
	public boolean equals(Object route){
		if(route instanceof Route){
			if(nbWagonNecessaire== ((Route)route).nbWagonNecessaire &&
			couleurNecessaireRoute == ((Route)route).couleurNecessaireRoute &&
			ville1 == ((Route)route).ville1 &&
			ville2 == ((Route)route).ville2)
				return true;
		}
		return false;
		
	}


}