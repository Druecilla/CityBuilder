package tk.bartbart333.citybuilder.game;

import static org.lwjgl.opengl.GL11.GL_COMPILE;
import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glCallList;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glEndList;
import static org.lwjgl.opengl.GL11.glGenLists;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glNewList;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex3f;

import java.io.File;
import java.io.FileInputStream;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import tk.bartbart333.citybuilder.CityBuilder;
import tk.bartbart333.citybuilder.math.Rectangle;
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
	/**
	 * Scalar that the scroll input will be multiplied by. 
	 */
	private static final float SCROLL_SCALAR = 0.4f;
	
	private static final float MIN_X_ROTATION = 20f;
	private static final float MAX_X_ROTATION = 90f;
	private Rectangle bounds = null;
	
	private Vector3f position;
	private Vector3f rotation;
	private float distance;
	private float scroll;
	
	private int meshListId = -1;
	private int texId = -1;
	
	/**
	 * Sets up the camera at the default position, rotation and zooming distance.
	 * @author Stef Siekman
	 */
	public Camera() {
		position = new Vector3f(100, 0, 100);
		rotation = new Vector3f(45, 0, 0);
		scroll = 500;
		distance = (float)Math.pow(1.007, scroll) + 2;
		
		try {
			// load the camera's texture
			Texture tex = TextureLoader.getTexture("png",
					new FileInputStream(new File("./assets/camera_model.png")),
					GL_NEAREST);
			// get the id from the texture
			texId = tex.getTextureID();
		} catch(Exception ex) {
			System.err.println("Failed to init the texture for the camera:");
			ex.printStackTrace();
			System.exit(1);
		}
		
		// create a display list id to hold the list
		meshListId = glGenLists(1);
		
		// insert the data into the list
		glNewList(meshListId, GL_COMPILE);
			final float HALF = 1;
			glBegin(GL_QUADS);
				// set the texture
				glBindTexture(GL_TEXTURE_2D, texId);
				glColor3f(1, 1, 1);
				
				// setup the top
				glTexCoord2f(.5f, .5f);
				glVertex3f(HALF, HALF, HALF);
				glTexCoord2f(0, .5f);
				glVertex3f(-HALF, HALF, HALF);
				glTexCoord2f(0, 0);
				glVertex3f(-HALF, HALF, -HALF);
				glTexCoord2f(.5f, 0);
				glVertex3f(HALF, HALF, -HALF);
				
				// setup front
				glTexCoord2f(1, .25f);
				glVertex3f(HALF, 0, HALF);
				glTexCoord2f(.5f, .25f);
				glVertex3f(-HALF, 0, HALF);
				glTexCoord2f(.5f, 0);
				glVertex3f(-HALF, HALF, HALF);
				glTexCoord2f(1, 0);
				glVertex3f(HALF, HALF, HALF);

				// setup back
				glTexCoord2f(1, .25f);
				glVertex3f(-HALF, 0, -HALF);
				glTexCoord2f(.5f, .25f);
				glVertex3f(HALF, 0, -HALF);
				glTexCoord2f(.5f, 0);
				glVertex3f(HALF, HALF, -HALF);
				glTexCoord2f(1, 0);
				glVertex3f(-HALF, HALF, -HALF);
				
				// setup left
				glTexCoord2f(1, .25f);
				glVertex3f(-HALF, 0, HALF);
				glTexCoord2f(.5f, .25f);
				glVertex3f(-HALF, 0, -HALF);
				glTexCoord2f(.5f, 0);
				glVertex3f(-HALF, HALF, -HALF);
				glTexCoord2f(1, 0);
				glVertex3f(-HALF, HALF, HALF);
				
				// setup right
				glTexCoord2f(1, .25f);
				glVertex3f(HALF, 0, -HALF);
				glTexCoord2f(.5f, .25f);
				glVertex3f(HALF, 0, HALF);
				glTexCoord2f(.5f, 0);
				glVertex3f(HALF, HALF, HALF);
				glTexCoord2f(1, 0);
				glVertex3f(HALF, HALF, -HALF);
			glEnd();
		glEndList();
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
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) movement.z -= 1;
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) movement.z += 1;
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) movement.x -= 1;
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) movement.x += 1;
		
		// If any keys pressed
		if (movement.x != 0 || movement.z != 0) {
			// Make the direction vector 1 in length
			movement.normalize();
			// Scale the direction by the delta and m/s to get a movement
			movement.mul(CityBuilder.getInstance().getDelta() * MOVEMENT_SCALAR);
			// rotate the movement to correct for the current camera rotation
			movement.rotate(-rotation.y, Vector3f.Y_AXIS);
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
			
			// Correct for the rotation caps for x rotation
			if (rotation.x < MIN_X_ROTATION) rotation.x = MIN_X_ROTATION;
			if (rotation.x > MAX_X_ROTATION) rotation.x = MAX_X_ROTATION;
		}
		
		// if a bounds is specified, check for bounds collision
		if (bounds != null) {
			// check for x-axis collision
			if (position.x < bounds.x) position.x = bounds.x;
			if (position.x > bounds.x + bounds.width) position.x = bounds.x + bounds.width;
			// check for z-axis collision
			if (position.z < bounds.y) position.z = bounds.x;
			if (position.z > bounds.y + bounds.height) position.z = bounds.y + bounds.height;
		}
		
		// get the scroll input
		float dScroll = Mouse.getDWheel();
		// if there is any input
		if (dScroll != 0) {
			// add the the scroll variable
			scroll += -dScroll * SCROLL_SCALAR;
			// limit the scroll variable
			if (scroll < 0) scroll = 0;
			if (scroll > 800) scroll = 800;
			distance = (float)Math.pow(1.007, scroll) + 2;
			System.out.println(scroll);
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
		glTranslatef(-position.x, -position.y, -position.z);
	}
	
	/**
	 * Renders a simple mesh at the position of the camera on the floor.
	 */
	public void renderDebugModel() {
		glPushMatrix();
			glTranslatef(position.x, position.y, position.z);
			glCallList(meshListId);
		glPopMatrix();
	}
	
	/**
	 * Set the bounds for the camera. The camera's position will be confined
	 * to these bounds. Set to null to disable the bounds.
	 * @param bounds Rectangle to specify the bounds, must be absolute world
	 * coordinates.
	 */
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
}