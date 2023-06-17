package game.reset;

import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

/**
 * A reset manager class that manages a list of resettables.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class ResetManager {
    /**
     * A list of resettables
     */
    private List<Resettable> resettables;
    /**
     * An instance of ResetManager
     */
    private static ResetManager instance;

    private ResetManager() {
        this.resettables = new ArrayList<>();
    }

    /**
     * A factory method that allow a caller to obtain an instance that provide public constructors.
     *
     * @return ResetManager
     */
    public static ResetManager getInstance() {
        if (instance == null) {
            instance = new ResetManager();
        }
        return instance;
    }

    /**
     * Reset the whole GameMap.
     */
    public void run() {
        for (Resettable actor: resettables) {
            actor.reset();
        }
    }

    /**
     * Register the resettable.
     *
     * @param resettable The resettable object
     */
    public void registerResettable(Resettable resettable) {
        resettables.add(resettable);
    }

    /**
     * Remove the resettable.
     *
     * @param resettable The resettable object
     */
    public void removeResettable(Resettable resettable) {
        resettables.remove(resettable);
    }
}
