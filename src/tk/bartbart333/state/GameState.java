package tk.bartbart333.state;

/**
 * The extendable GameState holds the abstract methods every GameState should overwrite.
 * @author Barthold
 */
public abstract class GameState {
	
	/**
	 * This method gets called when the state of the game gets set to the current GameState.
	 * @author Barthold
	 */
	public abstract void init();
	
	/**
	 * This method gets called every frame to update the GameState.
	 * @author Barthold
	 */
	public abstract void update();
	
	/**
	 * This method gets called every frame to render the GameState.
	 * @author Barthold
	 */
	public abstract void render();
}