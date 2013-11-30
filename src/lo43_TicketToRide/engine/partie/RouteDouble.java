package lo43_TicketToRide.engine.partie;

import lo43_TicketToRide.utils.Colors;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

/**
 * Je suis pas designer... (voir render)
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class RouteDouble extends Route {

  protected int couleurNecessaireRoute2;

  protected Joueur possederPar2;

  public int getCouleurNecessaireRoute2() {
	return couleurNecessaireRoute2;
}

public RouteDouble(Ville v1, Ville v2, int color, int wagon, int color2) {
		super(v1, v2, color, wagon);
		couleurNecessaireRoute2 = color2;
	}
  
  public RouteDouble(RouteDouble copy) {
	  super(copy);
	  couleurNecessaireRoute2 = copy.couleurNecessaireRoute2;
	  if(copy.possederPar2 != null)
		  possederPar2 = new Joueur(copy.possederPar2);
  }

  /**
   * Ajoute d'abord le possesseur pour la route simple sinon ajoute pour la route double
   */
  @Override
  public boolean ajouterPossesseur(Joueur joueur) {
	  if(super.possederPar == null)
		  return super.ajouterPossesseur(joueur);
	  else
		  if(possederPar2 == null){
			  possederPar2 = joueur;
			  return true;
			}
	  return false;
  }
  /**
   * 
   * @return un boolean[2] true si route posseder -> 0 route ; 1 -> routeDouble
   */
  public boolean[] getRoutePosserder(){
	  boolean a[] = new boolean[2];
	  if(possederPar != null)
		  a[0] = true;
	  if(possederPar2 != null)
		  a[1] = true;
	  return a;
  }
  
  @Override
	public void render(Graphics g, int deltaX, int deltaY) {
		// TODO Auto-generated method stub
	  	//TODO A faire
	  /*
	  if(calculerLonguerDesRectangles() > -45 && calculerLonguerDesRectangles() < 45){
		  super.render(g, deltaX, deltaY-10);
		  deltaY+=10;
	  }else{
		  super.render(g, deltaX-10, deltaY);
		  deltaX+=10;
	  }*/
	  g.setColor(Colors.getColor(couleurNecessaireRoute));
		Rectangle tmp = new Rectangle(0,0,calculerLonguerDesRectangles(),20);
		Rectangle tmpJoueur = new Rectangle(0,0,calculerLonguerDesRectangles(),20);
		g.rotate(ville1.x+deltaX, ville1.y+deltaY, 360-calculerAngleEntreDeuxVilles());
		for(int i=0;i<nbWagonNecessaire;++i){
			//TODO A verifier
			
			if(ville1.x <= ville2.x){
				tmp.setLocation(ville1.x+deltaX+tmp.getWidth()*i+2*i, ville1.y+deltaY-12);
				tmpJoueur.setLocation(ville1.x+deltaX+tmp.getWidth()*i+2*i, ville1.y+deltaY-12);
				//if(0 == i)
				//	System.out.println("1<r:"+nbWagonNecessaire+" "+ville1.nomUV+" "+ville2.nomUV);
			}else{
				// TODO ATTENTION
				tmp.setLocation(ville2.x+deltaX+tmp.getWidth()*i+2*i, ville2.y+deltaY-12);
				tmpJoueur.setLocation(ville2.x+deltaX+tmp.getWidth()*i+2*i, ville2.y+deltaY-12);
				//if(0 == i)
				//	System.out.println("2>r:"+nbWagonNecessaire+" "+ville1.nomUV+" "+ville2.nomUV);
			}
			
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
	  
		g.setColor(Colors.getColor(couleurNecessaireRoute2));
		
		g.rotate(ville1.x+deltaX, ville1.y+deltaY, 360-calculerAngleEntreDeuxVilles());
		for(int i=0;i<nbWagonNecessaire;++i){
			//TODO A verifier
			
			if(ville1.x <= ville2.x){
				tmp.setLocation(ville1.x+deltaX+tmp.getWidth()*i+2*i, ville1.y+deltaY+12);
				tmpJoueur.setLocation(ville1.x+deltaX+tmp.getWidth()*i+2*i, ville1.y+deltaY+12);
				//if(0 == i)
				//	System.out.println("1<r:"+nbWagonNecessaire+" "+ville1.nomUV+" "+ville2.nomUV);
			}else{
				// TODO ATTENTION
				tmp.setLocation(ville2.x+deltaX+tmp.getWidth()*i+2*i, ville2.y+deltaY+12);
				tmpJoueur.setLocation(ville2.x+deltaX+tmp.getWidth()*i+2*i, ville2.y+deltaY+12);
				//if(0 == i)
				//	System.out.println("2>r:"+nbWagonNecessaire+" "+ville1.nomUV+" "+ville2.nomUV);
			}
			
			g.setColor(Colors.getColor(couleurNecessaireRoute2));
			g.fill(tmp);
			if(possederPar2 != null){
				g.setColor(Colors.getColor(possederPar2.color));
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
 
	  /**
	   * Surcharge la methode mere et ne regarde pas si la 1ere est possede
	   */
	  @Override
	  public boolean isRoutePosseder(){
		  if(possederPar2 == null)
			  return false;
		  return true;
		}
  
  	/**
	 * On ne verifie pas la personne qui possede la route
	 */
  	@Override
	public boolean equals(Object route){
		if(route instanceof RouteDouble){
			if(super.equals(route) && couleurNecessaireRoute2 == ((RouteDouble)route).couleurNecessaireRoute2)
				return true;
		}
		return false;
		
	}

}