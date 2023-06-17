package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * An Action that attacks a target and moves one step away
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class QuickstepAction extends Action {

    /**
     * the target to perform the action on
     */
    private Actor target;

    /**
     * the weapon used for the action
     */
    private Weapon weapon;

    /**
     * the direction to executed the action
     */
    private String direction;

    public QuickstepAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * When executed, an AttackAction would be first executed, then a location for the actor to move to is determined
     * then the actor would move the random location one step away from where it previously was
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        result += new AttackAction(this.target, this.direction, this.weapon).execute(actor, map);
        Location here = map.locationOf(actor);

        List<Location> locations = new ArrayList<>();

        for (Exit exit: here.getExits()) {
            Location destination = exit.getDestination();
            if(destination.canActorEnter(actor)) {
                locations.add(destination);
            }
        }

        if (!locations.isEmpty()) {
            int idx = RandomNumberGenerator.getRandomInt(0, locations.size()-1);
            Location destination = locations.get(idx);
            String direction = "to (" + destination.x() + ", " + destination.y() + ")";
            result += new MoveActorAction(locations.get(idx), "").execute(actor, map);
        }
        return result;
    }

    /**
     * Describes which actor attacks who
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " and moves away ";
    }
}
