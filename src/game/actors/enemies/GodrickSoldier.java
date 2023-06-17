package game.actors.enemies;

import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.utils.RandomNumberGenerator;
import game.utils.Type;
import game.weapons.GreatKnife;

/**
 * A class that represents the enemy GodrickSoldier
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class GodrickSoldier extends Enemy {

    /**
     * Constructor
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198);
        addBehaviour(999, new WanderBehaviour());
        addBehaviour(1, new AttackBehaviour());
        addWeaponToInventory(new GreatKnife());
        addCapability(Type.STORMVEIL_CASTLE_RESIDENT);
    }

    /**
     * this method returns the value of the rune between 38 and 70
     * @return an int representing the value of the rune
     */
    @Override
    public int generateRune() {
        return RandomNumberGenerator.getRandomInt(38, 70);
    }
}
