package lo43_TicketToRide.engine.partie;

import java.util.Vector;

import lo43_TicketToRide.engine.IUpdatable;
import lo43_TicketToRide.engine.Regles;
import lo43_TicketToRide.utils.Timer;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class Partie implements IUpdatable {


  protected Timer tempsDeJeu;

    /**
   * 
   * @element-type Challenge
   */
  protected Vector<Challenge>  challenges;
  protected CarteJeu carte;
   /**
   * 
   * @element-type Joueur
   */
  protected static Vector<Joueur>  vectJoueurs;
    /**
   * 
   * @element-type CarteWagon
   */
  protected Vector<CarteWagon>  deckDeCarte;
    /**
   * 
   * @element-type CarteWagon
   */
  protected Vector<CarteWagon> carteRetournee;
  protected Joueur tourDuJoueur;

  public Partie(){
	  tempsDeJeu = new Timer(0);
  }
  
  @Override
  public void update(int delta) {
	  tempsDeJeu.update(delta);
  }
  
  public Boolean isGameOver() {
  return null;
  }

  public void checkGameOver() {
	  
  }

  public Joueur getTourDuJoueur() {
  return null;
  }

  public void finTourJoueur() {
  }

  /**
   * Pioche une carte et l'ajoute au tourDujoueur actuel
   */
  public void piocherCarteDeck() {
	  if(deckDeCarte.size() > 0){
		  if(tourDuJoueur != null)
			  tourDuJoueur.ajouterCarteWagon(deckDeCarte.remove(0));
		  else
			  System.err.println("Function piocherCarteDeck : tourDuJoueur == null");
	  }
  }
  
  /**
   * Pioche une carte a la position du vector et l'ajoute au tourDujoueur actuel
   * @param pos Position carte
   */
  public void piocherCarteRetournee(int pos) {
	  if(carteRetournee.size() > 0 && pos >= 0 && pos < carteRetournee.size()){
		  if(tourDuJoueur != null)
			  tourDuJoueur.ajouterCarteWagon(carteRetournee.remove(pos));
		  else
			  System.err.println("Function piocherCarteRetournee : tourDuJoueur == null");
	  }
  }
  
  /**
   * 
   * @param pos
   * @return a copy
   */
  synchronized public final Joueur getJoueurAt(int pos){
	  if(pos > 0 && pos < vectJoueurs.size())
		  return new Joueur(vectJoueurs.get(pos));
	  return null;
  }
  
  /**
   * 
   * @param pos
   * @return a copy
   */
  synchronized public final CarteWagon getCarteRetourneeAt(int pos){
	  if(pos > 0 && pos < carteRetournee.size()){
		  return new CarteWagon(carteRetournee.get(pos));
	  }
	  return null;
  }

  synchronized private void ajouterCarteManquanteRetournee() {
	  if(carteRetournee.size() == Regles.NB_MAX_CARTE_RETOURNEE || deckDeCarte.size() == 0)
		  return;
	  carteRetournee.add(deckDeCarte.remove(0));
	  ajouterCarteManquanteRetournee();
  }

  

}