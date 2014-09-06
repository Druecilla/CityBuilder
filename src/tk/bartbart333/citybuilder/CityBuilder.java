package tk.bartbart333.citybuilder;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.newdawn.slick.opengl.TextureImpl;

/**
 * The main CityBuilder class.
 * @author Barthold
 */
public class CityBuilder {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private static CityBuilder instance;
	
	public CityBuilder(){
		instance = this;
		initGL();
		
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_F4)){
			clearScreen();
			
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
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(0, WIDTH / HEIGHT, 0, 1000);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
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