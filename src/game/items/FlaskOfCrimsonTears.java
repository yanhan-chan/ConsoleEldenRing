package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;
import game.reset.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * FlaskOfCrimsonTears is an item can deals a significant amount of damage.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */

public class FlaskOfCrimsonTears extends Item implements Consumable, Resettable {
    /**
     * Use to indicate current usage of FlaskOfCrimsonTears
     */
    private int usage;
    /**
     * Player's restore hit point after using FlaskOfCrimsonTears
     */
    private final int restoreAmount = 250;
    /**
     * Max usage of FlaskOfCrimsonTears
     */
    private static final int MAX_USAGE = 2;
    /**
     * An instance of FlaskOfCrimsonTears
     */
    private static FlaskOfCrimsonTears instance;

    /**
     * Constructor
     */
    private FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", '0', false);
        this.usage = MAX_USAGE;
        this.addToResetManager();
    }

    /**
     * A factory method that allow a caller to obtain an instance that provide public constructors.
     *
     * @return FlaskOfCrimsonTears
     */
    public static FlaskOfCrimsonTears getInstance() {
        if (instance == null) {
            return (new FlaskOfCrimsonTears());
        }
        return instance;
    }

    /**
     * Return the usage of FlaskOfCrimsonTears.
     *
     * @return The usage of FlaskOfCrimsonTears
     */
    public int getUsage() {
        return usage;
    }

    /**
     * Return the restore amount of FlaskOfCrimsonTears.
     *
     * @return The restore amount of FlaskOfCrimsonTears
     */
    public int getRestoreAmount() {
        return restoreAmount;
    }

    /**
     * Return a list of allowable actions.
     *
     * @return A list of action
     */
    @Override
    public List<Action> getAllowableActions() {
        List<Action> actions = new ArrayList<>();
        actions.add(new ConsumeAction(this));
        return actions;
    }

    /**
     * Consume some item to heal player's hit point.
     *
     * @param actor The actor
     * @return A message to indicate the actor consume it nor the item is not available
     */
    @Override
    public String consumeEffect(Actor actor) {
        if (usage > 0) {
            actor.heal(restoreAmount);
            usage--;
            return actor + " consumed " + this;
        } else {
            return this + " is empty.";
        }
    }

    /**
     * Reset the usage of FlaskOfCrimsonTears.
     */
    @Override
    public void reset() {
        this.usage = MAX_USAGE;
    }

    /**
     * Return a string that represent the FlaskOfCrimsonTears
     *
     * @return A string that represent the FlaskOfCrimsonTears
     */
    @Override
    public String toString() {
        String retStr = super.toString() + " ";
        retStr += "(" + usage + "/" + MAX_USAGE + ")";
        return retStr;
    }
}
