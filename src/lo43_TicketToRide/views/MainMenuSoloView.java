package lo43_TicketToRide.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.Regles;
import lo43_TicketToRide.utils.ResourceManager;



/**
 * The main menu of the game. Severals sub menus are linked here like:
 * 
 * menu Options menu Credits menu ...
 * 
 * @author Yoann CAPLAIN
 * 
 */
public class MainMenuSoloView extends MainMenuView {

	@Override
	public void initResources() {
		super.initResources();
		
		super.textFieldPseudo[0].setAcceptingInput(false);
		butSolo.setNormalImage(ResourceManager.getImage("butSoloOver"));
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		super.render(container, sbgame, g);
		
		super.afficherSwitchIA(g);
		super.afficherPseudo(g);
		super.afficherCouleur(g);
		
		butLancerGame.render(container, g);
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy){
		super.mouseMoved(oldx, oldy, newx, newy);
		
	}
	
	@Override
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		
		
	}
	@Override
	public void mousePressed(int button, int x, int y) {
		super.mousePressed(button, x, y);
		
		super.switchIA();
		
	}
	
	@Override
	protected int nbJoueur(){
		int somme = 1;
		for(int i=1;i<Regles.NB_MAX_JOUEUR;++i)
				if(isIA[i])
					somme+=1;
		return somme;
	}
	
	/* Remonter sur classe mere
	@Override
	protected void gotoLancerPartie(){
		
		Vector<Joueur> joueur = new Vector<Joueur>(nbJoueur());
		
		// Ajout des joueurs
		joueur.add(new Joueur(textFieldPseudo[0].getText(), colors[0],false));
		for(int i=1;i<Regles.NB_MAX_JOUEUR;++i)
			joueur.add(new Joueur(textFieldPseudo[i].getText(), colors[i],isIA[i]));
		
		// Creation de la partie
		Partie partie = FactoryPartie.getInstance().creerPartie(joueur);
		// Ajout de la carte de jeu
		partie.setCarteJeu(FactoryCarteJeu.getInstance().creerCarteJeu());
		
		// Creation et Ajout des cartes wagons
		
	}*/

	@Override
	public int getID() {
		return Game.MAIN_MENU_SOLO_VIEW_ID;
	}

}
