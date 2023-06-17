package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.utils.Type;
import game.utils.RandomNumberGenerator;
import game.behaviours.WanderBehaviour;

/**
 * A class that represents the enemy LoneWolf
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class LoneWolf extends Enemy {

    /**
     * Constructor
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102);
        addBehaviour(999, new WanderBehaviour());
        addBehaviour(1, new AttackBehaviour());
        addCapability(Type.GUST_OF_WIND_RESIDENT);
    }

    /**
     * Creates and returns an intrinsic weapon that bites a damage of 97 and hit rate of 95
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }

    /**
     * this method returns the value of the rune between 55 and 1470
     * @return an int representing the value of the rune
     */
    @Override
    public int generateRune() {
        return RandomNumberGenerator.getRandomInt(55, 1470);
    }
}
