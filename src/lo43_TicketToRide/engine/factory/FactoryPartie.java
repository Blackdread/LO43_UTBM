package lo43_TicketToRide.engine.factory;

import java.util.Vector;

import lo43_TicketToRide.engine.Regles;
import lo43_TicketToRide.engine.partie.Joueur;
import lo43_TicketToRide.engine.partie.Partie;
import lo43_TicketToRide.engine.factory.CarteType;

	/**
	 * 
	 * @author Yoann CAPLAIN
	 * @since 25 11 2013
	 *
	 */
	public class FactoryPartie {
	
	  private static FactoryPartie factory;
	
	  private FactoryPartie(){}
	  
	  public static FactoryPartie getInstance(){
		  if(factory == null)
			  factory = new FactoryPartie();
		  return factory;
	  }
  /**
   * On ajoute deja les cartes wagon d'une partie, ainsi que les challenges
   * @param joueur Pas sur que ce soit utile de garder ce vector
   * @return
   */
  public Partie creerPartie(Vector<Joueur> joueur) {
	  Partie partie = new Partie();
	  int k = joueur.size();
	  for(int i=0;i<k;++i)
		  partie.ajouterJoueur(joueur.remove(0));
	  
	  
	  // Preferable de faire ca ailleurs (la ou est appelle cette fonction) ainsi on peut plus facilement donner un
	  // parametre pour la carte
	  //partie.setCarteJeu(FactoryCarteJeu.getInstance().creerCarteJeu());
	  
	  for(int i=0;i<Regles.NB_CARTE_WAGON_JOKER_POUR_UNE_PARTIE;++i)
		  partie.ajouterCarteWagon(FactoryCarteWagon.getInstance().creerCarte(CarteType.Joker));
	  
	  for(int kk=0;kk<Regles.NB_CARTE_WAGON_PAR_COULEUR;++kk)
		  partie.ajouterCarteWagon(FactoryCarteWagon.getInstance().creerCarte(CarteType.Yellow));
	  for(int kk=0;kk<Regles.NB_CARTE_WAGON_PAR_COULEUR;++kk)
		  partie.ajouterCarteWagon(FactoryCarteWagon.getInstance().creerCarte(CarteType.White));
	  for(int kk=0;kk<Regles.NB_CARTE_WAGON_PAR_COULEUR;++kk)
		  partie.ajouterCarteWagon(FactoryCarteWagon.getInstance().creerCarte(CarteType.Red));
	  for(int kk=0;kk<Regles.NB_CARTE_WAGON_PAR_COULEUR;++kk)
		  partie.ajouterCarteWagon(FactoryCarteWagon.getInstance().creerCarte(CarteType.Pink));
	  for(int kk=0;kk<Regles.NB_CARTE_WAGON_PAR_COULEUR;++kk)
		  partie.ajouterCarteWagon(FactoryCarteWagon.getInstance().creerCarte(CarteType.Orange));
	  for(int kk=0;kk<Regles.NB_CARTE_WAGON_PAR_COULEUR;++kk)
		  partie.ajouterCarteWagon(FactoryCarteWagon.getInstance().creerCarte(CarteType.Green));
	  for(int kk=0;kk<Regles.NB_CARTE_WAGON_PAR_COULEUR;++kk)
		  partie.ajouterCarteWagon(FactoryCarteWagon.getInstance().creerCarte(CarteType.Blue));
	  for(int kk=0;kk<Regles.NB_CARTE_WAGON_PAR_COULEUR;++kk)
		  partie.ajouterCarteWagon(FactoryCarteWagon.getInstance().creerCarte(CarteType.Black));
	  
	  return partie;
  }

}