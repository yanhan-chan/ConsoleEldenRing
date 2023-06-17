package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.*;
import game.utils.Type;
import game.utils.RandomNumberGenerator;
import game.weapons.GiantDogHead;

/**
 * A class that represents the enemy GiantDog
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class GiantDog extends Enemy {

    /**
     * Constructor
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693);
        addBehaviour(999, new WanderBehaviour());
        addBehaviour(1, new AttackBehaviour());
        addWeaponToInventory(new GiantDogHead());
        addCapability(Type.GUST_OF_WIND_RESIDENT);
    }

    /**
     * Creates and returns an intrinsic weapon that slams a damage of 314 and hit rate of 90
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return (new IntrinsicWeapon(314, "slams", 90));
    }

    /**
     * this method returns the value of the rune between 313 and 1808
     * @return an int representing the value of the rune
     */
    @Override
    public int generateRune() {
        return RandomNumberGenerator.getRandomInt(313, 1808);
    }
}
