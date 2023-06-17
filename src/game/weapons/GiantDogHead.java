package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackSurroundingAction;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack the actor.
 * It deals 314 damage with 90% hit rate.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class GiantDogHead extends WeaponItem {

    /**
     * Constructor
     */
    public GiantDogHead() {
        super("Giant dog head", 'O', 314, "slams", 90);
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
