package lo43_TicketToRide.views;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.utils.ResourceManager;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 22 11 2013
 */
public class RegleView extends View{
	
	private Image background;
	
	@Override
	public void initResources() {	
		background = ResourceManager.getImage("transparent").getScaledCopy(container.getWidth(), container.getHeight());
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbGame, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.drawImage(background, 0, 0);
		g.setColor(Color.red);
		g.drawString("Voir les règles sur le site officiel de Ticket To Ride", container.getWidth() / 2 - 150, container.getHeight() / 2 - 20);
		g.drawString("Version de base", container.getWidth() / 2 - 90, container.getHeight() / 2 );
	}
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if(Input.KEY_ESCAPE == key)
			super.gotoPreviousView();
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
	}
	
	@Override
	public int getID() {
		return Game.REGLE_VIEW_ID;
	}

}
