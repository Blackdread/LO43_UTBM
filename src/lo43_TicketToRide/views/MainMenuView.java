package lo43_TicketToRide.views;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.utils.ResourceManager;



/**
 * The main menu of the game. Severals sub menus are linked here like:
 * 
 * menu Options menu Credits menu ...
 * 
 * @author Yoann CAPLAIN
 * 
 */
public class MainMenuView extends View {

	private Image background;
	private MouseOverArea butJouer, butSolo, butMulti, butOption, butQuitter, butCredits;
	Music music;
	
	private boolean wasOverJouer = false;
	private boolean doOnce = false;

	@Override
	public void initResources() {
		background = ResourceManager.getImage("transparent").getScaledCopy(container.getWidth(), container.getHeight());

		Image tmp = ResourceManager.getImage("transparent");
		
		int larg = tmp.getWidth();
		int haut = tmp.getHeight();
		
		int x = container.getWidth() / 2 - larg/2;
		int y = container.getHeight() / 2 - haut/2 * 4;
		
		butJouer = new MouseOverArea(container, tmp, x, y, larg, haut);
		butJouer.setMouseOverImage(ResourceManager.getImage("transparent"));
		butJouer.setMouseDownSound(ResourceManager.getSound("transparent"));
		
		butMulti = new MouseOverArea(container, ResourceManager.getImage("transparent"), x + tmp.getWidth() + ResourceManager.getImage("transparent").getWidth(), y, ResourceManager.getImage("transparent").getWidth(), ResourceManager.getImage("transparent").getHeight());
		butMulti.setMouseOverImage(ResourceManager.getImage("transparent"));
		butMulti.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butOption = new MouseOverArea(container, ResourceManager.getImage("transparent"), x, y+haut, larg, haut);
		butOption.setMouseOverImage(ResourceManager.getImage("transparent"));
		butOption.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butQuitter = new MouseOverArea(container, ResourceManager.getImage("transparent"), x, y+haut*2-25, larg, haut);
		butQuitter.setMouseOverImage(ResourceManager.getImage("transparent"));
		butQuitter.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butCredits = new MouseOverArea(container, ResourceManager.getImage("transparent"), x, y+haut*3-50, larg, haut);
		butCredits.setMouseOverImage(ResourceManager.getImage("transparent"));
		butCredits.setMouseDownSound(ResourceManager.getSound("butClick"));
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		g.drawImage(background, 0, 0);
		butJouer.render(container, g);
		if(wasOverJouer){
			butSolo.render(container, g);
			butMulti.render(container, g);
		}
		butOption.render(container, g);
		butQuitter.render(container, g);
		butCredits.render(container, g);
		g.setColor(Color.white);
		//font.drawString(container.getWidth()-font.getWidth(Game.VERSION)-5, container.getHeight()-font.getHeight(Game.VERSION)-5, Game.VERSION, Color.cyan);
		g.drawString(Game.VERSION, 5, container.getHeight() - 20);
		super.render(container, sbgame, g);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		if(wasOverJouer){
			if(!butSolo.isMouseOver() && !butMulti.isMouseOver())
				wasOverJouer = false;
		}
		if(butJouer.isMouseOver())
			wasOverJouer = true;
		
		/*
		if(butJouer.isMouseOver() || butSolo.isMouseOver() || butMulti.isMouseOver() || butOption.isMouseOver() || butQuitter.isMouseOver() || butCredits.isMouseOver() && !ResourceManager.getSound("butOver").playing()){
			Message m = new Message();
			m.instruction = MessageKey.I_PLAY_SOUND;
			m.s_data.put(MessageKey.P_NAME, "butOver");
			m.engine = EngineManager.SOUND_ENGINE;
			
			engineManager.receiveMessage(m);
		}//*/
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		case Input.KEY_ESCAPE:
			gotoLastView();
			break;
		}
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		if(butJouer.isMouseOver())
			gotoJouerSolo();
		else if(butOption.isMouseOver())
			gotoOption();
		else if(butCredits.isMouseOver())
			gotoCredits();
		else if(butQuitter.isMouseOver())
			gotoLastView();
		else if(wasOverJouer){
			if(butSolo.isMouseOver())
				gotoJouerSolo();
			if(butMulti.isMouseOver())
				gotoJouerMulti();
		}
	}

	private void gotoJouerSolo() {
		container.setMouseGrabbed(false);
		game.enterState(Game.SOLO_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	private void gotoJouerMulti() {
		container.setMouseGrabbed(false);
		game.enterState(Game.MULTI_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	private void gotoOption() {
		container.setMouseGrabbed(false);
		game.enterState(Game.OPTIONS_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	private void gotoCredits() {
		container.setMouseGrabbed(false);
		game.enterState(Game.CREDITS_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	
	protected void gotoLastView() {
		container.setMouseGrabbed(false);
		game.enterState(Game.LAST_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return Game.MAIN_MENU_VIEW_ID;
	}

}
