package game.reset;

import edu.monash.fit2099.engine.positions.Location;

/**
 * RespawnManager used to respawn all enemies from the grounds.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */

public class RespawnManager {
    /**
     * The location in GameMap
     */
    private Location location;
    /**
     * An instance of RespawnManager
     */
    private static RespawnManager instance;

    private RespawnManager() {
    }

    /**
     * A factory method that allow a caller to obtain an instance that provide public constructors.
     *
     * @return RespawnManager
     */
    public static RespawnManager getInstance() {
        if (instance == null) {
            instance = new RespawnManager();
        }
        return instance;
    }

    /**
     * Return current location.
     *
     * @return The location
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Set current location.
     *
     * @param location The location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

}
