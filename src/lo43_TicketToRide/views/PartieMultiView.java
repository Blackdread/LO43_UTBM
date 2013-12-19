package lo43_TicketToRide.views;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.network.CliqueSouris;
import lo43_TicketToRide.engine.network.Server;
import lo43_TicketToRide.engine.network.ThreadNetworkSender;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class PartieMultiView extends PartiePasseView {

	public int colorOfThisPlayer;
	Server server;
	ThreadNetworkSender sender;
	
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
		
		afficherInfoPersoJoueur(g,colorOfThisPlayer);
		
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		//super.mousePressed(button, x, y); Car on est sur du multi
		//System.out.println("PartieMultiView:mousePressed "+x+" "+y);
		
		// Le clique sur le deck et challenge bug -> c'est du a la bibliotheque Slick2d et le isMouseOver
		
		CliqueSouris cl = new CliqueSouris(button,x,y);
		cl.pressed = true;
		//if(server != null)
			//server.sendAll(cl);
			sender.ajoutMessage(cl);
		//else
			//System.err.println("PartieMultiView:mousePressed server null, c'est pas normal");
		
	}
	
	public void mousePressed(CliqueSouris ob){
		super.mousePressed(ob.button, ob.x, ob.y);
	}
	
	@Override
	public int getID() {
		return Game.PARTIE_MULTI_VIEW_ID;
	}
}
