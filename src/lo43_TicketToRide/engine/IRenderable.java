package lo43_TicketToRide.engine;

import org.newdawn.slick.Graphics;

public interface IRenderable {

	/**
	 * la classe implemente doit avoir une "position"
	 * @param g Graphics
	 */
	//public void render(Graphics g);
	
	/**
	 * @Deprecated Il faut utiliser les fonctions scale, translate, rotate qui se trouvent dans Graphics.java
	 * J'ai decouvert ces fonctions un peu tard... Donc public void render(Graphics g); est mieux, il faut donc
	 * faire appel aux autres methodes avant le render(g) ainsi on a bien un translate si on souhaite, etc
	 * @param g
	 * @param deltaX decalage coin gauche haut sur les x
	 * @param deltaY decalage coin gauche haut sur les y
	 */
	@Deprecated
	public void render(Graphics g, final int deltaX, final int deltaY);
	
}
