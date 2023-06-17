package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackSurroundingAction;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack the actor.
 * It deals 527 damage with 100% hit rate.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class GiantCrayFishPincer extends WeaponItem {

    /**
     * Constructor
     */
    public GiantCrayFishPincer() {
        super("Giant cray fish pincer", 'x', 527, "slams", 100);
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
        return (new AttackSurroundingAction());
    }
}
