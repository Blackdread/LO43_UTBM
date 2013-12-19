package lo43_TicketToRide.engine.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.partie.Joueur;
import lo43_TicketToRide.engine.partie.Partie;
import lo43_TicketToRide.views.MainMenuMultiView;
import lo43_TicketToRide.views.PartieMultiView;

/**
 * Le thread client qui recoit les messages
 * @author Yoann CAPLAIN
 *
 */
public class ThreadNetworkListener implements Runnable{
	private Socket sock;
	private ObjectInputStream ois;
	boolean active;
	
	
	public ThreadNetworkListener(Socket s) throws UnknownHostException, IOException {
		sock = s;
		ois = new ObjectInputStream(sock.getInputStream());
	}

	@Override
	public void run() {
		active = true;
		while(active)
		{
			try {
				Object ob = ois.readObject();
				if(ob instanceof ListeDesJoueurs){
					for(Joueur v : ((ListeDesJoueurs)ob).vector)
						if(v!=null)
							((MainMenuMultiView)Game.getStateByID(Game.MAIN_MENU_MULTI_VIEW_ID)).ajouterUnNouveauJoueur(v.getPseudo());
					((PartieMultiView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).colorOfThisPlayer = ((ListeDesJoueurs)ob).colorDeCeluiQuiRecoit;
				}else if(ob instanceof Joueur){
					((MainMenuMultiView)Game.getStateByID(Game.MAIN_MENU_MULTI_VIEW_ID)).ajouterUnNouveauJoueur(((Joueur)ob).getPseudo());
				}else if(ob instanceof Partie){
	        		((PartieMultiView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).setPartie(null);
	        		((PartieMultiView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).setPartie((Partie) ob);
	        		((MainMenuMultiView)Game.getStateByID(Game.MAIN_MENU_MULTI_VIEW_ID)).goToGame();
	        	}else if(ob instanceof CliqueSouris){
	        		// c pas une erreur, je veux utiliser la classe au-dessus de multi
	        		if(((CliqueSouris)ob).pressed){
	        			//((PartiePasseView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).mousePressed(((CliqueSouris)ob).button, ((CliqueSouris)ob).x, ((CliqueSouris)ob).y);
	        			((PartieMultiView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).mousePressed((CliqueSouris)ob);
	        		}else{
	        			//((PartiePasseView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).mouseReleased(((CliqueSouris)ob).button, ((CliqueSouris)ob).x, ((CliqueSouris)ob).y);
	        		}
	        	}
			} catch (IOException e) {
				e.printStackTrace();
				active = false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		try {
			ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void desactive()
	{
		active = false;
	}

}
