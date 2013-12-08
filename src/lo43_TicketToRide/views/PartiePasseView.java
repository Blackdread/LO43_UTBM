package lo43_TicketToRide.views;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.Regles;
import lo43_TicketToRide.engine.partie.Joueur;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
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
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		super.render(container, sbgame, g);
		
		if(!partie.getTourDuJoueur().isIA())
			afficherInfoPersoJoueur(g,partie.getTourDuJoueur().getColor());
		
		if(afficherSelectionChallenge){
			afficherChallengesPourLesPiocher(g);
			butOkChallenges.render(container, g);
		}
		
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		super.mouseMoved(oldx, oldy, newx, newy);
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
		if(butDeckChallenge.isMouseOver() ){
			// TODO
			if(!afficherSelectionChallenge){
				Joueur tmp = partie.getTourDuJoueur();
				if(!tmp.isIA() && partie.isPiocherChallengeOK()){
					afficherSelectionChallenge = !afficherSelectionChallenge;
					butOkChallenges.setAcceptingInput(true);
				}
			}
		}else if(butDeckWagon.isMouseOver()){
			Joueur tmp = partie.getTourDuJoueur();
			if(!tmp.isIA())
				partie.piocherCarteDeck();
		}else if(butOkChallenges.isMouseOver() && butOkChallenges.isAcceptingInput()){
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
