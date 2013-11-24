package lo43_TicketToRide.engine.partie;

import java.util.Vector;



/**
 * 
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class Joueur {

  protected String pseudo;
  protected int color;
  protected int score;
  protected int nbWagon;
  protected boolean isIA;
   /**
   * 
   * @element-type Challenge
   */
  protected Vector  challenges;
    /**
   * 
   * @element-type CarteWagon
   */
  protected Vector  cartes;
  
  public Joueur(){
	  
  }
  
   
}