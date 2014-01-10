package lo43_TicketToRide;

import java.io.IOException;

import lo43_TicketToRide.engine.Game;

import org.newdawn.slick.SlickException;


/**
 * 
 * @author Yoann CAPLAIN
 * @since 09 10 2012
 */
public class Launch {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//*
		try {
			Game g;
			// Ou utilise la proprite : file.separator=\    => serait bien de le mettre plus tard
			String OSname = System.getProperties().getProperty("os.name");
			if(OSname.equalsIgnoreCase("Mac OS X") || OSname.startsWith("linux")){
				// Linux supporte les / normalement
				g = new Game("config/config.properties", "resources/");
			}else
				g = new Game("config\\config.properties", "resources\\");
			
			g.launch();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SlickException e) {
			e.printStackTrace();
		}//*/
	}

}
