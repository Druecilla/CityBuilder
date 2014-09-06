package tk.bartbart333.citybuilder.states;

import org.lwjgl.opengl.GL11;

import tk.bartbart333.citybuilder.CityBuilder;
import tk.bartbart333.citybuilder.game.Camera;
import tk.bartbart333.citybuilder.game.HUD;
import tk.bartbart333.citybuilder.state.State;

/**
 * The ingame State
 * @author Barthold
 */
public class StateGame extends State{
	
	private Camera camera;
	private HUD hud;
	
	/* (non-Javadoc)
	 * @see tk.bartbart333.citybuilder.state.State#init()
	 */
	@Override
	public void init() {
		camera = new Camera();
		hud = new HUD();
	}

	/* (non-Javadoc)
	 * @see tk.bartbart333.citybuilder.state.State#update()
	 */
	@Override
	public void update() {
		camera.update();
	}

	/* (non-Javadoc)
	 * @see tk.bartbart333.citybuilder.state.State#render()
	 */
	@Override
	public void render() {
		// change matrices to 3D rendering
		CityBuilder.getInstance().init3D();
		
		// render a test squad at y=-10
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex3f(-100, -10, -100);
		GL11.glVertex3f(100, -10, -100);
		GL11.glVertex3f(100, -10, 100);
		GL11.glVertex3f(-100, -10, 100);
		GL11.glEnd();
		
		// change matrices for 2D rendering
		CityBuilder.getInstance().init2D();
		
		hud.render();
	}
}