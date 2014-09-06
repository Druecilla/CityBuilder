package tk.bartbart333.state;

/**
 * @author Barthold
 * The extendable GameState holds the abstract methods every GameState should overwrite
 */
public abstract class GameState {
	
	/**
	 * @author Barthold
	 * This method gets called when the state of the game gets set to the current GameState
	 */
	public abstract void init();
	
	/**
	 * @author Barthold
	 * This method gets called every frame to update the GameState
	 */
	public abstract void update();
	
	/**
	 * @author Barthold
	 * This method gets called every frame to render the GameState
	 */
	public abstract void render();
}