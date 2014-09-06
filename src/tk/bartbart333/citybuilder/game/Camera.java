package tk.bartbart333.citybuilder.game;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import tk.bartbart333.citybuilder.CityBuilder;

/**
 *
 * @author Barthold
 */
public class Camera {
	
	private Vector3f rotation = new Vector3f();
	private Vector3f position = new Vector3f();
	private float speed = 0.5f;
	
	public Camera(){
		
	}

	/**
	 * Updates the Camera class.
	 * @author Barthold
	 */
	public void update() {
		updateRotation();
		updatePosition();
		updateCamera();
	}

	/**
	 * Updates the Rotation.
	 * @author Barthold
	 */
	private void updateRotation() {
		
	}

	/**
	 * Updates the Position.
	 * @author Barthold
	 */
	private void updatePosition() {
		if(Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.x -= Math.sin(-rotation.y * Math.PI / 180) * speed * CityBuilder.getInstance().getDelta();
			position.z -= Math.cos(-rotation.y * Math.PI / 180) * speed * CityBuilder.getInstance().getDelta();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.x += Math.sin(-rotation.y * Math.PI / 180) * speed * CityBuilder.getInstance().getDelta();
			position.z += Math.cos(-rotation.y * Math.PI / 180) * speed * CityBuilder.getInstance().getDelta();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x += Math.sin((-rotation.y - 90) * Math.PI / 180) * speed * CityBuilder.getInstance().getDelta();
			position.z += Math.cos((-rotation.y - 90) * Math.PI / 180) * speed * CityBuilder.getInstance().getDelta();
		}
		
		if(Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x += Math.sin((-rotation.y + 90) * Math.PI / 180) * speed * CityBuilder.getInstance().getDelta();
			position.z += Math.cos((-rotation.y + 90) * Math.PI / 180) * speed * CityBuilder.getInstance().getDelta();
		}
	}

	/**
	 * Updates the Camera.
	 * @author Barthold
	 */
	private void updateCamera() {
		GL11.glRotatef(rotation.x, 1, 0, 0);
		GL11.glRotatef(rotation.y, 0, 1, 0);
		GL11.glRotatef(rotation.z, 0, 0, 1);

		GL11.glTranslatef(-position.x, -position.y, -position.z);
	}
}