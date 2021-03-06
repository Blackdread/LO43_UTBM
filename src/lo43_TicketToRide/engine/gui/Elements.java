package lo43_TicketToRide.engine.gui;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;

public abstract class Elements extends MouseOverArea{
	
	//private int heightElement;	// supposer etre la hauteur du texte ou autre pour centrer tout ca mais pas utile ici pour le moment
	protected int decalageY, decalageX;
	
	public Elements(GUIContext container, Image image, Shape shape) {
		super(container, image, shape);
		// TODO Auto-generated constructor stub
		//this.heightElement=heightElement;
	}

	public Elements(GUIContext container, Image image, int x, int y) {
		super(container, image, x, y);
		// TODO Auto-generated constructor stub
		//this.heightElement=heightElement;
	}

	public Elements(GUIContext container, Image image, int x, int y,
			ComponentListener listener) {
		super(container, image, x, y, listener);
		// TODO Auto-generated constructor stub
		//this.heightElement=heightElement;
	}

	public Elements(GUIContext container, Image image, int x, int y,
			int width, int height) {
		super(container, image, x, y, width, height);
		// TODO Auto-generated constructor stub
		//this.heightElement=heightElement;
	}

	public Elements(GUIContext container, Image image, int x, int y,
			int width, int height, ComponentListener listener) {
		super(container, image, x, y, width, height, listener);
		// TODO Auto-generated constructor stub
		//this.heightElement=heightElement;
	}

	
	public abstract boolean isElementUsed();
	
	public abstract String toString();
	
	//public abstract void render(GUIContext container, Graphics g);
	
	public abstract void render(GUIContext container, Graphics g, int x, int y);
	
	public abstract void renderString(GUIContext container, Graphics g, int x, int y);

	public int getDecalageY() {
		return decalageY;
	}

	public int getDecalageX() {
		return decalageX;
	}

	public void setDecalageY(int decalageY) {
		this.decalageY = decalageY;
	}

	public void setDecalageX(int decalageX) {
		this.decalageX = decalageX;
	}
	
}
