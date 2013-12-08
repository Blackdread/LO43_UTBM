package lo43_TicketToRide.engine.partie;

import java.io.Serializable;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 14 11 2013
 * 
 */
public class CarteWagon implements Serializable{

  /**
   * 
   */
	private static final long serialVersionUID = 2206179139888856797L;
	protected int color;

  public CarteWagon(){
	  color = -1;
  }
  public CarteWagon(int color){
	  this.color = color;
  }
  
  public CarteWagon(CarteWagon copy){
	  color = copy.color;
  }
  
  public int getColor() {
	  return color;
  }
	
  public void setColor(int color) {
	  this.color = color;
  }

    
}