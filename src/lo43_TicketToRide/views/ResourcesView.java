package lo43_TicketToRide.views;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.GameMusic;
import lo43_TicketToRide.engine.gui.ProgressBarFillRect;
import lo43_TicketToRide.utils.Configuration;
import lo43_TicketToRide.utils.ResourceManager;
import lo43_TicketToRide.utils.Timer;


/**
 * The second state of the game, a simple state to load resources. Like
 * presentation state this state load his own resources.
 * 
 * After loading all resources, the state move on the first view, the main menu
 * view.
 * 
 * @author Yoann CAPLAIN
 * 
 */
public class ResourcesView extends View {

	private static final int WAIT_TIME_BEFORE_NEXTR = 50;

	private boolean ready, doneOnce;
	private Image background;
	private ProgressBarFillRect bar;
	private Timer timer;

	private MouseOverArea butJouer, butRegle;
	
	private Rectangle rectFun;
	
	public ResourcesView(GameContainer container) {
		timer = new Timer(WAIT_TIME_BEFORE_NEXTR);
		this.container = container;
		initResources();
	}

	public void initResources() {
		ready=false;
		
		ResourceManager.addImage("western2", "western2.png");
		background = ResourceManager.getImage("western2");
		background = background.getScaledCopy(container.getWidth(), container.getHeight());
		
		
		
		// Init Music and Sounds
		GameMusic.initMainTheme();
		if(Configuration.isMusicOn()){
			GameMusic.setMusicVolume(Configuration.getMusicVolume());
			GameMusic.loopMainTheme();
		}
		
		ResourceManager.addImage("butJouer", "butJouer.png");
		ResourceManager.addImage("butJouerOver", "butJouerOver.png");
		ResourceManager.addImage("butRegle", "butRegle.png");
		ResourceManager.addImage("butRegleOver", "butRegleOver.png");
		
		Image tmp = ResourceManager.getImage("butJouer");
		
		int larg = tmp.getWidth();
		int haut = tmp.getHeight();
		
		int x = container.getWidth() / 2 - larg/2;
		int y = container.getHeight() / 2 - haut/2 * 4;
		
		butJouer = new MouseOverArea(container, tmp, x, y, larg, haut);
		butJouer.setMouseOverImage(ResourceManager.getImage("butJouerOver"));
		butJouer.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		tmp = ResourceManager.getImage("butRegle");
		butRegle = new MouseOverArea(container, tmp, x, y+haut+10, larg, haut);
		butRegle.setMouseOverImage(ResourceManager.getImage("butRegleOver"));
		butRegle.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		bar = new ProgressBarFillRect(2,8);
		bar.setLocation(container.getWidth() / 2 - 100, 3*container.getHeight() / 4);
		bar.setValue(40);
		
		
		rectFun = new Rectangle(10,100,50,20);
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbGame, Graphics g) throws SlickException {
		super.render(container, sbGame, g);
		g.drawImage(background, 0, 0);
		if(!doneOnce){
			g.setColor(Color.red);
			bar.render(container, g);
			g.drawString("Loading ... " + ResourceManager.getAdvancement() + "%", bar.getX() + 20, bar.getY() - 25);
		}
		
		if (ready) {
			g.draw(rectFun);
			g.drawString("Ici", rectFun.getX()+5, rectFun.getY()+2);
			
			//g.drawString("Press a key or click", container.getWidth() / 2 - 90, container.getHeight() / 2 + 10);
			butJouer.render(container, g);
			butRegle.render(container, g);
		}	
	}

	@SuppressWarnings("static-access")
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		timer.update(delta);
		if (timer.isTimeComplete()) {
			ResourceManager.loadNextResource();
			if (ResourceManager.isLoadComplete() && !ready) {
				for (int i = 1; i < sbGame.getStateCount(); i++) {
					View view = ((Game) sbGame).getStateByIndex(i);
					view.initResources();
				}
				
				ready = true;
				
			}
			timer.resetTime();
		}
		if (bar != null)
			bar.setValue(((float) ResourceManager.getAdvancement()));
			//bar.setValue(((float) ResourceManager.getAdvancement()) / 100);
	}

	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		if(key == Input.KEY_ESCAPE)
			goToFun();
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		if(butJouer.isMouseOver())
			goToMenuSolo();
		if(butRegle.isMouseOver())
			goToRegle();
		if(rectFun.contains(x, y))
			goToFun();
	}

	private void goToFun(){
		if (ready) {
		container.setMouseGrabbed(false);
		game.enterState(Game.FUN_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
		}
	}
	
	private void goToRegle() {
		if (ready) {
			doneOnce = true;
			container.setMouseGrabbed(false);
			game.enterState(Game.REGLE_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
		}
	}
	
	private void goToMenuSolo() {
		if (ready) {
			doneOnce = true;
			container.setMouseGrabbed(false);
			game.enterState(Game.MAIN_MENU_SOLO_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
			//game.enterState(Game.TEST_STATE_ID, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public int getID() {
		return Game.RESOURCES_STATE_ID;
	}

}