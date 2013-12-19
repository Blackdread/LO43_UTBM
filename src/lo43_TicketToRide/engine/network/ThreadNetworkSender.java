package lo43_TicketToRide.engine.network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Le thread client qui possede une file de message et envoie ca sur le flux du socket
 * @author Yoann CAPLAIN
 *
 */
public class ThreadNetworkSender implements Runnable{
	private Socket sock;
	private ObjectOutputStream oos;
	
	private Queue<Object> message_queue = new LinkedList<Object>();
	boolean active;
	

	public ThreadNetworkSender(Socket s) throws IOException {
		s.setTcpNoDelay(true);
		sock = s;
		oos = new ObjectOutputStream(sock.getOutputStream());
	}

	@Override
	public void run() {
		active = true;
		while(active)
		{
			Object m;
			if((m = retirerMessage()) != null)
			{
				try {
					oos.writeObject(m);
					oos.flush();
					oos.reset();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		try {
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	synchronized public void ajoutMessage(Object m)
	{
		if(m != null)
		{
			message_queue.add(m);
		}
	}
	
	synchronized public Object retirerMessage()
	{
		return message_queue.poll();
	}
	
	public void desactive()
	{
		active = false;
	}

}
