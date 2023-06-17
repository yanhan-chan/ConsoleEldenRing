package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.*;
import game.utils.Type;
import game.utils.RandomNumberGenerator;
import game.weapons.GiantCrabPincer;

/**
 * A class that represents the enemy GiantCrab
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class GiantCrab extends Enemy {

    /**
     * Constructor
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 407);
        addBehaviour(999, new WanderBehaviour());
        addBehaviour(1, new AttackBehaviour());
        addCapability(Type.PUDDLE_OF_WATER_RESIDENT);
        addWeaponToInventory(new GiantCrabPincer());
    }

    /**
     * Creates and returns an intrinsic weapon that slams a damage of 208 and hit rate of 90
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return (new IntrinsicWeapon(208, "slams", 90));
    }

    /**
     * this method returns the value of the rune between 318 and 4961
     * @return an int representing the value of the rune
     */
    @Override
    public int generateRune() {
        return RandomNumberGenerator.getRandomInt(318, 4961);
    }
}
