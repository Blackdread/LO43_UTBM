package lo43_TicketToRide.views;

import lo43_TicketToRide.engine.Game;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 * Juste pour le fun
 * @author Yoann CAPLAIN
 *
 */
public class FunView extends View {

	/** The current scale applied to the graphics context */
	private float scale = 0.5f;
	/** True if we should be scaling up */
	private boolean scaleUp;
	/** True if we should be scaling down */
	private boolean scaleDown;
	
	private boolean isRot = false;
	private float rot = 0.0f;
	
	@Override
	public void initResources() {
		
	}

	public void render(GameContainer container, StateBasedGame sbGame, Graphics g) {
		g.translate(320,240);
		g.scale(scale, scale);

		g.setColor(Color.red);
		g.drawString("touches q,a,1,2", 10, 50);
		for (int x=0;x<10;x++) {
			for (int y=0;y<10;y++) {
				if(x%2==0)
					//g.rotate(-500+(x*100), -500+(y*100), rot);
					//g.rotate(640/2, 480/2, rot);
				//else
					g.rotate(container.getWidth()/2, container.getHeight()/2, rot);
				//g.scale(scale, scale);
				g.fillRect(-500+(x*100), -500+(y*100), 80, 80);
				//if(x%2==0)
				g.resetTransform();
				g.translate(320,240);
				g.scale(scale, scale);
				
			}
		}
		
		g.setColor(Color.green);
	      for (int x=0;x<10;x++) {
	         for (int y=0;y<10;y++) {
	        	 if(x%3==0)
	        		 g.rotate(container.getWidth()/2, container.getHeight()/2, rot);
	            g.fillRect(100+(x*100), 100+(y*100), 80, 80);
	            if(x%2==0)
	            	g.resetTransform();
	            g.translate(320,240);
	            g.scale(scale, scale);
	         }
	      }
	     
	      g.setColor(Color.yellow);
	      for (int x=0;x<10;x++) {
	         for (int y=0;y<10;y++) {
	        	 //if(x%4==0)
	        		 g.rotate(container.getWidth()/2-200, container.getHeight()/2+349, rot);
	        		 
	            g.fillRect(400+(x*100), (y*100), 10, 10);
	            if(x%5==0)
	            	g.resetTransform();
	            g.translate(320,240);
	            //g.scale(scale, scale);
	         }
	      }
	      
	      g.setColor(Color.white);
	      for (int x=0;x<5;x++) {
	         for (int y=0;y<4;y++) {
	        	 //if(x%4==0)
	        	g.rotate(400+(x*100)+25, (y*100)+25, rot);
	            g.fillRect(400+(x*100), (y*100), 50, 50);
	            
	            g.resetTransform();
	           // g.translate(320,240);
	            g.scale(scale, scale);
	         }
	      }
	      
	      
		/*
		g.setColor(new Color(1,1,1,0.5f));
		g.fillRect(-320,-240,640,480);
		g.setColor(Color.white);
		g.drawRect(-320,-240,640,480);
		*/
	}

	public void update(GameContainer container, StateBasedGame sbGame, int delta) {
		if (scaleUp) {
			scale += delta * 0.001f;
		}
		if (scaleDown) {
			scale -= delta * 0.001f;
		}
		if(isRot){
			rot+=1;
		}
	}

	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			container.setMouseGrabbed(false);
			game.enterState(Game.RESOURCES_STATE_ID, new FadeOutTransition(), new FadeInTransition());
		}
		if (key == Input.KEY_Q) {
			scaleUp = true;
		}
		if (key == Input.KEY_A) {
			scaleDown = true;
		}
		if(key == Input.KEY_1){
			rot+=1;
			isRot = true;
		}
		if(key == Input.KEY_2)
			rot-=1;
	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_Q) {
			scaleUp = false;
		}
		if (key == Input.KEY_A) {
			scaleDown = false;
		}
		if(key == Input.KEY_1){
			rot+=1;
			isRot = false;
		}
	}

	
	@Override
	public int getID() {
		return Game.FUN_VIEW_ID;
	}
}
