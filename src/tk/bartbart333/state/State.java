package tk.bartbart333.state;

import java.util.HashMap;

/**
 * This class manages the state of the game.
 * @author Barthold
 */
public class State {
	
	private static States currentstate;
	private static HashMap<States, GameState> states = new HashMap<States, GameState>();
	
	/**
	 * Adds a Gamestate to a States.
	 * @author Barthold
	 * @param state States
	 * @param gamestate GameState
	 */
	public static void addState(States state, GameState gamestate){
		states.put(state, gamestate);
	}
	
	/**
	 * Sets the currentstate.
	 * @author Barthold
	 * @param state States
	 * @throws NullPointerException
	 */
	public static void setState(States state){
		if(states.get(currentstate) != null){
			states.get(currentstate).init();
			currentstate = state;
		}else{
			throw new NullPointerException();
		}
	}
	
	/**
	 * Gets the GameState.
	 * @author Barthold
	 * @param state States
	 * @return GameState
	 */
	public static GameState getState(States state){
		return states.get(state);
	}
	
	/**
	 * Gets the CurrentState.
	 * @author Barthold
	 * @return States
	 */
	public static States getCurrentState(){
		return currentstate;
	}
	
	/**
	 * Calls the currentstate to update and render.
	 * @author Barthold
	 * @throws NullPointerException
	 */
	public static void call(){
		if(states.get(currentstate) != null){
			states.get(currentstate).update();
			states.get(currentstate).render();
		}else{
			throw new NullPointerException();
		}
	}
}