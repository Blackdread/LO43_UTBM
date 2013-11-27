package lo43_TicketToRide.views;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.Regles;
import lo43_TicketToRide.engine.partie.Joueur;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.StateBasedGame;

public final class PartieSoloView extends PartieView {

	
	@Override
	public void initResources() {
		super.initResources();
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		super.render(container, sbgame, g);
		
		afficherInfoPersoJoueur(g,partie.getJoueurAt(0).getColor());
		
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		super.mouseMoved(oldx, oldy, newx, newy);
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		switch(key){
		
		}
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
		for(int i=0;i<Regles.NB_MAX_CARTE_RETOURNEE;++i)
			if(shapeCarteRetournee[i].contains(x, y)){
				// Demander ˆ piocher
				Joueur tmp = partie.getTourDuJoueur();
				if(!tmp.isIA())
					partie.piocherCarteRetournee(i);
				break;
			}
		
	}

	@Override
	public int getID() {
		return Game.PARTIE_SOLO_VIEW_ID;
	}

}
