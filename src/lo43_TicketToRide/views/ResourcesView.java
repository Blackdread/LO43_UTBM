package lo43_TicketToRide.views;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
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

	private boolean ready;
	private Image background;
	private ProgressBarFillRect bar;
	private Timer timer;

	private MouseOverArea butJouer, butRegle;
	
	public ResourcesView(GameContainer container) {
		timer = new Timer(WAIT_TIME_BEFORE_NEXTR);
		this.container = container;
		initResources();
	}

	public void initResources() {
		ready=false;
		
		//ResourceManager.addImage("backgroundIntro", "backgroundIntro.png");
		//background = ResourceManager.getImage("intro");
		//background = background.getScaledCopy(container.getWidth(), container.getHeight());
		
		
		
		// Init Music and Sounds
		if(Configuration.isMusicOn()){
			GameMusic.initMainTheme();
			GameMusic.setMusicVolume(Configuration.getMusicVolume());
			GameMusic.loopMainTheme();
		}
		
		ResourceManager.addImage("butJouer", "butJouer.png");
		ResourceManager.addImage("butRegle", "butRegle.png");
		
		Image tmp = ResourceManager.getImage("butJouer");
		
		int larg = tmp.getWidth();
		int haut = tmp.getHeight();
		
		int x = container.getWidth() / 2 - larg/2;
		int y = container.getHeight() / 2 - haut/2 * 4;
		
		butJouer = new MouseOverArea(container, tmp, x, y, larg, haut);
		//butJouer.setMouseOverImage(ResourceManager.getImage(""));
		butJouer.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		tmp = ResourceManager.getImage("butRegle");
		butRegle = new MouseOverArea(container, tmp, x, y+haut+10, larg, haut);
		//butRegle.setMouseOverImage(ResourceManager.getImage(""));
		butRegle.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		/*
		try {
			background = new Image("resources/images/logo.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		//*/
		bar = new ProgressBarFillRect(2,8);
		bar.setLocation(container.getWidth() / 2 - 100, 3*container.getHeight() / 4);
		bar.setValue(40);
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame sbGame, Graphics g) throws SlickException {
		super.render(container, sbGame, g);
		//g.drawImage(background, 0, 0);
		g.setColor(Color.red);
		bar.render(container, g);
		g.drawString("Loading ... " + ResourceManager.getAdvancement() + "%", bar.getX() + 20, bar.getY() - 25);

		if (ready) {
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
				
				/*
				SoundEngine b = new SoundEngine();
				
				Message a = new Message();
				a.instruction = MessageKey.I_PLAY_MUSIC;
				a.s_data.put(MessageKey.P_NAME, "tron");
				a.f_data.put(MessageKey.P_VOLUME, 0.1f);
				a.b_data.put(MessageKey.P_LOOP, false);
				b.receiveMessage(a);
				//b.processMessage();
				
				Message c = new Message();
				c.instruction = MessageKey.I_PLAY_AT_MUSIC;
				c.f_data.put(MessageKey.P_POSITION, 50.0f);
				b.receiveMessage(c);
				b.processMessage();
				
				Message d = new Message();
				d.instruction = MessageKey.I_CHANGE_VOLUME_MUSIC;
				d.f_data.put(MessageKey.P_VOLUME, 0.20f);
				b.receiveMessage(d);
				b.processMessage();
				*/
				
				
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
		//goToMenu();
	}

	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		if(butJouer.isMouseOver())
			goToMenu();
		if(butRegle.isMouseOver())
			goToRegle();
		
	}

	private void goToRegle() {
		if (ready) {
			container.setMouseGrabbed(false);
			game.enterState(Game.REGLE_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
		}
	}
	
	private void goToMenu() {
		if (ready) {
			container.setMouseGrabbed(false);
			game.enterState(Game.MAIN_MENU_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
			//game.enterState(Game.TEST_STATE_ID, new FadeOutTransition(), new FadeInTransition());
		}
	}

	@Override
	public int getID() {
		return Game.RESOURCES_STATE_ID;
	}

}