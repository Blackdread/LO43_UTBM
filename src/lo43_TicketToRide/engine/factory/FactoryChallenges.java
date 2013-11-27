package lo43_TicketToRide.engine.factory;

import java.util.Vector;

import lo43_TicketToRide.engine.partie.CarteJeu;
import lo43_TicketToRide.engine.partie.Challenge;

public class FactoryChallenges {


	  private static FactoryChallenges factory;

	  private FactoryChallenges(){}
	  
	  public static FactoryChallenges getInstance(){
		  if(factory == null)
			  factory = new FactoryChallenges();
		  return factory;
	  }
	  
	  /**
	   * Il faut en creer un minimum de 15 car on au max 5 joueurs (3x5 = 15)
	   * @param carte utilise les villes de cette carte
	   * @param numCarte numero unique des CarteJeu
	   * @return
	   */
	  public Vector<Challenge> creerChallenges(CarteJeu carte, int numCarte) {
		  Vector<Challenge> cha = new Vector<Challenge>();
		  switch(numCarte){
		  case 0:
		  default:
			  cha.add(new Challenge(2, carte.getVilleAt(0), carte.getVilleAt(1)));
			  cha.add(new Challenge(5, carte.getVilleAt(3), carte.getVilleAt(11)));
			  cha.add(new Challenge(3, carte.getVilleAt(12), carte.getVilleAt(10)));
			  cha.add(new Challenge(6, carte.getVilleAt(9), carte.getVilleAt(3)));
			  cha.add(new Challenge(12, carte.getVilleAt(0), carte.getVilleAt(12)));
			  
			  cha.add(new Challenge(2, carte.getVilleAt(3), carte.getVilleAt(8)));
			  cha.add(new Challenge(2, carte.getVilleAt(5), carte.getVilleAt(11)));
			  cha.add(new Challenge(2, carte.getVilleAt(7), carte.getVilleAt(2)));
			  cha.add(new Challenge(2, carte.getVilleAt(11), carte.getVilleAt(2)));
			  cha.add(new Challenge(2, carte.getVilleAt(4), carte.getVilleAt(8)));
			  
			  // ******
			  // Y a pas assez de ville pour le moment
			  cha.add(new Challenge(2, carte.getVilleAt(3), carte.getVilleAt(8)));
			  cha.add(new Challenge(2, carte.getVilleAt(5), carte.getVilleAt(11)));
			  cha.add(new Challenge(2, carte.getVilleAt(7), carte.getVilleAt(2)));
			  cha.add(new Challenge(2, carte.getVilleAt(11), carte.getVilleAt(2)));
			  cha.add(new Challenge(2, carte.getVilleAt(4), carte.getVilleAt(8)));
			  
			  cha.add(new Challenge(2, carte.getVilleAt(11), carte.getVilleAt(2)));
			  cha.add(new Challenge(2, carte.getVilleAt(4), carte.getVilleAt(8)));
			  //*******
			  break;
		  }
		  return cha;
	  }
}
