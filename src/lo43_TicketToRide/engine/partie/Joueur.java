package lo43_TicketToRide.engine.partie;

import java.io.Serializable;
import java.util.Vector;

import lo43_TicketToRide.engine.Regles;



/**
 * 
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class Joueur implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1222L;
	protected String pseudo;
	/**
	 * Unique par joueur (comme une id)
	 */
	protected int color;
	protected int score;
	protected int nbWagon = Regles.NB_WAGON_PAR_JOUEUR;
	
	/**
	 * N'est plus utile depuis creation de IA
	 */
	protected boolean isIA;
	/**
	 * 
	 * @element-type Challenge
	 */
	protected Vector<Challenge>  challenges = new Vector<Challenge>();
	/**
	 * 
	 * @element-type CarteWagon
	 */
	protected Vector<CarteWagon>  cartes = new Vector<CarteWagon>();

	public Joueur(String pseudo){
		super();
		this.pseudo = pseudo;
		isIA = false;
		color = -1;
	}


	public Joueur(String pseudo, int color, boolean isIA) {
		super();
		this.pseudo = pseudo;
		this.color = color;
		this.isIA = isIA;
	}


	public Joueur(final Joueur copy){
		pseudo = copy.pseudo;
		color = copy.color;
		score = copy.score;
		nbWagon = copy.nbWagon;
		isIA = copy.isIA;
		challenges = copy.getChallenges();
		cartes = copy.getCartes();
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

	synchronized public int getNbCarte(){
		return cartes.size();
	}

	synchronized public void retirerCarteAt(int i){
		if(i>=0 && i < cartes.size())
			cartes.remove(i);
	}
	/**
	 * 
	 * @param i
	 * @return pas une copy
	 */
	synchronized public CarteWagon getCarteAt(int i){
		if(i>=0 && i < cartes.size())
			return cartes.get(i);
		return null;
	}

	synchronized public int compterNbCarteDeTelleCouleur(int color){
		int somme=0;
		for(int i=0;i<cartes.size();++i)
			if(cartes.get(i).getColor() == color)
				somme +=1;
		return somme;

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

	synchronized public void retirerWagon(int nb){
		nbWagon -=nb;
	}

	synchronized public void setIA(boolean isIA) {
		this.isIA = isIA;
	}

	synchronized public void ajouterPoint(int points){
		this.score += points;
	}

	@Override
	synchronized public boolean equals(Object a){
		if(a instanceof Joueur)
			return ((Joueur)a).color == color;
		return false;
	}

}