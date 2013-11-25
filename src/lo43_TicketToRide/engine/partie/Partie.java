package lo43_TicketToRide.engine.partie;

import java.util.Vector;

import lo43_TicketToRide.engine.IUpdatable;
import lo43_TicketToRide.engine.Regles;
import lo43_TicketToRide.utils.Timer;

/**
 * Contient tous ce qui se trouve dans une partie
 * 
 * @Annotation Les verifications sur piocher cartes, poser routes, etc pourraient etre fait differemment,
 * avec une classe qui contient des methodes et renvoit des boolean s'il l'action est possible.
 * Mais ce jeu ne contient qu'un seul type de regle donc pas necessaire ici.
 * Par contre si ce jeu vient a evoluer (ajout des extensions du jeu original) il sera necessaire de revoir
 * le systeme de gestion d'autorisation a faire telle ou telle action
 * 
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class Partie implements IUpdatable {

  private static final int TEMPS_MAX_PAR_TOUR = 120000;
  protected Timer tempsDeJeu, tempsMaxParTour;

   /**
   * 
   * @element-type Challenge
   */
  protected Vector<Challenge>  challenges = new Vector<Challenge>();
  protected CarteJeu carte;
   /**
   * 
   * @element-type Joueur
   */
  protected static Vector<Joueur>  vectJoueurs = new Vector<Joueur>(Regles.NB_MAX_JOUEUR);
   /**
   * 
   * @element-type CarteWagon
   */
  protected Vector<CarteWagon>  deckDeCarte = new Vector<CarteWagon>();
  /**
   * 
   * @element-type CarteWagon
   */
  protected Vector<CarteWagon>  defausse = new Vector<CarteWagon>();
   /**
   * 
   * @element-type CarteWagon
   */
  protected Vector<CarteWagon> carteRetournee = new Vector<CarteWagon>();
  protected Joueur tourDuJoueur;

  /*
   * PossibilitŽs lors d'un tour
   */
  protected int compteurCarteDeckPiocher = 0;
  protected int compteurCarteRetourneePiocher = 0;
  protected boolean carteChallengesPiocher = false;
  protected boolean routePoser = false;
  
  public Partie(){
	  tempsDeJeu = new Timer(0);
	  tempsMaxParTour = new Timer(TEMPS_MAX_PAR_TOUR);
  }
  
  @Override
  synchronized public void update(int delta) {
	  tempsDeJeu.update(delta);
	  tempsMaxParTour.update(delta);
	  
	  if(tempsMaxParTour.isTimeComplete()){
		  tempsMaxParTour.resetTime();
		  finTourJoueur();
	  }
		  
  }
  
  public Boolean isGameOver() {
  return null;
  }

  public void checkGameOver() {
	  
  }
  
  /**
   * 
   * @return a copy
   */
  synchronized public final Joueur getTourDuJoueur() {
	  return new Joueur(tourDuJoueur);
  }

  synchronized public final void ajouterJoueur(Joueur a){
	  vectJoueurs.add(a);
  }
  
  /**
   * Ajoute un challenge dans le vector
   * @param a
   */
  synchronized public final void ajouterChallenge(Challenge a){
	  challenges.add(a);
  }
  
  /**
   * Passe au joueur suivant et initialise les actions possibles d'un tour
   */
  synchronized private void finTourJoueur() {
	  int index = vectJoueurs.indexOf(tourDuJoueur, 0);
	  if(index >= vectJoueurs.size()-1)
		  tourDuJoueur = vectJoueurs.get(0);
	  else
		  tourDuJoueur = vectJoueurs.get(index+1);
	  
	  compteurCarteDeckPiocher = 0;
	  compteurCarteRetourneePiocher = 0;
	  carteChallengesPiocher = false;
	  routePoser = false;
  }
  
  /**
   * Pioche une carte et l'ajoute au tourDujoueur actuel
   * @Annotation Verification par rapport aux regles fais
   */
  synchronized public void piocherCarteDeck() {
	  if(deckDeCarte.size() > 0){
		  if(tourDuJoueur != null && compteurCarteDeckPiocher < 2 && compteurCarteRetourneePiocher < 2
				  && !carteChallengesPiocher && !routePoser){
			  
			  defausse.add(deckDeCarte.get(0));
			  tourDuJoueur.ajouterCarteWagon(deckDeCarte.remove(0));
			  compteurCarteDeckPiocher +=1;
		  }
	  }
  }
  
  /**
   * Pioche une carte a la position du vector et l'ajoute au tourDujoueur actuel
   * et ajoute une carte retournee depuis le deck
   * @Annotation Verification par rapport aux regles fais
   * @param pos Position carte
   */
  synchronized public void piocherCarteRetournee(int pos) {
	  if(carteRetournee.size() > 0 && pos >= 0 && pos < carteRetournee.size() && tourDuJoueur != null
			  && !carteChallengesPiocher && !routePoser){
		  CarteWagon tmp = carteRetournee.get(pos);
		  if(tmp instanceof CarteWagonJoker){
			  if(compteurCarteDeckPiocher > 0 || compteurCarteRetourneePiocher > 0)
				  return;
			  else{
				  compteurCarteRetourneePiocher += 2;
				  defausse.add(tmp);
				  tourDuJoueur.ajouterCarteWagon(carteRetournee.remove(pos));
				  ajouterCarteManquanteRetournee();
				  return;
			  }
		  }
		  
		  if(compteurCarteDeckPiocher < 2 && compteurCarteRetourneePiocher < 2){
			  defausse.add(tmp);
			  tourDuJoueur.ajouterCarteWagon(carteRetournee.remove(pos));
			  compteurCarteRetourneePiocher += 1;
			  ajouterCarteManquanteRetournee();
		  }
	  }
  }
  
  /**
   * Ajoute les challenges au tourDuJoueur et remet au bas du deck les challenges non pris
   * On sait qu'il y a forcement 1 challenge de pris obligatoirement (non tester ici)
   * Position des cartes piochŽes (va de 0 ˆ 2 inclus)
   * @param a position 0
   * @param b position 1
   * @param c position 2
   */
  synchronized public final void piocherChallengesSelectionne(boolean a, boolean b, boolean c){
	  if(tourDuJoueur != null && compteurCarteDeckPiocher == 0 && compteurCarteRetourneePiocher == 0
			  && !carteChallengesPiocher && !routePoser && challenges.size() >= 1){
		  if(challenges.size() >= 3){
			  if(c)
				  tourDuJoueur.ajouterChallenge(challenges.remove(2));
			  else
				  challenges.add(challenges.remove(2));
		  }
		  if(challenges.size() >= 2){
			  if(b)
				  tourDuJoueur.ajouterChallenge(challenges.remove(1));
			  else
				  challenges.add(challenges.remove(1));
		  }
		  if(challenges.size() >= 1){
			  if(a)
				  tourDuJoueur.ajouterChallenge(challenges.remove(0));
			  else
				  challenges.add(challenges.remove(0));
		  }
		  //* Le mettre ???
		  if(!a && !b && !c)
			  tourDuJoueur.ajouterChallenge(challenges.remove(0));
		  //*/
		  carteChallengesPiocher = true;
	  }
  }
  
  /**
   * 
   * @param pos
   * @return a copy
   */
  synchronized public final Joueur getJoueurAt(int pos){
	  if(pos >= 0 && pos < vectJoueurs.size())
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

  synchronized private final void ajouterCarteManquanteRetournee() {
	  if(carteRetournee.size() == Regles.NB_MAX_CARTE_RETOURNEE || deckDeCarte.size() == 0)
		  return;
	  carteRetournee.add(deckDeCarte.remove(0));
	  ajouterCarteManquanteRetournee();
  }
  
  /**
   * Remet les cartes et appelle une fonction recursif pour melanger les cartes
   */
  synchronized private final void remettreDansDeckCarteDeLaDefausse(){
	  if(deckDeCarte.size() == 0){
		  remettreDansDeckCarteDeLaDefausseRecursif();
	  }
  }
  
  synchronized private final void remettreDansDeckCarteDeLaDefausseRecursif(){
	  if(defausse.size() >= 1){
		  deckDeCarte.add(defausse.remove( (int)(Math.random()*defausse.size()) ));
	  }else
		  return;
	  remettreDansDeckCarteDeLaDefausseRecursif();
  }

}