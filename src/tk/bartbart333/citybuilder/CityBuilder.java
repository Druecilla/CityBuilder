package tk.bartbart333.citybuilder;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class CityBuilder {
	
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	
	private static CityBuilder instance;
	
	public CityBuilder(){
		instance = this;
		initGL();
		
		while(!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_F4)){
			
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
		System.exit(0);
	}
	
	/**
	 * @author Barthold
	 * Initializes the opengl display.
	 */
	private void initGL() {
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.setTitle("CityBuilder");
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @author Barthold
	 * Initializes the 2D display.
	 */
	public void init2D(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}
	
	/**
	 * @author Barthold
	 * Initializes the 3D display.
	 */
	public void init3D(){
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GLU.gluPerspective(0, WIDTH / HEIGHT, 0, 1000);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}

	/**
	 * @author Barthold
	 * Returns the CityBuilder object.
	 * @return CityBuilder object.
	 */
	public static CityBuilder getInstance(){
		return instance;
	}
	
	public static void main(String[] args){
		new CityBuilder();
	}
}