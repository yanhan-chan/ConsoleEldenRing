package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Rune;
import game.items.RuneManager;

/**
 * An Action to pick up rune
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class PickUpRuneAction extends PickUpAction {

    /**
     * The Rune to be dropped
     */
    private final Rune item;

    /**
     * RuneManager used for managing runes
     */
    private final RuneManager runeManager = RuneManager.getInstance();

    /**
     * Constructor
     *
     * @param item the rune to be picked up
     */
    public PickUpRuneAction(Rune item) {
        super(item);
        this.item = item;
    }

    /**
     * When executed the rune would be picked up
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        runeManager.setRuneValue(runeManager.getRuneValue() + item.getValue());
        return super.execute(actor, map);
    }

    /**
     * Describes which actor picks up the rune
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " retrieves " + item + " (value: " + item.getValue() + ")";
    }
}
