package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackSurroundingAction;
import game.utils.Type;

/**
 * A Behaviour class that represents an AttackSurroundingBehaviour
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class AttackSurroundingBehaviour implements Behaviour {

    /**
     * returns a AttackSurroundingAction if there is an actor in the exits of the current actor
     * that is not the same type og the actor
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);

        for (Exit exit: here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && !(destination.getActor().findCapabilitiesByType(Type.class).equals(actor.findCapabilitiesByType(Type.class)))) {
                return (new AttackSurroundingAction());
            }
        }

        return null;
    }
}
