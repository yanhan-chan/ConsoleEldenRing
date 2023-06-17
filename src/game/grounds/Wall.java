package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents the Wall in the game.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class Wall extends Ground {
	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
	}

	/**
	 * To indicate the actor allow to enter the ground or not.
	 *
	 * @param actor The Actor to check
	 * @return False, since the Wall is not allow to enter by any actor
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}

	/**
	 * To indicate the actor not able to move forwards.
	 *
	 * @return True, since the Wall is a blocked object
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
