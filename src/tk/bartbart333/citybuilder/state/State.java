package tk.bartbart333.citybuilder.state;

import java.util.HashMap;

/**
 * This class manages the state of the game.
 * @author Barthold
 */
public abstract class State {
	
	private static States currentstate;
	private static HashMap<States, State> states = new HashMap<States, State>();
	
	/**
	 * This method gets called when the state of the game gets set to the current State.
	 * @author Barthold
	 */
	public abstract void init();
	
	/**
	 * This method gets called every frame to update the State.
	 * @author Barthold
	 */
	public abstract void update();
	
	/**
	 * This method gets called every frame to render the State.
	 * @author Barthold
	 */
	public abstract void render();
	
	/**
	 * Adds a State to a States.
	 * @author Barthold
	 * @param state States
	 * @param State State
	 */
	public static void addState(States state, State State){
		states.put(state, State);
	}
	
	/**
	 * Sets the currentstate.
	 * @author Barthold
	 * @param state States
	 * @throws NullPointerException
	 */
	public static void setState(States state){
		if(states.get(state) != null){
			currentstate = state;
			states.get(currentstate).init();
		}else{
			throw new NullPointerException();
		}
	}
	
	/**
	 * Gets the State.
	 * @author Barthold
	 * @param state States
	 * @return State
	 */
	public static State getState(States state){
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