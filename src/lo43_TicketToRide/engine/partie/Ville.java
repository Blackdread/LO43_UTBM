package lo43_TicketToRide.engine.partie;

import java.io.Serializable;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import lo43_TicketToRide.engine.IPosition;
import lo43_TicketToRide.engine.IRenderable;

/**
 * Represente une UV
 * @author Yoann CAPLAIN
 * @since 23 11 2013
 * 
 */
public class Ville implements IRenderable, IPosition, Serializable{
  
	private static final long serialVersionUID = -2973170336488215261L;
	protected int x,y;
    protected String nomUV;

  
  public Ville(String nom){
	  nomUV = nom;
  }
  
  public Ville(Ville copy){
	  nomUV = copy.nomUV;
  }

	public String getNomUV() {
		return nomUV;
	}

	@Override
	public void render(Graphics g,final int deltaX,final int deltaY) {
		g.setColor(Color.black);
		g.drawString(""+nomUV, x+deltaX-15, y+deltaY-15);
		
		g.drawOval(x+deltaX, y+deltaY, 10, 10);
		g.setColor(Color.red);
		g.fillOval(x+deltaX, y+deltaY, 10, 10);
	}

	@Override
	public float getX() {
		return x;
	}

	@Override
	public float getY() {
		return y;
	}

	@Override
	public void setX(float x) {
		this.x = (int)x;
	}

	@Override
	public void setY(float y) {
		this.y = (int)y;
	}

	@Override
	public void setLocation(float x, float y) {
		this.x = (int)x;
		this.y = (int)y;
	}
  
	@Override
	public boolean equals(Object a){
		if(a instanceof Ville)
			if(nomUV.equalsIgnoreCase(((Ville)a).nomUV))
				return true;
		return false;
	}
  
  
}