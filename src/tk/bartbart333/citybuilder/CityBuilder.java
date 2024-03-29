package tk.bartbart333.citybuilder;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.TextureImpl;

import tk.bartbart333.citybuilder.state.State;
import tk.bartbart333.citybuilder.state.States;
import tk.bartbart333.citybuilder.states.StateGame;

/**
 * The main CityBuilder class.
 * @author Barthold
 */
public class CityBuilder {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private static CityBuilder instance;
	
	private long lastframe;
	private float delta;
	
	public CityBuilder(){
		instance = this;
		initGL();
		
		lastframe = getTime();
		
		FontRenderer.init();
		
		State.addState(States.GAME, new StateGame());
		
		State.setState(States.GAME);
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_F4)){
			clearScreen();
			updateDelta();
			
			State.call();
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
		System.exit(0);
	}
	
	/**
	 * Initializes the opengl display.
	 * @author Barthold
	 */
	private void initGL() {
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("CityBuilder");
			Display.create();
			
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Initializes the 2D display.
	 * @author Barthold
	 */
	public void init2D(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}
	
	/**
	 * Initializes the 3D display.
	 * @author Barthold
	 */
	public void init3D(){
		// applies projection transformations
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(100, WIDTH / HEIGHT, 0.001f, 1000);
		
		// changes to the modelview matrix for rendering
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		// reset world transformations
		GL11.glLoadIdentity();
	}
	
	/**
	 * Clears the screen at the beginning of the loop.
	 * @author Barthold
	 */
	private void clearScreen(){
		GL11.glLoadIdentity();
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		TextureImpl.bindNone();
		GL11.glColor3f(1.0f, 1.0f, 1.0f);
	}
	
	/**
	 * Calculates the delta for this frame.
	 * @author Barthold
	 */
	private void updateDelta(){
		long time = getTime();
		delta = (time - lastframe) / 1000f;
		lastframe = time;
	}
	
	/**
	 * Returns a long with the time in seconds.
	 * @author Barthold
	 * @return long Returns a long.
	 */
	public long getTime(){
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	/**
	 * Returns the delta time.
	 * @author Barthold
	 * @return long Returns a float, delta.
	 */
	public float getDelta(){
		return delta;
	}
	
	/**
	 * Returns the CityBuilder object.
	 * @author Barthold
	 * @return CityBuilder object.
	 */
	public static CityBuilder getInstance(){
		return instance;
	}
	
	public static void main(String[] args){
		new CityBuilder();
	}
}