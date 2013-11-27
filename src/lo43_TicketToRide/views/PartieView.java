package lo43_TicketToRide.views;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.StateBasedGame;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.Regles;
import lo43_TicketToRide.engine.factory.CarteType;
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
	
	protected Rectangle shapeBas;
	protected RoundedRectangle rectCarteJeu;
	
	/**
	 * Juste pour laisser un delay
	 */
	protected boolean partieEnCours = false;
	protected Partie partie;
	
	
	@Override
	public void initResources() {
		background = ResourceManager.getImage("western").getScaledCopy(container.getWidth(), container.getHeight());
		
		int largCarte = ResourceManager.getImage("challenge").getWidth();
		int hautCarte = ResourceManager.getImage("challenge").getHeight();
		int margin = 30;
		
		butDeckChallenge = new MouseOverArea(container, ResourceManager.getImage("challenge"), container.getWidth() - largCarte - margin, margin - 10, largCarte, hautCarte);
		butDeckChallenge.setMouseOverImage(ResourceManager.getImage("challengeOver"));
		butDeckChallenge.setMouseDownSound(ResourceManager.getSound("butClick"));
		butDeckWagon = new MouseOverArea(container, ResourceManager.getImage("deck"), container.getWidth() - largCarte - margin, hautCarte+margin, largCarte, hautCarte);
		butDeckWagon.setMouseOverImage(ResourceManager.getImage("deckOver"));
		butDeckWagon.setMouseDownSound(ResourceManager.getSound("butClick"));
		
		
		for(int i=0;i<Regles.NB_MAX_CARTE_RETOURNEE;++i){
			shapeCarteRetournee[i] = new Rectangle(container.getWidth() - largCarte - margin, hautCarte+margin+5*(i+1) + hautCarte*(i+1),largCarte,hautCarte);
		}
		largCarte = 170;
		hautCarte = 100;
		for(int i=0;i<Regles.NB_MAX_JOUEUR;++i){
			shapeJoueur[i] = new Rectangle(100 + (margin + largCarte)*i, margin,largCarte,hautCarte);
		}
		
		shapeBas = new Rectangle(10,container.getHeight()-125,container.getWidth()-100,120);
		
		
		int y = (int) (shapeJoueur[0].getY()+shapeJoueur[0].getHeight()) + 10;
		int width = (int) (shapeCarteRetournee[0].getX() - 30);
		int height = (int)(shapeBas.getY()-y) - 10;
		rectCarteJeu = new RoundedRectangle(10,y, width,height,10);
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		if(partie != null && partieEnCours)
			partie.update(delta);
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {
		g.drawImage(background, 0, 0);

		g.setColor(Color.black);
		butDeckChallenge.render(container, g);
		g.drawString(""+partie.getDeckChallengeSize(), butDeckChallenge.getX()+5, butDeckChallenge.getY()+5);
		afficherCarteRetournee(g);
		butDeckWagon.render(container, g);
		g.setColor(Color.black);
		g.drawString(""+partie.getDeckSize(), butDeckWagon.getX()+5, butDeckWagon.getY()+5);
		
		afficherJoueurs(g);
		
		g.setColor(Color.black);
		g.draw(rectCarteJeu);
		
		g.setDrawMode(Graphics.MODE_COLOR_MULTIPLY);
		g.setColor(Color.magenta);
		g.fill(shapeBas);
		g.setDrawMode(Graphics.MODE_NORMAL);
		
		g.setColor(Color.black);
		g.draw(shapeBas);
		
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
			
			g.drawString("Pseudo:   "+tmp.getPseudo(), shapeJoueur[i].getX() + 2, shapeJoueur[i].getY()+3);
			g.drawString("Nb wagon: "+tmp.getNbWagon(), shapeJoueur[i].getX() + 2, shapeJoueur[i].getY()+23);
			g.drawString("Score:    "+tmp.getScore(), shapeJoueur[i].getX() + 2, shapeJoueur[i].getY()+43);
			
			g.drawString("Nb chall:    "+tmp.getChallenges().size(), shapeJoueur[i].getX() + 2, shapeJoueur[i].getY()+63);
			g.drawString("Nb carte:    "+tmp.getCartes().size(), shapeJoueur[i].getX() + 2, shapeJoueur[i].getY()+75);
		}
	}
	
	/**
	 * 
	 * @param g
	 * @param color Couleur du joueur
	 */
	protected void afficherInfoPersoJoueur(Graphics g, int color){
		
		Joueur tmp = partie.getJoueurByColor(color);
		int i=0;
		int a = Colors.getColorId(CarteType.Pink);
		int nb = tmp.compterNbCarteDeTelleCouleur(a);
		if(nb > 0){
			dessinerCarteDuJoueur(g,i,Colors.getColor(a), nb);
			i+=1;
		}
		
		a = Colors.getColorId(CarteType.Yellow);
		nb = tmp.compterNbCarteDeTelleCouleur(a);
		if(nb > 0){
			dessinerCarteDuJoueur(g,i,Colors.getColor(a), nb);
			i+=1;
		}
		
		a = Colors.getColorId(CarteType.Black);
		nb = tmp.compterNbCarteDeTelleCouleur(a);
		if(nb > 0){
			dessinerCarteDuJoueur(g,i,Colors.getColor(a), nb);
			i+=1;
		}
		
		a = Colors.getColorId(CarteType.Blue);
		nb = tmp.compterNbCarteDeTelleCouleur(a);
		if(nb > 0){
			dessinerCarteDuJoueur(g,i,Colors.getColor(a), nb);
			i+=1;
		}
		
		a = Colors.getColorId(CarteType.Green);
		nb = tmp.compterNbCarteDeTelleCouleur(a);
		if(nb > 0){
			dessinerCarteDuJoueur(g,i,Colors.getColor(a), nb);
			i+=1;
		}
		
		a = Colors.getColorId(CarteType.Orange);
		nb = tmp.compterNbCarteDeTelleCouleur(a);
		if(nb > 0){
			dessinerCarteDuJoueur(g,i,Colors.getColor(a), nb);
			i+=1;
		}
		
		a = Colors.getColorId(CarteType.Red);
		nb = tmp.compterNbCarteDeTelleCouleur(a);
		if(nb > 0){
			dessinerCarteDuJoueur(g,i,Colors.getColor(a), nb);
			i+=1;
		}
		
		a = Colors.getColorId(CarteType.White);
		nb = tmp.compterNbCarteDeTelleCouleur(a);
		if(nb > 0){
			dessinerCarteDuJoueur(g,i,Colors.getColor(a), nb);
			i+=1;
		}
		
		a = Colors.getColorId(CarteType.Joker);
		nb = tmp.compterNbCarteDeTelleCouleur(a);
		if(nb > 0){
			dessinerCarteDuJoueur(g,i,Colors.getColor(a), nb);
			i+=1;
		}
		
	}
	
	private void dessinerCarteDuJoueur(Graphics g,final int i,final Color color, final int nbCarte){
		//int wid = (int) shapeBas.getWidth();
		int hei = (int) shapeBas.getHeight();
		int x = (int) shapeBas.getX(), y = (int) shapeBas.getY();
		
		g.setColor(color);
		g.fillRect(x+200 + i*80 + i*10, y+10, 80, hei - 20);
		
		g.setColor(Color.magenta);
		g.drawString(""+nbCarte, x+200 + i*80 + i*10 + 5, y+15);
	}

	protected void afficherCarteRetournee(Graphics g){
		CarteWagon tmp;
		for(int i=0;i<Regles.NB_MAX_CARTE_RETOURNEE;++i){
			tmp = partie.getCarteRetourneeAt(i);
			if(tmp == null){
				//System.err.println("afficherCarteRetournee: tmp == null");
				break;
			}
			g.setColor(Colors.getColor(tmp.getColor()));
			if(tmp instanceof CarteWagonJoker){
				//g.setColor(Color.gray);
				g.fill(shapeCarteRetournee[i]);
				
				g.setColor(Color.black);
				g.drawString("Joker", shapeCarteRetournee[i].getX()+23, shapeCarteRetournee[i].getY()+22);
			}else{
				//g.setColor(Colors.getColor(tmp.getColor()));
				g.fill(shapeCarteRetournee[i]);
			}
		}
	}
	
	
	protected void goto22() {
		container.setMouseGrabbed(false);
		//game.enterState(Game., new FadeOutTransition(), new FadeInTransition());
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	@Override
	public int getID() {
		return Game.PARTIE_VIEW_ID;
	}

}
