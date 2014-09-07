package tk.bartbart333.citybuilder.game;

import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTranslatef;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import tk.bartbart333.citybuilder.CityBuilder;
import tk.bartbart333.citybuilder.math.Vector3f;


/**
 *
 * @author Barthold
 * @author Stef Siekman
 */
public class Camera {
	
	/**
	 * Scalar that the movement input will be multiplied by. In other words this
	 * is the speed the camera will move by, in meters/second.
	 */
	private static final float MOVEMENT_SCALAR = 50;
	/**
	 * Scalar that the rotation input will be multiplied by. 
	 */
	private static final float ROTATION_SCALAR = 0.2f;
	
	private Vector3f position;
	private Vector3f rotation;
	private float distance;
	
	/**
	 * Sets up the camera at the default position, rotation and zooming distance.
	 * @author Stef Siekman
	 */
	public Camera() {
		position = new Vector3f(100, 0, 100);
		rotation = new Vector3f(45, 0, 0);
		distance = 50;
	}

	/**
	 * Updates the camera position and rotation corresponding to the user's
	 * input.
	 * @author Barthold
	 * @author Stef Siekman
	 */
	public void update() {
		// Get the WASD direction
		Vector3f movement = new Vector3f();
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) movement.z += 1;
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) movement.z -= 1;
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) movement.x -= 1;
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) movement.x += 1;
		
		// If any keys pressed
		if (movement.x != 0 || movement.z != 0) {
			// Make the direction vector 1 in length
			movement.normalize();
			// Scale the direction by the delta and m/s to get a movement
			movement.mul(CityBuilder.getInstance().getDelta() * MOVEMENT_SCALAR);
			// Apply this movement to the position vector
			position.x += movement.x;
			position.z += movement.z;
		}
		
		// If the left mouse button is pressed
		if (Mouse.isButtonDown(0)) {
			// get the rotation input and multiply by scalar
			float rotY = Mouse.getDX() * ROTATION_SCALAR;
			float rotX = -Mouse.getDY() * ROTATION_SCALAR;
			
			// Apply the rotation
			rotation.x += rotX;
			rotation.y += rotY;
		}
	}
	
	/**
	 * Applies the transformations the camera should make in other to place
	 * the camera at the right position and rotation. This function should be
	 * called after a glLoadIdentity and before rendering anything.
	 * @author Stef Siekman
	 */
	public void applyTransform() {
		// reset the world transformations
		glLoadIdentity();
		
		// move the camera backwards
		glTranslatef(0, 0, -distance);
		
		// rotate the world
		glRotatef(rotation.x, 1, 0, 0);
		glRotatef(rotation.y, 0, 1, 0);
		glRotatef(rotation.z, 0, 0, 1);
		
		// move to the camera's position in the world
		glTranslatef(-position.x, -position.y, position.z);
	}
}