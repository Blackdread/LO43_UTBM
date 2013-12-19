package lo43_TicketToRide.views;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.utils.ResourceManager;
import lo43_TicketToRide.utils.Timer;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 09 10 2012
 */
public class LastView extends View{

	private static final int WAIT_TIME_BEFORE_NEXTR = 1000;
	
	private Image background;
	private Timer timer;
	private boolean ready;
	
	@Override
	public void initResources() {
		ready = false;
		timer = new Timer(WAIT_TIME_BEFORE_NEXTR);	
		background = ResourceManager.getImage("transparent").getScaledCopy(container.getWidth(), container.getHeight());
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbGame, Graphics g) throws SlickException {
		super.render(container, game, g);
		g.drawImage(background, 0, 0);
		g.setColor(Color.red);
		
		g.drawString("Jeu creer par Yoann CAPLAIN pour l'UV LO43 UTBM en 2013", container.getWidth() / 2 - 200, container.getHeight() / 2 - 40);
		
		if(ready)
			g.drawString("Press a key or click", container.getWidth() / 2 - 90, container.getHeight() / 2 + 40);
	}
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		timer.update(delta);
		if (timer.isTimeComplete()) {
			timer.resetTime();
			ready=true;
		}
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if(ready)
			container.exit();
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		if(ready)
			container.exit();
	}
	
	@Override
	public int getID() {
		return Game.LAST_VIEW_ID;
	}

}
