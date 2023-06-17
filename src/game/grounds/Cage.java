package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Dog;
import game.utils.RandomNumberGenerator;

/**
 * A class that represent the Cage in the game
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class Cage extends Ground {

    /**
     * Constructor
     */
    public Cage() {
        super('<');
    }

    /**
     * To add Dog in the specific location.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (RandomNumberGenerator.getRandomInt(0, 100) <= 37 && !location.containsAnActor()) {
            location.addActor(new Dog());
        }
    }
}
