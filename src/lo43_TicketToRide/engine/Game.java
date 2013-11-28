package lo43_TicketToRide.engine;

import java.io.IOException;
import java.util.ArrayList;

import lo43_TicketToRide.utils.Configuration;
import lo43_TicketToRide.utils.ResourceManager;
import lo43_TicketToRide.views.*;

import org.lwjgl.LWJGLException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 * 
 * @author Yoann CAPLAIN
 * @since 17 11 2013
 */
public class Game extends StateBasedGame {
	
	private static AppGameContainer container;

	/**
	 * The current name of the project.
	 */
	public static final String NAME = "Ticket To Ride";
	/**
	 * The current version of the project.
	 */
	public static final String VERSION = "Version 1.0 Beta";
	
	
	private static ArrayList<View> states;
	
	public static final int MINIMUM_SCREEN_HAUTEUR = 768;
	
	// State IDS
	public static final int RESOURCES_STATE_ID = 0;
	public static final int MAIN_MENU_VIEW_ID = 1;
	public static final int OPTIONS_VIEW_ID = 4;
	public static final int CREDITS_VIEW_ID = 8;
	public static final int REGLE_VIEW_ID = 9;
	
	public static final int MAIN_MENU_SOLO_VIEW_ID = 12;
	public static final int MAIN_MENU_MULTI_VIEW_ID = 13;
	public static final int MAIN_MENU_PASSE_ET_JOUE_VIEW_ID = 14;
	
	public static final int PARTIE_VIEW_ID = 64;
	public static final int PARTIE_SOLO_VIEW_ID = 65;
	public static final int PARTIE_MULTI_VIEW_ID = 66;
	public static final int PARTIE_PASSE_ET_JOUE_VIEW_ID = 67;
	
	public static final int FUN_VIEW_ID = 512;
	
	public static final int TRANSITION_VIEW_ID = 1024;
	public static final int LAST_VIEW_ID = 2048;
	
	
	public Game(String configFileLocation, String resourceJarLocation) throws IOException, SlickException {
		super(NAME);
		// Initialize resources
		ResourceManager.init(resourceJarLocation);
		// Initialize configuration
		Configuration.init(configFileLocation);
		
		states = new ArrayList<View>();
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		addState(new ResourcesView(container));
		
		//addState(new MainMenuView()); devenu abstract
		addState(new MainMenuSoloView());
		addState(new MainMenuMultiView());
		addState(new MainMenuPasseView());
		addState(new OptionsView());
		addState(new CreditsView());
		addState(new RegleView());
		
		addState(new PartieSoloView());
		addState(new PartiePasseView());
		/*
		addState(new SoloView());
		addState(new MultiView());
		addState(new SalonView());
		
		addState(new TransitionView());//*/
		addState(new LastView());
		
		addState(new FunView());
		//addState(new InGameMultiView());
		//*/
		
	}
	
	private void applyCurrentConfiguration(AppGameContainer container) throws IOException, SlickException {
		Configuration.updateConfigFile();
		container.setDisplayMode(Configuration.getWidth(), Configuration.getHeight(), Configuration.isFullScreen());  
		container.setTargetFrameRate(Configuration.getTargetFPS());
		container.setSmoothDeltas(Configuration.isSmoothDeltas());
		container.setMultiSample(4);
		container.setVSync(Configuration.isVSynch());
		container.setMusicVolume(Configuration.getMusicVolume());
		container.setMusicOn(Configuration.isMusicOn());
		container.setSoundVolume(Configuration.getSoundVolume());
		container.setShowFPS((Configuration.isDebug()) ? true : false);
		container.setVerbose((Configuration.isDebug()) ? true : false);
	}
	
	/**
	 * Apply the current configuration to the game container of the game
	 * context.
	 * 
	 * @throws IOException
	 *             If the configuration loading failed.
	 * @throws SlickException
	 *             If the configuration loading failed.
	 */
	public void applyCurrentConfiguration() throws IOException, SlickException {
		applyCurrentConfiguration((AppGameContainer) this.getContainer());
	}

	/**
	 * Entry point to launch the game.
	 * 
	 * @throws SlickException
	 * @throws IOException
	 * @throws LWJGLException 
	 */
	public void launch() throws SlickException, IOException {
		container = new AppGameContainer(this);
		
		// Icon		ici car ca a besoin d'etre mis avant que le container ne commence
		/*
		String icon[] = {"resources/others/ico16.png", "resources/others/ico24.png","resources/others/ico32.png"};
		try {
			container.setIcons(icon);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		//*/
		
		container.setMinimumLogicUpdateInterval(10);
		container.setMaximumLogicUpdateInterval(10);
		container.setUpdateOnlyWhenVisible(false);
		container.setAlwaysRender(true);
		
		// Apply Configuration
		applyCurrentConfiguration(container);

		// Start the game
		container.start();
	}
	
	@Override
	public void addState(GameState state) {
		super.addState(state);
		states.add((View) state);
	}

	public static View getStateByIndex(int index) {
		return (View) states.get(index);
	}
	
	public static View getStateByID(int id) {
		for(View v : states)
			if(v != null && v.getID() == id)
				return v;
		return null;
	}
	
	public static void changeResolution(int width, int height) throws SlickException{
		((AppGameContainer) Game.container).setDisplayMode(width, height, Configuration.isFullScreen());
	}

	/**
	 * Plus simple que de devoir recharger que celles dont on a besoin. C'est un petit jeu apres tout :)
	 * C'est Slick 2D qui a ete mal pense sur sa facon de fonctionner, le RessourceManager ne devrait pas tous charger mais le
	 * faire progressivement selon la vue ou on se trouve.
	 */
	@Deprecated
	public static void rechargerToutesLesRessources(){
		for(View v : states)
			if(v!=null){
				v.initResources();
			}
		
	}

}