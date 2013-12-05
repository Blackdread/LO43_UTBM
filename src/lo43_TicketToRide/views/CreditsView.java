package lo43_TicketToRide.views;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.utils.ResourceManager;


/**
 * Menu associated to the credits.
 * 
 * @author Yoann CAPLAIN
 * 
 */

public class CreditsView extends View {

	private Image background;
	MouseOverArea butQuitter;
	
	@Override
	public void initResources() {
		//*
		background = ResourceManager.getImage("western").getScaledCopy(container.getWidth(), container.getHeight());
		
		butQuitter = new MouseOverArea(container, ResourceManager.getImage("butQuitter"), container.getWidth()/10, container.getHeight()-container.getHeight()/10 - 50);
		butQuitter.setMouseOverImage(ResourceManager.getImage("butQuitterOver"));
		butQuitter.setMouseDownSound(ResourceManager.getSound("butClick"));
		//*/
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbGame, Graphics g) throws SlickException {
		
		g.drawImage(background, 0, 0);
		
		g.setColor(Color.white);
		g.drawString("Jeu créé par Yoann CAPLAIN en 2013 en 3 semaines", container.getWidth()/2-90, container.getHeight()/2-15);
		g.drawString("Créé pour le projet de LO43 pour l'école d'ingénieur UTBM", container.getWidth()/2-90, container.getHeight()/2);
		
		butQuitter.render(container, g);
		super.render(container, game, g);
	}
	
	@Override
	public void mouseReleased(int button, int x, int y) {
		super.mouseReleased(button, x, y);
		if(butQuitter.isMouseOver())
			goToMenu();
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if(Input.KEY_ESCAPE == key)
			super.gotoPreviousView();
	}

	private void goToMenu() {
		container.setMouseGrabbed(false);
		super.gotoPreviousView();
	}

	

	@Override
	public int getID() {
		return Game.CREDITS_VIEW_ID;
	}
}
