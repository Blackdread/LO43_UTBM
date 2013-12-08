package lo43_TicketToRide.engine.partie;

import java.io.Serializable;
import java.util.Vector;

import lo43_TicketToRide.engine.IUpdatable;
import lo43_TicketToRide.engine.Regles;
import lo43_TicketToRide.engine.factory.CarteType;
import lo43_TicketToRide.utils.Colors;
import lo43_TicketToRide.utils.Timer;

/**
 * Contient tous ce qui se trouve dans une partie
 * 
 * Le plus simple pour changer les regles est de les faire dans les fonctions qui suivent et si on veut creer
 * une partie avec des regles differentes, il suffit d'hériter de la classe mere et de surcharger les methodes
 * en questions
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
public class Partie implements IUpdatable, Serializable {
	
	private static final long serialVersionUID = -4317180942233324696L;
	
	private static final int TEMPS_MAX_PAR_TOUR = 6000; // <= pour les tests TODO
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

  /**
   * True si c'est le dernier tour
   */
  protected boolean lastTurn;
  protected boolean gameIsOver;
  
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
  protected int compteurCarteRetourneePiocher = 0;
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
	  checkIsLastTurnToFire();
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
  
  
  synchronized public final boolean isGameIsOver() {
	  return gameIsOver;
  }

  /**
   * true si joueur.nbWagon <= 3
   */
  synchronized public final void checkIsLastTurnToFire() {
	  for(Joueur v : vectJoueurs)
		  if(v.nbWagon <= 3)
			  lastTurn = true;
  }
  
  synchronized protected void calculerLesPointsAtEndOfGame(){
	  //TODO pas fait
	  for(Joueur v : vectJoueurs){
		  for(Challenge o : v.getChallenges()){
			  
		  }
	  }
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
	  return new sdsdsdsds(carte);
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
	  if(routePoser || carteChallengesPiocher || compteurCarteDeckPiocher >= 2 || compteurCarteRetourneePiocher >= 2){
		  finTourJoueur();
	  }
  }
  
  /**
   * Passe au joueur suivant et initialise les actions possibles d'un tour
   * Si c'est le last turn => si on passe a nouveau au joueur 0 alors c'est fini
   */
  synchronized private void finTourJoueur() {
	  int index = vectJoueurs.indexOf(tourDuJoueur, 0);
	  if(index >= vectJoueurs.size()-1){
		  tourDuJoueur = vectJoueurs.get(0);
		  if(lastTurn){
			  gameIsOver = true;
			  calculerLesPointsAtEndOfGame();
			  // TODO Faire changer la vue des joueurs vers la vue de fin de jeu
			  // c'est la view qui check <= preferable que ce soit la vue
		  
		  }
	  }else
		  tourDuJoueur = vectJoueurs.get(index+1);
	  
	  compteurCarteDeckPiocher = 0;
	  compteurCarteRetourneePiocher = 0;
	  carteChallengesPiocher = false;
	  routePoser = false;
	  
	  tempsMaxParTour.resetTime();
	  
	  if(tourDuJoueur.isIA()){
		  // TODO appeller la fonction pour l'IA
	  }
	  
	// TODO Verifier si fonction fini
	  ajouterCarteManquanteRetournee(); // -> cas rare si toutes les cartes piocher mais personne les a utiliser
  }
  
  /**
   * Regarde les cartes du joueur et renvoi true si le joueur peut prendre une route
   * Donc n'a pas deja piocher des cartes, etc
   * Renvoi false si c'est une routeDouble que une des deux routes est prise et que le nb de joueur est <= 3
   * Verifie que le joueur a assez de wagon
   * @param routeAPrendre
   * @return
   */
  synchronized public boolean isPossibleToTakeRoad(final Route routeAPrendre){
	  if(!routePoser && compteurCarteDeckPiocher == 0 && !carteChallengesPiocher && compteurCarteRetourneePiocher == 0){
		  if(vectJoueurs.size() <= 3){
			  if(routeAPrendre instanceof RouteDouble){
				  //if(((Route)routeAPrendre).isRoutePosseder() || ((RouteDouble)routeAPrendre).isRoutePosseder())
				  if(((RouteDouble)routeAPrendre).getRoutePosserder()[0] == true || ((RouteDouble)routeAPrendre).getRoutePosserder()[1] == true)  
					  return false;
			  }
			  if(routeAPrendre.isRoutePosseder())
					  return false;
		  }
		  if(tourDuJoueur.getNbWagon() < routeAPrendre.nbWagonNecessaire)
			  return false;
		  
		  int color,color2;
		  color = routeAPrendre.couleurNecessaireRoute;
		  int nb = tourDuJoueur.compterNbCarteDeTelleCouleur(color);
		  int nb3 = tourDuJoueur.compterNbCarteDeTelleCouleur(Colors.getColorId(CarteType.Joker));
		  if(routeAPrendre instanceof RouteDouble){
			  color2 = ((RouteDouble) routeAPrendre).couleurNecessaireRoute2;
			  int nb2 = tourDuJoueur.compterNbCarteDeTelleCouleur(color2);
			  
			  if(nb + nb3 >= routeAPrendre.nbWagonNecessaire || nb2 + nb3 >= routeAPrendre.nbWagonNecessaire)
				  return true;
		  }else{
			  color = routeAPrendre.couleurNecessaireRoute;
			  nb = tourDuJoueur.compterNbCarteDeTelleCouleur(color);
			  
			  if(nb+ nb3 >= routeAPrendre.nbWagonNecessaire)
				  return true;
		  }
	  }
	  
	  return false;
  }
  
  /**
   * Cherche la route tel que : carte.getRouteAt(i).equals(routeAPrendre) si true il ajoute
   * carte.getRouteAt(i) le possesseur et met routePoser a true s'il a pu ajouter le possesseur
   * Et enleve les cartes utiliser
   * @param routeAPrendre Une copy de la route que l'on veut prendre
   */
  synchronized public void prendrePossessionRoute(final Route routeAPrendre){
	  boolean[] a = new boolean[2];
	  if(!routePoser){
		  int o = carte.getRouteSize();
		  for(int i=0;i<o;++i)
			  if(carte.getRouteAt(i).equals(routeAPrendre)){
				  
				  if(routeAPrendre instanceof RouteDouble)
					  a=((RouteDouble)routeAPrendre).getRoutePosserder();
				  
				  if(carte.getRouteAt(i).ajouterPossesseur(tourDuJoueur)){
					  routePoser = true;
					  
					  int color;
					  color = routeAPrendre.couleurNecessaireRoute;
					  int nb = tourDuJoueur.compterNbCarteDeTelleCouleur(color);
					  int nb3 = tourDuJoueur.compterNbCarteDeTelleCouleur(Colors.getColorId(CarteType.Joker));
					  if(routeAPrendre instanceof RouteDouble){
						  int color2 = ((RouteDouble) routeAPrendre).couleurNecessaireRoute2;
						  int nb2 = tourDuJoueur.compterNbCarteDeTelleCouleur(color2);
						  
						 if(a[0] != ((RouteDouble)routeAPrendre).getRoutePosserder()[0]){
							 // C la route simple qui a ete prise
							 if(nb >= routeAPrendre.nbWagonNecessaire){
								  retirerCarteDuJoueur(routeAPrendre.nbWagonNecessaire, color);
								  tourDuJoueur.retirerWagon(routeAPrendre.nbWagonNecessaire);
								  donnerPointsSuivantLongueurRoutePrise(routeAPrendre.nbWagonNecessaire);
								  break;
							  }
							  
							  if(nb + nb3 >= routeAPrendre.nbWagonNecessaire){
								  retirerCarteDuJoueur(nb,color);
								  retirerCarteDuJoueur(routeAPrendre.nbWagonNecessaire - nb,Colors.getColorId(CarteType.Joker));
								  tourDuJoueur.retirerWagon(routeAPrendre.nbWagonNecessaire);
								  donnerPointsSuivantLongueurRoutePrise(routeAPrendre.nbWagonNecessaire);
							  }
						 }else{
							 if(nb2 >= routeAPrendre.nbWagonNecessaire){
								  retirerCarteDuJoueur(routeAPrendre.nbWagonNecessaire, color2);
								  tourDuJoueur.retirerWagon(routeAPrendre.nbWagonNecessaire);
								  donnerPointsSuivantLongueurRoutePrise(routeAPrendre.nbWagonNecessaire);
								  break;
							  }
							  
							  if(nb2 + nb3 >= routeAPrendre.nbWagonNecessaire){
								  retirerCarteDuJoueur(nb2,color2);
								  retirerCarteDuJoueur(routeAPrendre.nbWagonNecessaire - nb2,Colors.getColorId(CarteType.Joker));
								  tourDuJoueur.retirerWagon(routeAPrendre.nbWagonNecessaire);
								  donnerPointsSuivantLongueurRoutePrise(routeAPrendre.nbWagonNecessaire);
							  }
						 }
						  
					  }else{
						  color = routeAPrendre.couleurNecessaireRoute;
						  nb = tourDuJoueur.compterNbCarteDeTelleCouleur(color);
						  
						  if(nb >= routeAPrendre.nbWagonNecessaire){
							  retirerCarteDuJoueur(routeAPrendre.nbWagonNecessaire, color);
							  tourDuJoueur.retirerWagon(routeAPrendre.nbWagonNecessaire);
							  donnerPointsSuivantLongueurRoutePrise(routeAPrendre.nbWagonNecessaire);
							  break;
						  }
						  
						  if(nb + nb3 >= routeAPrendre.nbWagonNecessaire){
							  retirerCarteDuJoueur(nb,color);
							  retirerCarteDuJoueur(routeAPrendre.nbWagonNecessaire - nb,Colors.getColorId(CarteType.Joker));
							  tourDuJoueur.retirerWagon(routeAPrendre.nbWagonNecessaire);
							  donnerPointsSuivantLongueurRoutePrise(routeAPrendre.nbWagonNecessaire);
						  }
					  }
				  }
				  break;
			  }
	  }
  }
  
  synchronized protected void donnerPointsSuivantLongueurRoutePrise(int longueur){
	  // TODO
	  switch(longueur){
	  case 1:
		  tourDuJoueur.ajouterPoint(1);
		  break;
	  case 2:
		  tourDuJoueur.ajouterPoint(2);
		  break;
	  case 3:
		  tourDuJoueur.ajouterPoint(4);
		  break;
	  case 4:
		  tourDuJoueur.ajouterPoint(7);
		  break;
	  case 5:
		  tourDuJoueur.ajouterPoint(10);
		  break;
	  case 6:
		  tourDuJoueur.ajouterPoint(15);
		  break;
	  case 7:
		  tourDuJoueur.ajouterPoint(22);
		  break;
	  case 8:
		  tourDuJoueur.ajouterPoint(30);
		  break;
	  }
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
   * Enleve les cartes du joueurs et les mets dans la defausse
   * @param nbDeCarteAEnleve
   * @param colorCarte
   */
  synchronized private void retirerCarteDuJoueur(int nbDeCarteAEnleve, int colorCarte){
	  for(int i=0;i<tourDuJoueur.getNbCarte();++i)
		  if(nbDeCarteAEnleve > 0){
			  if(tourDuJoueur.getCarteAt(i).color == colorCarte){
				  //System.out.println("carte retirer "+colorCarte+" et "+tourDuJoueur.getCarteAt(i).color );
				  nbDeCarteAEnleve -=1;
				  deckDeCarte.add(tourDuJoueur.getCarteAt(i));
				  tourDuJoueur.retirerCarteAt(i);
			  }
		  }else
			  break;
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
		  if(carteRetournee.get(pos) instanceof CarteWagonJoker)
			  return new CarteWagonJoker((CarteWagonJoker) carteRetournee.get(pos));
		  return new CarteWagon(carteRetournee.get(pos));
	  }
	  //System.err.println("getCarteRetourneeAt: erreur avec pos="+pos
	//		  +"\nEst nomrmalement declenche si le deck est vide => normal");
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

  synchronized public void forcerFinGame() {
		lastTurn = true;
		gameIsOver = true;
		calculerLesPointsAtEndOfGame();
  }
  /**
   * 
   * @return a copy
   */
  synchronized public Vector<Joueur> getJoueurs(){
	  return new Vector<Joueur>(vectJoueurs);
  }
}