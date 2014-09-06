package tk.bartbart333.citybuilder;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

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