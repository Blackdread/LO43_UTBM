package lo43_TicketToRide.views;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.Regles;
import lo43_TicketToRide.engine.partie.CarteWagon;
import lo43_TicketToRide.engine.partie.CarteWagonJoker;
import lo43_TicketToRide.engine.partie.Joueur;
import lo43_TicketToRide.engine.partie.Partie;
import lo43_TicketToRide.utils.Colors;
import lo43_TicketToRide.utils.ResourceManager;



/**
 * Affiche les elements partages d'une partie
 * 
 * 
 * @author Yoann CAPLAIN
 * @since 24 11 2013
 */
public abstract class PartieView extends View {

	private Image background;
	protected MouseOverArea butDeckWagon, butDeckChallenge;
	/**
	 * Rectangle car on affiche juste une couleur
	 */
	protected Rectangle[] shapeCarteRetournee = new Rectangle[Regles.NB_MAX_CARTE_RETOURNEE];
	protected Rectangle[] shapeJoueur = new Rectangle[Regles.NB_MAX_JOUEUR];
	
	private Partie partie;
	
	@Override
	public void initResources() {
		background = ResourceManager.getImage("western").getScaledCopy(container.getWidth(), container.getHeight());
		
		/*
		Image tmp = ResourceManager.getImage("");
		int larg = tmp.getWidth();
		int haut = tmp.getHeight();
		int margin = 30;
		
		int x = container.getWidth() / 2 - larg/2 - margin * 3;
		int y = container.getHeight() / 4 - haut/2;
		//*/
		//but = new MouseOverArea(container, ResourceManager.getImage(""), x, y, larg, haut);
		//but.setMouseOverImage(ResourceManager.getImage(""));
		//but.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		int largCarte = 200;
		int hautCarte = 100;
		int margin = 30;
		for(int i=0;i<Regles.NB_MAX_CARTE_RETOURNEE;++i){
			shapeCarteRetournee[i] = new Rectangle(container.getWidth() - largCarte - margin, hautCarte+margin,largCarte,hautCarte);
		}
		largCarte = 150;
		hautCarte = 200;
		for(int i=0;i<Regles.NB_MAX_JOUEUR;++i){
			shapeJoueur[i] = new Rectangle(largCarte + margin, margin,largCarte,hautCarte);
		}
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);

		butDeckChallenge.render(container, g);
		afficherCarteRetournee(g);
		butDeckWagon.render(container, g);
		
		afficherJoueurs(g);
		
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
		
		}
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
		for(int i=0;i<Regles.NB_MAX_CARTE_RETOURNEE;++i)
			if(shapeCarteRetournee[i].contains(x, y)){
				// Demander ˆ piocher
				
				break;
			}
		
	}
	
	protected void afficherJoueurs(Graphics g){
		Joueur tmp;
		for(int i=0;i<Regles.NB_MAX_JOUEUR;++i){
			tmp = partie.getJoueurAt(i);
			if(tmp == null)
				break;
			g.setColor(Colors.getColor(tmp.getColor()));
			g.fill(shapeJoueur[i]);
			
			g.setColor(Color.black);
			g.draw(shapeJoueur[i]);
			
			g.drawString("Pseudo:   "+tmp.getPseudo(), shapeJoueur[i].getX(), shapeJoueur[i].getY()+10);
			g.drawString("Nb wagon: "+tmp.getNbWagon(), shapeJoueur[i].getX(), shapeJoueur[i].getY()+60);
			g.drawString("Score:    "+tmp.getScore(), shapeJoueur[i].getX(), shapeJoueur[i].getY()+110);
		}
	}

	protected void afficherCarteRetournee(Graphics g){
		CarteWagon tmp;
		for(int i=0;i<Regles.NB_MAX_CARTE_RETOURNEE;++i){
			tmp = partie.getCarteRetourneeAt(i);
			if(tmp == null)
				break;
			if(tmp instanceof CarteWagonJoker){
				g.setColor(Color.black);
				g.drawString("Joker", shapeCarteRetournee[i].getX()+80, shapeCarteRetournee[i].getY()+40);
				
				g.setColor(Color.gray);
			}else
			g.setColor(Colors.getColor(tmp.getColor()));
			g.fill(shapeCarteRetournee[i]);
		}
	}
	
	
	protected void goto22() {
		container.setMouseGrabbed(false);
		//game.enterState(Game., new FadeOutTransition(), new FadeInTransition());
	}

	@Override
	public int getID() {
		return Game.PARTIE_VIEW_ID;
	}

}
