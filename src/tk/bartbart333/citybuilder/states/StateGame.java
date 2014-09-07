package tk.bartbart333.citybuilder.states;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

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
	private Texture test;
	
	/* (non-Javadoc)
	 * @see tk.bartbart333.citybuilder.state.State#init()
	 */
	@Override
	public void init() {
		camera = new Camera();
		hud = new HUD();
		try {
			test = TextureLoader.getTexture("PNG", new FileInputStream(new File("./assets/test_texture.png")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		
		camera.applyTransform();
		
		// render a test squad at y=-10
		test.bind();
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-100, 0, -100);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(100, 0, -100);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(100, 0, 100);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(-100, 0, 100);
		GL11.glEnd();
		
		camera.renderDebugModel();
		
		// change matrices for 2D rendering
		CityBuilder.getInstance().init2D();
		
		hud.render();
	}
}