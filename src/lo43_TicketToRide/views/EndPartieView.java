package lo43_TicketToRide.views;


import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.partie.Joueur;
import lo43_TicketToRide.engine.partie.Partie;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class EndPartieView extends View {

	protected Partie partie;
	protected Joueur joueurGagnant;

	@Override
	public void initResources() {
		
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
			
			
		}else
			System.err.println("EndPartieView:enter: partie null");
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
		if(partie != null){
			
			g.setColor(Color.white);
			g.drawString(""+joueurGagnant.getPseudo()+" "+joueurGagnant.getScore(), container.getWidth()/2, container.getHeight()/3);
			
		}
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
		
	}
	
	protected void afficherJoueurs(Graphics g){
		
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
