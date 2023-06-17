package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.HeavySkeletalSwordsman;
import game.actors.enemies.SkeletalBandit;
import game.utils.RandomNumberGenerator;

/**
 * A class that represents the Graveyard in the game
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class Graveyard extends Ground {

    /**
     * Constructor
     */
    public Graveyard() {
        super('n');
    }

    /**
     * This method enable the Graveyard to experience flow of time
     * This method spawns either a HeavySkeletalSwordsman or SkeletalBandit depending on the location by first
     * ensuring that the current location has no actor
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (RandomNumberGenerator.getRandomInt(0, 100) <= 27 && !location.containsAnActor()) {
            int middle = location.map().getXRange().max() / 2;
            if (location.x() > middle) {
                location.addActor(new SkeletalBandit());
            } else {
                location.addActor(new HeavySkeletalSwordsman());
            }
        }
    }
}

