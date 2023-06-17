package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.PickUpRuneAction;
import game.reset.Resettable;

/**
 * Rune is the currency of the game.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class Rune extends Item implements Resettable {
    /**
     * The value to indicate the Rune
     */
    private int value;
    /**
     * The location that Rune dropped
     */
    private Location droppedLocation;

    /**
     * Constructor
     */
    public Rune() {
        super("Runes", '$', true);
        this.value = 0;
        addToResetManager();
    }

    /**
     * Return the value of Rune.
     *
     * @return The value of Rune
     */
    public int getValue() {
        return value;
    }

    /**
     * Set the value of Rune.
     *
     * @param value The value of Rune
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Return the location that Rune dropped.
     *
     * @return The location that Rune dropped
     */
    public Location getDroppedLocation() {
        return this.droppedLocation;
    }

    /**
     * Set the location of Rune dropped.
     *
     * @param location The location of Rune dropped
     */
    public void setDroppedLocation(Location location) {
        this.droppedLocation = location;
    }

    /**
     * Return an action allow the player to pick up the Rune.
     *
     * @param actor The actor
     * @return PickUpRuneAction object if the item is portable, else null.
     */
    @Override
    public PickUpAction getPickUpAction(Actor actor) {
        if (portable) {
            return new PickUpRuneAction(this);
        }
        return null;
    }

    /**
     * Return an action that allow the actor to drop the Rune.
     *
     * @param actor The Rune
     * @return null
     */
    @Override
    public DropAction getDropAction(Actor actor) {
        return null;
    }

    @Override
    public void reset() {
    }
}
