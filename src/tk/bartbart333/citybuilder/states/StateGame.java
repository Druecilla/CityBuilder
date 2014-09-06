package tk.bartbart333.citybuilder.states;

import org.lwjgl.opengl.GL11;

import tk.bartbart333.citybuilder.CityBuilder;
import tk.bartbart333.citybuilder.game.Camera;
import tk.bartbart333.citybuilder.state.State;

/**
 * The ingame State
 * @author Barthold
 */
public class StateGame extends State{
	
	private Camera camera;
	
	/* (non-Javadoc)
	 * @see tk.bartbart333.citybuilder.state.State#init()
	 */
	@Override
	public void init() {
		camera = new Camera();
	}

	/* (non-Javadoc)
	 * @see tk.bartbart333.citybuilder.state.State#update()
	 */
	@Override
	public void update() {
		camera.update();
		
		CityBuilder.getInstance().init3D();
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(-100, -10, -100);
		GL11.glVertex3f(100, -10, -100);
		GL11.glVertex3f(100, -10, 100);
		GL11.glVertex3f(-100, -10, 100);
		GL11.glEnd();
		
		CityBuilder.getInstance().init2D();
		
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2i(0, 0);
		GL11.glVertex2i(100, 0);
		GL11.glVertex2i(100, 100);
		GL11.glVertex2i(0, 100);
		GL11.glEnd();
	}

	/* (non-Javadoc)
	 * @see tk.bartbart333.citybuilder.state.State#render()
	 */
	@Override
	public void render() {
		
	}
}