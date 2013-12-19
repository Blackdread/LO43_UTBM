package lo43_TicketToRide.views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Vector;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.Regles;
import lo43_TicketToRide.engine.factory.FactoryCarteJeu;
import lo43_TicketToRide.engine.factory.FactoryChallenges;
import lo43_TicketToRide.engine.factory.FactoryPartie;
import lo43_TicketToRide.engine.network.Server;
import lo43_TicketToRide.engine.network.ThreadNetworkListener;
import lo43_TicketToRide.engine.network.ThreadNetworkSender;
import lo43_TicketToRide.engine.partie.Joueur;
import lo43_TicketToRide.engine.partie.Partie;
import lo43_TicketToRide.utils.Configuration;
import lo43_TicketToRide.utils.ResourceManager;



/**
 * The main menu of the game. Severals sub menus are linked here like:
 * 
 * menu Options menu Credits menu ...
 * 
 * @author Yoann CAPLAIN
 * 
 */
public class MainMenuMultiView extends MainMenuView {
	
	protected MouseOverArea rejoindreServeur, lancerServeur;
	protected TextField ipRejoindre;
	protected Rectangle rectHeberger,rectRejoindre;
	
	protected String ipLocal = "", ipInternet = "";
	/**
	 * si le joueur heberge
	 */
	protected boolean isHeberge = false;
	/**
	 * si le joueur a rejoins une partie
	 */
	protected boolean isJoin = false;
	
	protected Server server;
	protected Thread threadServer;
	
	protected Socket sock;
	protected ThreadNetworkListener runList;
	protected ThreadNetworkSender runSend;
	protected Thread threadList;
	protected Thread threadSend;

	@Override
	public void initResources() {
		super.initResources();
		
		butMulti.setNormalImage(ResourceManager.getImage("butMultiOver"));
		
		for(int i=0;i<Regles.NB_MAX_JOUEUR;++i)
			super.textFieldPseudo[i].setAcceptingInput(false);
		
		ipRejoindre = new TextField(container, container.getDefaultFont(), container.getWidth()/2+130-300, container.getHeight()/2+10 , 150, 20);
		ipRejoindre.setMaxLength(15);
				
		rectHeberger = new Rectangle(container.getWidth()/2-300-60, container.getHeight()/2-15,40,20);
		rectRejoindre = new Rectangle(container.getWidth()/2-300-60, container.getHeight()/2+10,40,20);
		
		try {
			ipLocal = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		URL u;
		try {
			// j'ai trouve cette addresse qui donne directement notre IP, c'est pratique, 
			// ca evite d'avoir a aller sur monIp.com
			u = new URL("http://lufrima.free.fr/ip.php");
			InputStream i = u.openStream();
			BufferedReader b = new BufferedReader(new InputStreamReader(i));
			ipInternet = b.readLine();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		isJoueur[0] = false;
		textFieldPseudo[0].setText("none");
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		super.enter(container, game);

	}
	
	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException {
		super.leave(container, game);
		
	}
	
	@Override
	public void update(GameContainer container, StateBasedGame sbGame, int delta) throws SlickException {
		super.update(container, sbGame, delta);
		
		
	}
	
	@Override
	public void render(GameContainer container, StateBasedGame sbgame, Graphics g) throws SlickException {	
		super.render(container, sbgame, g);
		
		
		g.drawString("ip locale = "+ipLocal+" ip internet = "+ipInternet,container.getWidth()/2-300, container.getHeight()/2-40);
		if(!isHeberge && !isJoin){
			g.drawString("HŽberger une partie", container.getWidth()/2-300, container.getHeight()/2-15);
			g.draw(rectHeberger);
			g.drawString("Rejoindre IP :", container.getWidth()/2-300, container.getHeight()/2+10);
			g.draw(rectRejoindre);
			ipRejoindre.render(container, g);
		}
		
		if(isHeberge || isJoin){
			butLancerGame.render(container, g);
			super.afficherPseudo(g);
			super.afficherCouleur(g);
		}
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
		
		if(!isHeberge && !isJoin){
			if(rectHeberger.contains(x, y)){
				isHeberge = true;
				server = new Server("heberger");
				threadServer = new Thread(server);
				threadServer.start();
				((PartieMultiView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).server = server;
				rejoindreServerViaIp("127.0.0.1");
			}
			if(rectRejoindre.contains(x, y)){
				isJoin = true;
				rejoindreServerViaIp(ipRejoindre.getText());
			}
		}
	}
	
	private void rejoindreServerViaIp(String ip){
		if(!ip.equalsIgnoreCase("")){
			try {
				sock = new Socket(ip, Configuration.getPort());
				runList = new ThreadNetworkListener(sock);
				threadList = new Thread(runList);
				threadList.start();
				runSend = new ThreadNetworkSender(sock);
				((PartieMultiView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).sender = runSend;
				runSend.ajoutMessage(new Joueur(Configuration.getPseudo()));
				threadSend = new Thread(runSend);
				threadSend.start();
				System.out.println("Connecter au serveur");
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void ajouterUnNouveauJoueur(String pseudo){
		//joueur.add(new Joueur(textFieldPseudo[0].getText(), colors[0],false));
		for(int i=0;i<Regles.NB_MAX_JOUEUR;++i)
			if(!isJoueur[i]){//&& !isIA[i])
				isJoueur[i] = true;
				textFieldPseudo[i].setText(pseudo);
				break;
			}
	}
	
	@Override
	protected void gotoLancerPartie(){
		Vector<Joueur> joueur = new Vector<Joueur>(nbJoueur());
		
		// Ajout des joueurs
		joueur.add(new Joueur(textFieldPseudo[0].getText(), colors[0],false));
		for(int i=1;i<Regles.NB_MAX_JOUEUR;++i)
			if(isJoueur[i] || isIA[i])
				joueur.add(new Joueur(textFieldPseudo[i].getText(), colors[i],isIA[i]));
		
		//if(joueur.size() > 1){
			// Creation de la partie
			Partie partie = FactoryPartie.getInstance().creerPartie(joueur);
			// Ajout de la carte de jeu
			partie.setCarteJeu(FactoryCarteJeu.getInstance().creerCarteJeu(0));
			
			// Creation et Ajout des cartes wagons
			// Fait dans FactoryPartie
			
			// Creation et Ajout des challenges
			partie.ajouterChallenge(FactoryChallenges.getInstance().creerChallenges(partie.getCarteJeu(), 0));
			
			partie.initialiserPartie();
			
			// Init la partie
			((PartieMultiView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).setPartie(partie);
			//((PartieMultiView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).server = server;
			
			server.sendAll(partie);
			//container.setMouseGrabbed(false);
			//game.enterState(Game.PARTIE_MULTI_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
		//}
	}

	public void goToGame(){
		container.setMouseGrabbed(false);
		game.enterState(Game.PARTIE_MULTI_VIEW_ID, new FadeOutTransition(), new FadeInTransition());
	}
	
	@Override
	protected int nbJoueur() {
		int somme = 1;
		for(int i=1;i<Regles.NB_MAX_JOUEUR;++i)
				if(isIA[i] || !(textFieldPseudo[i].getText().startsWith("IA")))
					somme+=1;
		return somme;
	}
	
	@Override
	public int getID() {
		return Game.MAIN_MENU_MULTI_VIEW_ID;
	}

}
