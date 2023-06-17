package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.utils.Type;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

/**
 * A Behaviour class that represents an AttackBehaviour
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class AttackBehaviour implements Behaviour {

    /**
     * This method returns the AttackAction if the current actor's surrounding does not have
     * the same type as the current actor
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        ActionList actionList = new ActionList();

        for (Exit exit: here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && !(destination.getActor().findCapabilitiesByType(Type.class).equals(actor.findCapabilitiesByType(Type.class)))) {
                if (!actor.getWeaponInventory().isEmpty()) {
                    WeaponItem weapon = actor.getWeaponInventory().get(0);
                    if (weapon.hasCapability(Status.HAS_SPECIAL_SKILL) && RandomNumberGenerator.getRandomInt(0, 100) <= 50) {
                        actionList.add(weapon.getSkill(destination.getActor(), exit.getName()));
                    } else if (weapon.getSkill(actor) != null) {
                        actionList.add(weapon.getSkill(actor));
                    } else {
                        actionList.add(new AttackAction(destination.getActor(), exit.getName(), weapon));
                    }
                } else {
                    actionList.add(new AttackAction(destination.getActor(), exit.getName()));
                }
            }
        }

        if (actionList.size() == 0) {
            return null;
        } else {
            int idx = RandomNumberGenerator.getRandomInt(0, actionList.size()-1);
            return actionList.get(idx);
        }
    }
}
