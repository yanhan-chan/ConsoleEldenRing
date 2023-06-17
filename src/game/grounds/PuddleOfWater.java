package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.GiantCrab;
import game.actors.enemies.GiantCrayfish;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents the PuddleOfWater in the game
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class PuddleOfWater extends Ground {

    /**
     * Constructor
     */
    public PuddleOfWater() {
        super('~');
    }

    /**
     * This method enable the Graveyard to experience flow of time
     * This method spawns either a GiantCrayfish or GiantCrab depending on the location by first
     * ensuring that the current location has no actor
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        int middle = location.map().getXRange().max() / 2;
        if (location.x() > middle) {
            if (RandomNumberGenerator.getRandomInt(0, 100) <= 1 && !location.containsAnActor()) {
                location.addActor(new GiantCrayfish());
            }
        } else {
            if (RandomNumberGenerator.getRandomInt(0, 100) <= 2 && !location.containsAnActor()) {
                location.addActor(new GiantCrab());
            }
        }
    }
}

