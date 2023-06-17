package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.GodrickSoldier;
import game.utils.RandomNumberGenerator;

/**
 * A class that represent the Barrack in the game
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class Barrack extends Ground {
    /**
     * Constructor
     */
    public Barrack() {
        super('B');
    }

    /**
     * To add GodrickSoldier to specific location.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (RandomNumberGenerator.getRandomInt(0, 100) <= 45 && !location.containsAnActor()) {
            location.addActor(new GodrickSoldier());
        }
    }
}
