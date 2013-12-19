package lo43_TicketToRide.engine.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import lo43_TicketToRide.engine.Game;
import lo43_TicketToRide.engine.partie.Joueur;
import lo43_TicketToRide.engine.partie.Partie;
import lo43_TicketToRide.views.MainMenuMultiView;
import lo43_TicketToRide.views.PartieMultiView;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 18 04 2013
 */
public class ClientServerIn implements Runnable{
    private Socket s;
    private ClientServer clientServer;
    private ObjectInputStream in;
    private boolean continuer = true;
     
    public ClientServerIn(Socket soc, ClientServer clientServer){
    	s = soc;
    	this.clientServer = clientServer;
    }
    
    public void run(){
        System.out.println("clientIn demarrer");
        try{
        	in = new ObjectInputStream(s.getInputStream());
        	
        	while(continuer){
	        	Object ob = in.readObject();
	        	clientServer.server.sendAll(ob);
	        	//*
	        	if(ob instanceof Joueur){
	        		clientServer.player = new Joueur((Joueur)ob);
	        	}else if(ob instanceof Partie){
	        		((PartieMultiView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).setPartie(null);
	        		((PartieMultiView)Game.getStateByID(Game.PARTIE_MULTI_VIEW_ID)).setPartie((Partie) ob);
	        		((MainMenuMultiView)Game.getStateByID(Game.MAIN_MENU_MULTI_VIEW_ID)).goToGame();
	        	}
	        	//*
	        	try {
					Thread.sleep(10);
					// Ou attendre
					//this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	        	//*/
        	}
        	
        }catch(Exception e){
        	e.printStackTrace();
        	continuer=false;
        }
		clientServer.clientDisconnected();
		
        System.out.println("clienIn fin "+clientServer.getId());
    }

    synchronized public void stop(){
    	continuer=false;
    	try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	public void setContinuer(boolean continuer) {
		this.continuer = continuer;
	}
}
