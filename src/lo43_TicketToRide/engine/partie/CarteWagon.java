package lo43_TicketToRide.engine.partie;

/**
 * 
 * @author Yoann CAPLAIN
 * @since 14 11 2013
 * 
 */
public class CarteWagon {

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