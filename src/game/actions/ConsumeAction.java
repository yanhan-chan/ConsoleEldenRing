package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * An Action to consume a consumable item
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class ConsumeAction extends Action {

    /**
     * The Consumable to be consumed
     */
    private Consumable consumable;

    /**
     * Constructor
     *
     * @param consumable the Consumable to be consumed
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * When executed the consumable would be consumed by the actor
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the result of the ConsumeAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return consumable.consumeEffect(actor);
    }

    /**
     * Describes which actor has consumed the consumable
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " consumes " + consumable;
    }
}
