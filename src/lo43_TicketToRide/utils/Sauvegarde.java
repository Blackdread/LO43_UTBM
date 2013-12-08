package lo43_TicketToRide.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Sauvegarde {

	/**
	 * Sauvegarde l'objet dans fileLocation
	 * @param fileLocation le chemin, nom fichier et extension
	 * @param ob objet a sauvegarder
	 * @return 
	 */
	public static boolean save(String fileLocation, Object ob){
        try{
            FileOutputStream fos = new FileOutputStream(fileLocation);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(ob);

            oos.close();
            return true;
        }catch(Exception e){e.printStackTrace();}
        return false;
	}
	/**
	 * Charge l'objet
	 * @param fileLocation le chemin, nom fichier et extension
	 * @return null si objet non charger
	 */
	public static Object load(String fileLocation){
		Object ob = null;
        try{
            FileInputStream fos = new FileInputStream(fileLocation);
            ObjectInputStream oos = new ObjectInputStream(fos);
            
            ob = oos.readObject();

            oos.close();
        }catch(Exception e){e.printStackTrace();}
        return ob;
	}
	
}
