package lo43_TicketToRide.engine.partie;

import java.util.Vector;

import lo43_TicketToRide.engine.factory.FactoryIA.NiveauIA;
/**
 * 
 * @author Yoann CAPLAIN
 * @since 19 12 2013
 */
public class IA extends Joueur {
	private static final long serialVersionUID = 8637694316677610630L;

	protected NiveauIA niveauDifficulte;
	
	public IA(String pseudo, NiveauIA niveauDifficulte) {
		super(pseudo);
		isIA = true;
		this.niveauDifficulte=niveauDifficulte;
	}
	public IA(String pseudo, int color,NiveauIA niveauDifficulte) {
		super(pseudo,color,true);
		this.niveauDifficulte=niveauDifficulte;
	}

	public IA(final IA copy){
		super(copy);
		niveauDifficulte = copy.niveauDifficulte;
	}
	
	public void faireUneAction(Partie partie){
		switch(niveauDifficulte){
		default:
			Vector<Route> tmp = partie.getCarteJeu().getRoutePossibleDePrendre(this);
			if(tmp.size() > 1){
				partie.prendrePossessionRoute(tmp.get(0));
			}else{
				if(partie.getDeckSize() == 0){
					partie.piocherCarteRetournee(0);
					if(!partie.piocherCarteRetournee(0)){
						partie.piocherCarteRetournee(1);
						partie.compteurCarteRetourneePiocher+=5555;
					}
				}else{
					partie.piocherCarteDeck();
					partie.piocherCarteDeck();
				}
			}
		}
	}
	
	public NiveauIA getNiveauDifficulte() {
		return niveauDifficulte;
	}
	public void setNiveauDifficulte(NiveauIA niveauDifficulte) {
		this.niveauDifficulte = niveauDifficulte;
	}

	
}
