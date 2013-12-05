package lo43_TicketToRide.views;


import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.partie.Joueur;
import lo43_TicketToRide.engine.partie.Partie;
import lo43_TicketToRide.utils.ResourceManager;

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

public class EndPartieView extends View {

	protected Partie partie;
	protected Joueur joueurGagnant;

	protected MouseOverArea butQuitter;
	
	@Override
	public void initResources() {
		Image tmp = ResourceManager.getImage("butQuitter");
		int larg = tmp.getWidth();
		int haut = tmp.getHeight();
		
		butQuitter = new MouseOverArea(container, tmp, 50, container.getHeight() - 50 - haut, larg, haut);
		butQuitter.setMouseOverImage(ResourceManager.getImage("butQuitterOver"));
		butQuitter.setMouseDownSound(ResourceManager.getSound("butClick"));
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);
		if(partie != null){
			int max = 0;
			for(Joueur v : partie.getJoueurs())
				if(v.getScore() > max){
					joueurGagnant = v;
					max = v.getScore();
				}
			//System.out.println("EndPartieView:enter: fait");
			
		}else
			System.err.println("EndPartieView:enter: partie null");
		
		//*
		((PartieView)Game.getStateByID(Game.PARTIE_SOLO_VIEW_ID)).setPartie(null);
		((PartieView)Game.getStateByID(Game.PARTIE_PASSE_ET_JOUE_VIEW_ID)).setPartie(null);
		((PartieView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).setPartie(null);
		//*/
	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
		partie=null;
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		if(partie != null && joueurGagnant != null){
			
			g.setColor(Color.white);
			g.drawString("Winner :", container.getWidth()/2, container.getHeight()/3-35);
			g.drawString(""+joueurGagnant.getPseudo()+" "+joueurGagnant.getScore(), container.getWidth()/2, container.getHeight()/3-20);
			
			g.drawString("Perdants :", container.getWidth()/2, container.getHeight()/3+20);
			int nb = partie.getJoueurs().size();
			int i=0;
			for(Joueur v : partie.getJoueurs()){
				if(!v.equals(joueurGagnant)){
					g.drawString(""+v.getPseudo()+" "+v.getScore(), container.getWidth()/nb+i*80, container.getHeight()/3+40);
					i++;
				}
			}
			
		}//else
			// Se produit du au fait que la fonction enter n'est pas appele avant de commencer a faire render
			//System.err.println("EndPartieView:render: null");
		butQuitter.render(container, g);
		
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
			goToMenuSolo();
			
		}
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		if(Input.MOUSE_LEFT_BUTTON == button)
			if(butQuitter.isMouseOver())
				goToMenuSolo();
	}
	
	private void goToMenuSolo() {
		container.setMouseGrabbed(false);
		game.enterState(Game.MAIN_MENU_SOLO_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	
	@Override
	public int getID() {
		return Game.END_PARTIE_VIEW_ID;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

}
