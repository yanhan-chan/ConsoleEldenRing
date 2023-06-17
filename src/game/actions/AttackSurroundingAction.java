package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.Status;

/**
 * An Action to attack surrounding Actors.
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Adrian Kristanto
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class AttackSurroundingAction extends Action {

    /**
     * The Weapon used to attack
     */
    private Weapon weapon;

    /**
     * Constructor
     */
    public AttackSurroundingAction() {}

    /**
     * Constructor.
     *
     * @param weapon the Weapon used to attack
     */
    public AttackSurroundingAction(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * When executed, if there exists an actor in the current exits of the actor,
     * an AttackAction would be executed.
     *
     * @param actor The actor performing the attack surrounding action.
     * @param map The map the actor is on.
     * @return the reuslt of the attack, e.g. whether the target is killed, etc
     * @see AttackAction
     */
    public String execute(Actor actor, GameMap map) {
        String result = "";
        Location here = map.locationOf(actor);
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }

        result += actor + " attacks their surrounding!\n";
        for (Exit exit: here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                result += new AttackAction(target, exit.getName(), weapon).execute(actor, map);
            }
        }

        return result;
    }

    /**
     * Describes which target the actor is attacking with which weapon
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks anything in the surrounding with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }
}
