package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.GiantDog;
import game.actors.enemies.LoneWolf;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents the GustOfWind in the game
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class GustOfWind extends Ground {

    /**
     * Constructor
     */
    public GustOfWind() {
        super('&');
    }

    /**
     * This method enable the Graveyard to experience flow of time
     * This method spawns either a GiantDog or LoneWolf depending on the location by first
     * ensuring that the current location has no actor
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        int middle = location.map().getXRange().max() / 2;
        if (location.x() > middle) {
            if (RandomNumberGenerator.getRandomInt(0, 100) <= 4 && !location.containsAnActor()) {
                location.addActor(new GiantDog());
            }
        } else {
            if (RandomNumberGenerator.getRandomInt(0, 100) <= 33 && !location.containsAnActor()) {
                location.addActor(new LoneWolf());
            }
        }
    }
}

