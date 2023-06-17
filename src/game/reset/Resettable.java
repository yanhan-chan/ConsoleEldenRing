package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A resettable interface
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public interface Resettable {
    /**
     * Reset the GameMap.
     */
    void reset();

    /**
     * Register this resettable.
     */
    default void addToResetManager() {
        ResetManager.getInstance().registerResettable(this);
    }
}
