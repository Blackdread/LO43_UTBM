package lo43_TicketToRide.engine.partie;

import java.util.Vector;

import lo43_TicketToRide.engine.IUpdatable;
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
  protected Vector  challenges;
  protected CarteJeu carte;
   /**
   * 
   * @element-type Joueur
   */
  protected static Vector  vectJoueurs;
    /**
   * 
   * @element-type CarteWagon
   */
  protected Vector  deckDeCarte;
    /**
   * 
   * @element-type CarteWagon
   */
  protected Vector  carteRetournee;
  protected Joueur tourDuJoueur;

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

  public void piocherCarteDeck() {
  }

  public void piocherCarteRetournee(CarteWagon carte) {
  }

  private void ajouterCarteManquanteRetournee() {
  }

@Override
public void update(int delta) {
	// TODO Auto-generated method stub
	
}

}