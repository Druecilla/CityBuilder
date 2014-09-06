package tk.bartbart333.citybuilder.states;

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
		CityBuilder.getInstance().init3D();
		
		
		
		CityBuilder.getInstance().init2D();
		
		hud.render();
	}
}