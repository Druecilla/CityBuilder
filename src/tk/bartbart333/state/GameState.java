package tk.bartbart333.state;

/**
 * @author Barthold
 * The extendable GameState holds the abstract methods every GameState should overwrite
 */
public abstract class GameState {
	
	public abstract void init();
	
	public abstract void update();
	
	public abstract void render();
}