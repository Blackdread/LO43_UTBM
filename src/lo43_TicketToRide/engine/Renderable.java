package lo43_TicketToRide.engine;

import org.newdawn.slick.Graphics;

public interface Renderable {

	/**
	 * la classe implemente deja une position
	 * @param g Graphics
	 */
	public void render(Graphics g);
	
	public void render(Graphics g, int x, int y);
}
