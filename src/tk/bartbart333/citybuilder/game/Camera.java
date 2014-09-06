package tk.bartbart333.citybuilder.game;

import org.lwjgl.util.vector.Vector3f;

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
	 * @author Stef Siekman
	 */
	public void update() {
	}
	
	/**
	 * Applies the transformations the camera should make in other to place
	 * the camera at the right position and rotation. This function should be
	 * called after a glLoadIdentity and before rendering anything.
	 * @author Stef Siekman
	 */
	public void applyTransform() {
		
	}
}