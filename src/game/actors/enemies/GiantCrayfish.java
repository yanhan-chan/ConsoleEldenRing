package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.*;
import game.utils.Type;
import game.utils.RandomNumberGenerator;
import game.weapons.GiantCrayFishPincer;

/**
 * A class that represents the enemy GiantCrayfish
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class GiantCrayfish extends Enemy {

    /**
     * Constructor
     */
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803);
        addBehaviour(999, new WanderBehaviour());
        addBehaviour(1, new AttackBehaviour());
        addCapability(Type.PUDDLE_OF_WATER_RESIDENT);
        addWeaponToInventory(new GiantCrayFishPincer());
    }

    /**
     * Creates and returns an intrinsic weapon that slams a damage of 527 and hit rate of 100
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(527, "slams", 100);
    }

    /**
     * this method returns the value of the rune between 500 and 2374
     * @return an int representing the value of the rune
     */
    @Override
    public int generateRune() {
        return RandomNumberGenerator.getRandomInt(500, 2374);
    }
}
