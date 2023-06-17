package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackSurroundingAction;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack the actor.
 * It deals 208 damage with 90% hit rate
 *
 * Created by:
 * @auhtor Wai Jin, Jui Kai, Yanhan
 */
public class GiantCrabPincer extends WeaponItem {

    /**
     * Constructor
     */
    public GiantCrabPincer() {
        super("Giant crab pincer", 'x', 208, "slams", 90);
        addCapability(Status.ATTACK_SURROUNDING);
    }

    /**
     * Return new AttackSurroundingAction object.
     *
     * @param holder Weapon holder
     * @return A new AttackSurroundingAction object
     */
    @Override
    public Action getSkill(Actor holder) {
        return (new AttackSurroundingAction(this));
    }
}
