package game.actors.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.utils.RandomNumberGenerator;
import game.utils.Type;

/**
 * A class that represents the enemy Dog
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class Dog extends Enemy {

    /**
     * Constructor
     */
    public Dog() {
        super("Dog", 'a', 104);
        addBehaviour(999, new WanderBehaviour());
        addBehaviour(1, new AttackBehaviour());
        addCapability(Type.STORMVEIL_CASTLE_RESIDENT);
    }

    /**
     * Creates and returns an intrinsic weapon that bites a damage of 101 and hit rate of 93
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }

    /**
     * this method returns the value of the rune between 52 and 1390
     * @return an int representing the value of the rune
     */
    @Override
    public int generateRune() {
        return RandomNumberGenerator.getRandomInt(52, 1390);
    }
}
