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
  
  public Joueur(final Joueur copy){
	  pseudo = copy.pseudo;
	  color = copy.color;
	  score = copy.score;
	  nbWagon = copy.nbWagon;
	  isIA = copy.isIA;
  }

	public String getPseudo() {
		return pseudo;
	}
	
	public int getColor() {
		return color;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getNbWagon() {
		return nbWagon;
	}
	
	public boolean isIA() {
		return isIA;
	}
	
	public Vector getChallenges() {
		return challenges;
	}
	
	public Vector getCartes() {
		return cartes;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void setNbWagon(int nbWagon) {
		this.nbWagon = nbWagon;
	}
	
	public void setIA(boolean isIA) {
		this.isIA = isIA;
	}
	
	public void setChallenges(Vector challenges) {
		this.challenges = challenges;
	}
	
	public void setCartes(Vector cartes) {
		this.cartes = cartes;
	}
  
   
}