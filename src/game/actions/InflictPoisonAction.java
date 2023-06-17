package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.statuseffects.PoisonEffect;
import game.statuseffects.StatusEffectType;
import game.utils.RandomNumberGenerator;

/**
 * An Action to inflict a poison effect on the target
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class InflictPoisonAction extends Action {

    /**
     * The target to inflict the poison effect on
     */
    private Actor target;

    /**
     * The weapon used for attack before inflicting the poison effect
     */
    private Weapon weapon;

    /**
     * Constructor
     *
     * @param target The target to inflict the poison effect on
     * @param weapon The weapon used for attack before inflicting the poison effect
     */
    public InflictPoisonAction(Actor target, Weapon weapon) {
        this.target = target;
        this.weapon = weapon;
    }

    /**
     * When executed, an attack action would be first executed and then if the attack is successful
     * then the poison effect would be inflicted onto the target based on a certain probability
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the result of the InflictPoisonAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        result += new AttackAction(this.target, "", this.weapon).execute(actor, map);
        if (!result.contains("misses")) {
            if (RandomNumberGenerator.getRandomInt(0, 100) <= 60) {
                target.addCapability(StatusEffectType.POISONED);
                result += "\n" + target + " is inflicted with poison status.";
            }
        }
        return result;
    }

    /**
     * Describes which actor has poisoned the target with which weapon
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " poisons " + target + " with " + weapon;
    }
}
