package lo43_TicketToRide.engine.gui;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.MouseOverArea;
/**
 * @TODO Pas encore terminer
 * @author Yoann CAPLAIN
 * @since 17 11 2013
 */
public class ButtonMultiValue extends MouseOverArea{
	public ButtonMultiValue(GUIContext container, Image image, Shape shape) {
		super(container, image, shape);
	}

	public ButtonMultiValue(GUIContext container, Image image, int x, int y) {
		super(container, image, x, y);
	}

	public ButtonMultiValue(GUIContext container, Image image, int x, int y,
			ComponentListener listener) {
		super(container, image, x, y, listener);
	}

	public ButtonMultiValue(GUIContext container, Image image, int x, int y,
			int width, int height) {
		super(container, image, x, y, width, height);
	}

	public ButtonMultiValue(GUIContext container, Image image, int x, int y,
			int width, int height, ComponentListener listener) {
		super(container, image, x, y, width, height, listener);
	}
}
