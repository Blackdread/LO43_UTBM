package lo43_TicketToRide.engine.network;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import lo43_TicketToRide.engine.partie.Joueur;

/**
*
* @author Yoann CAPLAIN
*/
public class ClientServer {
	
	protected int id;
    protected Socket s;
    protected Server server;
    
    protected Joueur player;

    protected ClientServerIn in;
	protected ClientServerOut out;
    
    public ClientServer(Server ser, Socket s, int id){
    	try {
			s.setTcpNoDelay(true);
		} catch (SocketException e) {
			e.printStackTrace();
		}
        this.s = s;
        server = ser;
        this.id = id;
        in = new ClientServerIn(s,this);
        out = new ClientServerOut(s,this);
        
        Thread tIn = new Thread(in);
        tIn.start();
        Thread tOut = new Thread(out);
        tOut.start();
    }
    
    /**
     * Fonction appeler lorsque la connexion avec le joueur est perdu ou qu'il a quitter tout simplement
     * on notifie la partie, on supprime le client du server et on arrete les Threads (in et out)
     */
    public void clientDisconnected(){
    	server.clientDisconnected(this);
    	
    	stopThreads();
    	
    	try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public void stopThreads(){
    	//in.setContinuer(false);
    	//out.setContinuer(false);
    	
    	in.stop();
    	out.stop();
    	try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

	public Server getServer() {
		return server;
	}

	public int getId() {
		return id;
	}
	
	public String getPseudo() {
		if(player != null)
			return player.getPseudo();
		return null;
	}

	public ClientServerIn getIn() {
		return in;
	}

	public ClientServerOut getOut() {
		return out;
	}
}
