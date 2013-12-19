package lo43_TicketToRide.engine.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

import lo43_TicketToRide.utils.Configuration;

/**
* Classe cree a la base pour un serveur qui heberge des parties, il a ete adapte pour n'en faire qu'une pour ce projet
* @author Yoann CAPLAIN
* @since 18 04 2013
*/
public class Server implements Runnable{

	private String nameServer;
	private int port = Configuration.getPort();
    private ServerSocket s_server;
    private boolean isServerOn = false;
    
    private HashMap<Integer, ClientServer> hashClients = new HashMap<Integer, ClientServer>();
    
    //private int ID_partie = 0;
    //private HashMap<Integer, Partie> hashPartie = new HashMap<Integer, Partie>();
    
    private boolean continuer = true;
    
    /**
     * -1 = no limits
     */
    private int maxPartieAtTheSameTime = -1;
	
	public Server(String nameServer){
		this.nameServer = nameServer;
		try{
			s_server = new ServerSocket(port);
        }catch(Exception e){e.printStackTrace();}
	}
	
	public void run(){
		System.out.println("Debut du serveur: "+nameServer);
		int i = 0;
		isServerOn = true;
		while(continuer){
			try{
				ClientServer client = new ClientServer(this, s_server.accept(), i);
				System.out.println("Nouveau client: "+nameServer+" et id "+i);
				addClient(client);
				envoyerListeDesJoueursDejaPresent(client);
				i++;
				//envoyerListeDesParties(client);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	    
		try{
			s_server.close();
		}catch(Exception e){e.printStackTrace();}
		System.out.println("Fin du serveur: "+nameServer);
	}
	
	/**
	 * Envoie au joueur arrivant des joueurs dans la partie
	 */
	private void envoyerListeDesJoueursDejaPresent(ClientServer client){
		ListeDesJoueurs tmp = new ListeDesJoueurs();
		for(ClientServer v : hashClients.values())
			if(v != null)
				tmp.vector.add(v.player);
		tmp.colorDeCeluiQuiRecoit = tmp.vector.size()-1;
		tmp.vector.remove(tmp.vector.size()-1);
		client.out.ajouterMessage(tmp);
	}
	
	synchronized public void sendAll(Object ob){
		for(ClientServer v : hashClients.values())
			if(v != null)
				v.getOut().ajouterMessage(ob);
	}
	
	synchronized public void sendAllExceptMe(Object ob, int idMe){
		for(ClientServer v : hashClients.values())
			if(v != null && v.id != idMe)
				v.getOut().ajouterMessage(ob);
	}
	
	// TODO supprimer tous les thread qui ont ete instancie
	public void stopServer(){
		continuer = false;
		isServerOn = false;
		
		for(ClientServer v : hashClients.values())
			if(v != null)
				v.stopThreads();
		
		try {
			s_server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void clientDisconnected(ClientServer client){
		hashClients.remove(client.getId());
	}
	
	synchronized public void addClient(ClientServer client){
    	hashClients.put(client.getId(), client);
    }
	
    synchronized public void removeClient(int id){
    	hashClients.remove(id);
    }
    
    public String getNameServer() {
        return nameServer;
    }

    public void setNameServer(String nameServer) {
        this.nameServer = nameServer;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    /**
     * -1 = no limits
     */
	public int getMaxPartieAtTheSameTime() {
		return maxPartieAtTheSameTime;
	}
	/**
     * -1 = no limits
     */
	public void setMaxPartieAtTheSameTime(int maxPartieAtTheSameTime) {
		this.maxPartieAtTheSameTime = maxPartieAtTheSameTime;
	}

	public synchronized boolean isServerOn() {
		return isServerOn;
	}
}
