package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.RandomNumberGenerator;

/**
 * An Action that attacks a target for 2X damage of the weapon
 *
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class UnsheatheAction extends Action {

    /**
     * the target to be attacked
     */
    private Actor target;

    /**
     * the weapon used for the attack
     */
    private Weapon weapon;

    /**
     * the chance of success of the attack
     */
    private final int chanceToHit = 60;

    /**
     * Constructor
     *
     * @param target the target to be attacked
     * @param weapon the weapon used for the attack
     */
    public UnsheatheAction(Actor target, Weapon weapon) {
        this.target = target;
        this.weapon = weapon;
    }

    /**
     * When executed, the chance to hit of the weapon that the Actor used is computed to determine whether
     * the actor will hit the target. If so, deal damage to the target and determine whether the target is killed.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (!(RandomNumberGenerator.getRandomInt(0, 100) <= chanceToHit)) {
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage()*2;
        String result = actor + " unsheathes " + weapon + " on " + target + "\n";
        result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);
        if (!target.isConscious()) {
            result += new DeathAction(actor).execute(target, map);
        }

        return result;
    }

    /**
     * Describes which actor performs the action using what weapon on who
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unsheathes " + weapon + " on " + target;
    }
}
