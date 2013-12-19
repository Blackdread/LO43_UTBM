package lo43_TicketToRide.views;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.Regles;
import lo43_TicketToRide.engine.partie.Joueur;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

public class PartiePasseView extends PartieView {

	
	@Override
	public void initResources() {
		super.initResources();
		textSave.setAcceptingInput(false);
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		textSave.setAcceptingInput(true);
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
		
		textSave.setAcceptingInput(false);
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		super.render(container, sbgame, g);
		
		if(!partie.getTourDuJoueur().isIA())
			afficherInfoPersoJoueur(g,partie.getTourDuJoueur().getColor());
		
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		//System.out.println("PartiePasseView mousePressed "+x+" "+y);
		
		/* test
		if(partie.getTourDuJoueur().getChallenges().remove(0).isChallengeSucceeded(partie.getCarteJeu(), partie.getTourDuJoueur().getColor()))
			System.out.println("OK OK OK");
		else
			System.out.println("PAS PAS PAS OK OK OK");
		*/
		if(butDeckChallenge.isMouseOver() || new Rectangle(butDeckChallenge.getX(),butDeckChallenge.getY(),butDeckChallenge.getWidth(),butDeckChallenge.getHeight()).contains(x, y)){
			// TODO
			if(!afficherSelectionChallenge){
				Joueur tmp = partie.getTourDuJoueur();
				if(!tmp.isIA() && partie.isPiocherChallengeOK()){
					afficherSelectionChallenge = !afficherSelectionChallenge;
					butOkChallenges.setAcceptingInput(true);
				}
			}
		}else if(butDeckWagon.isMouseOver() || new Rectangle(butDeckWagon.getX(),butDeckWagon.getY(),butDeckWagon.getWidth(),butDeckWagon.getHeight()).contains(x, y)){
			Joueur tmp = partie.getTourDuJoueur();
			if(!tmp.isIA())
				partie.piocherCarteDeck();
		}else if((butOkChallenges.isMouseOver() || new Rectangle(butOkChallenges.getX(),butOkChallenges.getY(),butOkChallenges.getWidth(),butOkChallenges.getHeight()).contains(x, y)) && butOkChallenges.isAcceptingInput() && afficherSelectionChallenge){
			Joueur tmp = partie.getTourDuJoueur();
			if(!tmp.isIA()){
				partie.piocherChallengesSelectionne(isChallengeSelected[0], isChallengeSelected[1], isChallengeSelected[2]);
				afficherSelectionChallenge = false;
				butOkChallenges.setAcceptingInput(false);
				System.out.println("okChallenge fin");
			}
		}
		
		for(int i=0;i<Regles.NB_MAX_CARTE_RETOURNEE;++i)
			if(shapeCarteRetournee[i].contains(x, y)){
				// Demander ˆ piocher
				Joueur tmp = partie.getTourDuJoueur();
				if(!tmp.isIA())
					partie.piocherCarteRetournee(i);
				break;
			}
		
	}

	@Override
	public int getID() {
		return Game.PARTIE_PASSE_ET_JOUE_VIEW_ID;
	}
}
