package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.Rune;

/**
 * An Action to drop rune
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class DropRuneAction extends DropAction {

    /**
     * The Rune to be dropped
     */
    private final Rune item;

    /**
     * the Location of the Rune dropped
     */
    private Location lastLocation;

    public DropRuneAction(Rune item, Location lastLocation) {
        super(item);
        this.item = item;
        this.lastLocation = lastLocation;
    }

    /**
     * When executed, the Rune would be removed from the actor's inventory
     * the rune would be dropped at the location of the actor
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(item);
        item.setDroppedLocation(lastLocation);
        lastLocation.addItem(item);
        return menuDescription(actor);
    }

    /**
     * Describes which actor drops the rune
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " drops " + item + " (value: " + item.getValue() + ").";
    }
}
