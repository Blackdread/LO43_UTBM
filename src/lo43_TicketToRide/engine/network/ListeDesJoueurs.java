package lo43_TicketToRide.engine.network;

import java.io.Serializable;
import java.util.Vector;

import lo43_TicketToRide.engine.partie.Joueur;

public class ListeDesJoueurs implements Serializable{
	private static final long serialVersionUID = 3917770519871393625L;
	Vector<Joueur> vector;
	
	/**
	 * C'est la couleur de celui recoit cette objet
	 */
	int colorDeCeluiQuiRecoit;
	public ListeDesJoueurs(){
		vector = new Vector<Joueur>();
	}
}
