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
  protected Vector<Challenge>  challenges;
    /**
   * 
   * @element-type CarteWagon
   */
  protected Vector<CarteWagon>  cartes;
  
  public Joueur(){
	  
  }
  
  public Joueur(final Joueur copy){
	  pseudo = copy.pseudo;
	  color = copy.color;
	  score = copy.score;
	  nbWagon = copy.nbWagon;
	  isIA = copy.isIA;
	  challenges = getChallenges();
	  cartes = getCartes();
  }

   synchronized public String getPseudo() {
		return pseudo;
	}
	
   synchronized public int getColor() {
		return color;
	}
	
	synchronized public int getScore() {
		return score;
	}
	
	synchronized public int getNbWagon() {
		return nbWagon;
	}
	
	synchronized public boolean isIA() {
		return isIA;
	}
	
	/**
	 * 
	 * @return a copy
	 */
	synchronized public Vector<Challenge> getChallenges() {
		return new Vector<Challenge>(challenges);
	}
	/**
	 * 
	 * @return a copy
	 */
	synchronized public Vector<CarteWagon> getCartes() {
		return new Vector<CarteWagon>(cartes);
	}
	
	synchronized public void ajouterCarteWagon(CarteWagon a){
		cartes.add(a);
	}
	
	synchronized public void ajouterChallenge(Challenge a){
		challenges.add(a);
	}
	
	synchronized public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	synchronized public void setColor(int color) {
		this.color = color;
	}
	
	synchronized public void setScore(int score) {
		this.score = score;
	}
	
	synchronized public void setNbWagon(int nbWagon) {
		this.nbWagon = nbWagon;
	}
	
	synchronized public void setIA(boolean isIA) {
		this.isIA = isIA;
	}
   
}