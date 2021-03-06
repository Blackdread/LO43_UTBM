package lo43_TicketToRide.views;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.Regles;
import lo43_TicketToRide.utils.ResourceManager;



/**
 * The main menu of the game. Severals sub menus are linked here like:
 * 
 * menu Options menu Credits menu ...
 * 
 * @author Yoann CAPLAIN
 * 
 */
public class MainMenuSoloView extends MainMenuView {
	
	@Override
	public void initResources() {
		super.initResources();
		
		super.textFieldPseudo[0].setAcceptingInput(false);
		butSolo.setNormalImage(ResourceManager.getImage("butSoloOver"));
	}
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		for(int i=1;i<Regles.NB_MAX_JOUEUR;i++)
			super.textFieldPseudo[i].setAcceptingInput(true);
		textSave.setAcceptingInput(true);
		//System.out.println("Enter solo");
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
		for(int i=1;i<Regles.NB_MAX_JOUEUR;i++)
			super.textFieldPseudo[i].setAcceptingInput(false);
		textSave.setAcceptingInput(false);
		//System.out.println("Leave solo");
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		super.render(container, sbgame, g);
		
		super.afficherSwitchIA(g);
		super.afficherPseudo(g);
		super.afficherCouleur(g);
		
		g.setColor(Color.red);
		textSave.render(container, g);
		g.draw(rectSave);
		g.drawString("go", rectSave.getX()+1, rectSave.getY()+1);
		
		butLancerGame.render(container, g);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		super.mouseMoved(oldx, oldy, newx, newy);
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		
		
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
		super.switchIA();
		
		if(rectSave.contains(x, y))
			if(chargerPartieSauvegarder("saves/"+textSave.getText()+".sav")){
				container.setMouseGrabbed(false);
				game.enterState(Game.PARTIE_SOLO_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
			}
	}
	
	@Override
	protected int nbJoueur(){
		int somme = 1;
		for(int i=1;i<Regles.NB_MAX_JOUEUR;++i)
				if(isIA[i])
					somme+=1;
		return somme;
	}
	
	//* Remonter sur classe mere
	@Override
	protected void gotoLancerPartie(){
		super.gotoLancerPartie();
		
		container.setMouseGrabbed(false);
		game.enterState(Game.PARTIE_SOLO_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
		
	}//*/

	@Override
	public int getID() {
		return Game.MAIN_MENU_SOLO_VIEW_ID;
	}

}
