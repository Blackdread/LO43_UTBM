package lo43_TicketToRide.engine.network;

import java.io.Serializable;

/**
 * Classe a pour notifier par le reseau un clique de souris
 * @author Yoann CAPLAIN
 * @since 18 12 2013
 */
public class CliqueSouris implements Serializable{
	private static final long serialVersionUID = -8366837041723462815L;

	public boolean pressed;
	public boolean released;
	
	public int button;
	public int x;
	public int y;
	
	private CliqueSouris(){
		pressed = false;
		released  = false;
	}
	
	public CliqueSouris(int button,int x,int y){
		this();
		this.button = button;
		this.x = x;
		this.y = y;
	}
}
