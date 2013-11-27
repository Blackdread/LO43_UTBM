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

	private static final int TEMPS_MAX_PAR_TOUR = 6000; // <= pour les tests
  //private static final int TEMPS_MAX_PAR_TOUR = 100000;
  protected Timer tempsDeJeu, tempsMaxParTour;

   /**
   * 
   * @element-type Challenge
   */
  protected Vector<Challenge>  challenges = new Vector<Challenge>(Regles.NB_CARTE_CHALLENGE);
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
  protected Vector<CarteWagon>  deckDeCarte = new Vector<CarteWagon>(Regles.NB_TOTAL_CARTE_WAGON_POUR_UNE_PARTIE);
  /**
   * 
   * @element-type CarteWagon
   */
  protected Vector<CarteWagon>  defausse = new Vector<CarteWagon>();
   /**
   * 
   * @element-type CarteWagon
   */
  protected Vector<CarteWagon> carteRetournee = new Vector<CarteWagon>(Regles.NB_MAX_CARTE_RETOURNEE);
  protected Joueur tourDuJoueur;

  /*
   * Possibilités lors d'un tour
   */
  /**
   * Max 2
   */
  protected int compteurCarteDeckPiocher = 0;
  /**
   * Max 2
   */
  protected int compteurCarteRetourneePiocher = -11110;
  protected boolean carteChallengesPiocher = false;
  protected boolean routePoser = false;
  
  public Partie(){
	  tempsDeJeu = new Timer(999999999);
	  tempsMaxParTour = new Timer(TEMPS_MAX_PAR_TOUR);
  }
  
  @Override
  synchronized public void update(int delta) {
	  //if(carte != null && tourDuJoueur != null){
		  tempsDeJeu.update(delta);
		  tempsMaxParTour.update(delta);
	  //}
	  
	  if(tempsMaxParTour.isTimeComplete()){
		  tempsMaxParTour.resetTime();
		  finTourJoueur();
	  }
	  //System.out.println("Update partie");
	  
	  checkFinTourJoueur();
		  
  }
  
  /**
   * Mettre les cartes retournees
   * Donner les cartes challenges aux joueurs
   * Donner les cartes wagon aux joueurs
   * Initialiser  tourDuJoueur
   */
  synchronized public void initialiserPartie(){
	  melangerDeckDeCarte();
	  melangerChallenges();
	  
	  ajouterCarteManquanteRetournee();
	  verifierNbCarteJokerRetournee();
	  
	  int tmp = challenges.size();
	  if(tmp < vectJoueurs.size()*Regles.NB_CARTE_CHALLENGE_PAR_JOUEUR_DEBUT_PARTIE){
		  System.err.println(" Pas assez de challenge !!");
		  // On fait planter le jeu...
	  }else{
		  tmp = vectJoueurs.size();
		  for(int k=0;k<Regles.NB_CARTE_CHALLENGE_PAR_JOUEUR_DEBUT_PARTIE;++k)
			  for(int i=0;i<tmp;++i)
				  vectJoueurs.get(i).ajouterChallenge(challenges.remove(0));
	  }//*
	  tmp = vectJoueurs.size();
	  for(int k=0;k<Regles.NB_CARTE_WAGON_PAR_JOUEUR_DEBUT_PARTIE;++k)
		  for(int i=0;i<tmp;++i)
			  vectJoueurs.get(i).ajouterCarteWagon(deckDeCarte.remove(0));
	  //*/
	  System.out.println("vect "+vectJoueurs.get(0).pseudo+" "+vectJoueurs.get(0).getColor()+" taille "+vectJoueurs.get(0).getCartes().size());
	  tourDuJoueur = vectJoueurs.get(0);
  }
  
  public boolean isGameOver() {
	  return false;// TODO
  }

  public void checkGameOver() {
	  // TODO
  }
  
  synchronized public final void setCarteJeu(CarteJeu carte){
	  this.carte = carte;
  }
  
  /**
   * 
   * @return a copy
   */
  synchronized public final Joueur getTourDuJoueur() {
	  if(tourDuJoueur == null)
		  return null;
	  return new Joueur(tourDuJoueur);
  }
  
  /**
   * 
   * @return a copy (pas pour le moment)
   */
  synchronized public CarteJeu getCarteJeu() {
	 /* if(carte == null)
		  return null;
	  return new Joueur(carte);
	  */
	  return carte;
  }

  synchronized public final void ajouterJoueur(Joueur a){
	  vectJoueurs.add(a);
  }
  
  synchronized public final int getDeckSize(){
	  return deckDeCarte.size();
  }
  synchronized public final int getDeckChallengeSize(){
	  return challenges.size();
  }
  
  /**
   * Ajoute un challenge dans le vector
   * @param a
   */
  synchronized public final void ajouterChallenge(Challenge a){
	  challenges.add(a);
  }
  synchronized public final void ajouterChallenge(Vector<Challenge> a){
	  challenges.addAll(a);
  }
  
  synchronized public final void ajouterCarteWagon(CarteWagon a){
	  deckDeCarte.add(a);
  }
  
  /**
   * Verifie si le tour du joueur est fini si oui alors on passe au joueur suivant
   */
  synchronized private void checkFinTourJoueur(){
	  
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
	  
	// TODO Verifier si fonction fini
  }
  
  /**
   * Pioche une carte et l'ajoute au tourDujoueur actuel
   * @Annotation Verification par rapport aux regles fais
   */
  synchronized public void piocherCarteDeck() {
	  if(deckDeCarte.size() > 0){
		  if(tourDuJoueur != null && compteurCarteDeckPiocher < 2 && compteurCarteRetourneePiocher < 2
				  && !carteChallengesPiocher && !routePoser){
			  
			  //defausse.add(deckDeCarte.get(0));
			  tourDuJoueur.ajouterCarteWagon(deckDeCarte.remove(0));
			  compteurCarteDeckPiocher +=1;
		  }
	  }
	  remettreDansDeckCarteDeLaDefausse();
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
				  //defausse.add(tmp);
				  tourDuJoueur.ajouterCarteWagon(carteRetournee.remove(pos));
				  ajouterCarteManquanteRetournee();
				  verifierNbCarteJokerRetournee();
				  return;
			  }
		  }
		  
		  if(compteurCarteDeckPiocher < 2 && compteurCarteRetourneePiocher < 2){
			  //defausse.add(tmp);
			  tourDuJoueur.ajouterCarteWagon(carteRetournee.remove(pos));
			  compteurCarteRetourneePiocher += 1;
			  ajouterCarteManquanteRetournee();
			  verifierNbCarteJokerRetournee();
		  }
	  }
	  remettreDansDeckCarteDeLaDefausse();
  }
  
  synchronized public Vector<Challenge> copierChallengePourSelectionnerCeuxAPiocher(){
	  Vector<Challenge> cha = new Vector<Challenge>(3);
	  for(int i=0;i<3 && i< challenges.size();++i)
		  cha.add(new Challenge(challenges.get(i)));
	return cha;
  }
  /**
   * Ajoute les challenges au tourDuJoueur et remet au bas du deck les challenges non pris
   * On sait qu'il y a forcement 1 challenge de pris obligatoirement
   * Position des cartes piochées (va de 0 à 2 inclus)
   * @param a position 0
   * @param b position 1
   * @param c position 2
   */
  synchronized public final void piocherChallengesSelectionne(boolean a, boolean b, boolean c){
	  if(tourDuJoueur != null && compteurCarteDeckPiocher == 0 && compteurCarteRetourneePiocher <= 0
			  && !carteChallengesPiocher && !routePoser && challenges.size() >= 1){ //le <= c juste pour les tests
		  //System.out.println("PiocheChallenge inside");
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
	  remettreDansDeckCarteDeLaDefausse();
	 // System.out.println("PiocheChallenge fin");
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
   * @param color La couleur du joueur (unique)
   * @return a copy
   */
  synchronized public final Joueur getJoueurByColor(int color){
	  for(int i=0;i<vectJoueurs.size();++i)
		  if(vectJoueurs.get(i).getColor() == color)
			  return new Joueur(vectJoueurs.get(i));
	  return null;
  }
  
  /**
   * 
   * @param pos
   * @return a copy
   */
  synchronized public final CarteWagon getCarteRetourneeAt(int pos){
	  if(pos >= 0 && pos < carteRetournee.size()){
		  // Etrange d'etre oblige de faire ca...
		  // En faite c'est logique car je fais un new et CarteWagonJoker herite de CarteWagon donc il peut
		  // etre transformer en CarteWagon et perdre son "instance" de CarteWagonJoker
		  // TODO voir avec prof pour eviter d'etre oblige de faire ca
		  // carteRetournee.get(pos).getClass().newInstance() => c'est peut etre ca
		  if(carteRetournee.get(pos) instanceof CarteWagonJoker)
			  return new CarteWagonJoker((CarteWagonJoker) carteRetournee.get(pos));
		  return new CarteWagon(carteRetournee.get(pos));
	  }
	  System.err.println("getCarteRetourneeAt: erreur avec pos="+pos
			  +"\nEst nomrmalement declenche si le deck est vide => normal");
	  return null;
  }

  synchronized public final boolean isPiocherChallengeOK(){
	  if(tourDuJoueur != null && compteurCarteDeckPiocher == 0 && compteurCarteRetourneePiocher <= 0
			  && !carteChallengesPiocher && !routePoser && challenges.size() >= 1)
		  return true;
	  return false;
  }
  
  synchronized private final void ajouterCarteManquanteRetournee() {
	  remettreDansDeckCarteDeLaDefausse();
	  
	  if(carteRetournee.size() == Regles.NB_MAX_CARTE_RETOURNEE || deckDeCarte.size() == 0){
		  //System.out.println("ajouterCarteManquanteRetournee: return");
		  return;
	  }
	  carteRetournee.add(deckDeCarte.remove(0));
	  ajouterCarteManquanteRetournee();
	  //System.out.println("ajouterCarteManquanteRetournee: fin");
  }
  
  /**
   * Si, au cours du jeu, 3 cartes visibles sur 5 sont des locomotives, les 5 cartes
   * sont alors immeediatement defaussées et remplacées par 5 nouvelles cartes.
   */
  synchronized private final void verifierNbCarteJokerRetournee(){
	  int somme = 0;
	  if(carteRetournee.size() == 5)
		  for(int i=0;i<5;++i)
			  if(carteRetournee.get(i) instanceof CarteWagonJoker)
				  somme+=1;
	  if(somme >= 3){
		  for(int i=0;i<5;++i)
			  defausse.add(carteRetournee.remove(0));
		  ajouterCarteManquanteRetournee();
	  }
  }
  
  /**
   * Melange les cartes
   */
  synchronized private void melangerDeckDeCarte(){
	  Vector<CarteWagon>  tmp = new Vector<CarteWagon>(deckDeCarte);
	  System.out.println("melangerDeckDeCarte: "+deckDeCarte.size());
	  deckDeCarte.removeAllElements();
	  
	  while(tmp.size()>0)
		  deckDeCarte.add(tmp.remove((int) (Math.random()*tmp.size()) ));
  }
  
  /**
   * Melange les cartes
   */
  synchronized private void melangerChallenges(){
	  Vector<Challenge>  tmp = new Vector<Challenge>(challenges);
	  System.out.println("melangerChallenges: "+challenges.size());
	  challenges.removeAllElements();
	  
	  while(tmp.size()>0)
		  challenges.add(tmp.remove((int) (Math.random()*tmp.size()) ));
  }
  
  /**
   * Remet les cartes si le deck EST VIDE et appelle une fonction recursif pour melanger les cartes
   */
  synchronized private final void remettreDansDeckCarteDeLaDefausse(){
	  if(deckDeCarte.size() == 0){
		  remettreDansDeckCarteDeLaDefausseRecursif();
	  }
  }
  
  /**
   * Remet les cartes de facon recursif et melange
   */
  synchronized private final void remettreDansDeckCarteDeLaDefausseRecursif(){
	  if(defausse.size() >= 1){
		  deckDeCarte.add(defausse.remove( (int)(Math.random()*defausse.size()) ));
	  }else
		  return;
	  remettreDansDeckCarteDeLaDefausseRecursif();
  }
  
  synchronized public final Timer getTempsDeJeu(){
	  return this.tempsDeJeu;
  }
  synchronized public final Timer getTempsMaxParTour(){
	  return this.tempsMaxParTour;
  }


}