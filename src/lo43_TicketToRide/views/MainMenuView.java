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
public abstract class MainMenuView extends View {

	private Image background;
	protected MouseOverArea butSolo, butPasseEtJoue, butMulti, butOption, butQuitter, butCredits, butRetour;

	// max 5 joueurs
	protected final int NB_MAX_JOUEUR = 5;
	protected boolean isIA[] = new boolean[NB_MAX_JOUEUR];
	protected MouseOverArea[] switchIA = new MouseOverArea[NB_MAX_JOUEUR];
	
	@Override
	public void initResources() {
		background = ResourceManager.getImage("western").getScaledCopy(container.getWidth(), container.getHeight());

		Image tmp = ResourceManager.getImage("butSolo");
		int larg = tmp.getWidth();
		int haut = tmp.getHeight();
		int margin = 30;

		int x = container.getWidth() / 2 - larg/2 - margin * 3;
		int y = container.getHeight() / 4 - haut/2;
		
		butSolo = new MouseOverArea(container, ResourceManager.getImage("butSolo"), x, y, larg, haut);
		//butSolo.setMouseOverImage(ResourceManager.getImage("transparent"));
		butSolo.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butMulti = new MouseOverArea(container, ResourceManager.getImage("butMulti"), x + larg + margin, y, larg, haut);
		//butMulti.setMouseOverImage(ResourceManager.getImage("transparent"));
		butMulti.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butPasseEtJoue = new MouseOverArea(container, ResourceManager.getImage("butPasseEtJoue"), x + (larg + margin)*2, y, larg, haut);
		butPasseEtJoue.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butOption = new MouseOverArea(container, ResourceManager.getImage("butOption"), x, y, larg, haut);
		//butOption.setMouseOverImage(ResourceManager.getImage("transparent"));
		butOption.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butCredits = new MouseOverArea(container, ResourceManager.getImage("butCredits"), 50, container.getHeight() - 50 - haut, larg, haut);
		//butCredits.setMouseOverImage(ResourceManager.getImage("transparent"));
		butCredits.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butQuitter = new MouseOverArea(container, ResourceManager.getImage("butQuitter"), 50 + larg + margin, container.getHeight() - 50 - haut, larg, haut);
		//butQuitter.setMouseOverImage(ResourceManager.getImage("transparent"));
		butQuitter.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		butRetour = new MouseOverArea(container, ResourceManager.getImage("butRetour"), 50, 70, larg, haut);
		//butCredits.setMouseOverImage(ResourceManager.getImage("transparent"));
		butCredits.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		tmp = ResourceManager.getImage("switchOFF");
		int larg2 = tmp.getWidth();
		haut = tmp.getHeight();
		
		for(int i=0;i<NB_MAX_JOUEUR;++i)
			switchIA[i] = new MouseOverArea(container, tmp, x - larg2 - margin + i*(larg+margin), y + haut + 10, larg2, haut);
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);
		butSolo.render(container, g);
		butMulti.render(container, g);
		butPasseEtJoue.render(container, g);
		butOption.render(container, g);
		butQuitter.render(container, g);
		butCredits.render(container, g);
		butRetour.render(container, g);
		
		
		
		g.setColor(Color.white);
		//font.drawString(container.getWidth()-font.getWidth(Game.VERSION)-5, container.getHeight()-font.getHeight(Game.VERSION)-5, Game.VERSION, Color.cyan);
		g.drawString(Game.VERSION, 5, container.getHeight() - 20);
		super.render(container, sbgame, g);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		super.mouseMoved(oldx, oldy, newx, newy);
		
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
		if(butSolo.isMouseOver())
			gotoJouerSolo();
		else if(butOption.isMouseOver())
			gotoOption();
		else if(butCredits.isMouseOver())
			gotoCredits();
		else if(butQuitter.isMouseOver())
			gotoLastView();
		else if(butMulti.isMouseOver())
			gotoJouerMulti();
		else if(butPasseEtJoue.isMouseOver())
			gotoJouerPasseEtJoue();
		else if(butRetour.isMouseOver())
			gotoResource();
	}
	
	protected void afficherSwitchIA(Graphics g){
		for(int i=0;i<NB_MAX_JOUEUR;++i){
			switchIA[i].render(container, g);
			g.drawString(""+isIA[i], switchIA[i].getX(), switchIA[i].getY() + 20);
		}
	}
	
	protected void switchIA(){
		for(int i=0;i<NB_MAX_JOUEUR;++i)
			if(switchIA[i].isMouseOver()){
				if(isIA[i] == false){
					switchIA[i].setNormalImage(ResourceManager.getImage("switchON"));
					// obliger de faire a car bug de slick
					// apparemment par defaut il me le over l'image de base
					switchIA[i].setMouseOverImage(ResourceManager.getImage("switchON"));
				}else{
					switchIA[i].setNormalImage(ResourceManager.getImage("switchOFF"));
					// obliger de faire a car bug de slick
					// apparemment par defaut il me le over l'image de base
					switchIA[i].setMouseOverImage(ResourceManager.getImage("switchOFF"));
				}
				
				isIA[i] = !isIA[i];
				break;
			}
				
	}

	private void gotoJouerSolo() {
		container.setMouseGrabbed(false);
		game.enterState(Game.MAIN_MENU_SOLO_VIEW_ID);
	}
	private void gotoJouerMulti() {
		container.setMouseGrabbed(false);
		game.enterState(Game.MAIN_MENU_MULTI_VIEW_ID);
	}
	private void gotoJouerPasseEtJoue() {
		container.setMouseGrabbed(false);
		game.enterState(Game.MAIN_MENU_PASSE_ET_JOUE_VIEW_ID);
	}
	private void gotoOption() {
		container.setMouseGrabbed(false);
		game.enterState(Game.OPTIONS_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	private void gotoCredits() {
		container.setMouseGrabbed(false);
		game.enterState(Game.CREDITS_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	protected void gotoResource() {
		container.setMouseGrabbed(false);
		game.enterState(Game.RESOURCES_STATE_ID, new FadeOutTransition(), new FadeInTransition());
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
