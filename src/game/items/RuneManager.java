package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DropRuneAction;
import game.actors.Player;

/**
 * A RuneManager is a class that manages the Rune.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class RuneManager {
    /**
     * A Rune object
     */
    private Rune rune;
    /**
     * An instance of RuneManager
     */
    private static RuneManager instance;
    /**
     * A holder of Player
     */
    private Player holder;
    /**
     * A droppedRune to indicate the Rune to be dropped
     */
    private Rune droppedRune;

    private RuneManager() {}

    /**
     * A factory method that allow a caller to obtain an instance that provide public constructors.
     *
     * @return RuneManager
     */
    public static RuneManager getInstance() {
        if (instance == null) {
            instance = new RuneManager();
        }
        return instance;
    }

    /**
     * Set the Rune.
     *
     * This methods will add the Rune into the Actor's inventory.
     * @param actor The Actor object
     * @param rune The Rune object
     */
    public void setRune(Player actor, Rune rune) {
        actor.addItemToInventory(rune);
        this.rune = rune;
        this.holder = actor;
    }

    /**
     * Get the value of Rune
     *
     * This methods will return the value of Rune.
     * @return An integer that represent the Rune.
     */
    public int getRuneValue() {
        return this.rune.getValue();
    }

    /**
     * Set the value of Rune.
     *
     * This methods will set the value of Rune.
     * @param value An integer that represent the value of Rune
     */
    public void setRuneValue(int value) {
        this.rune.setValue(value);
    }

    /**
     * Return the DropAction.
     *
     * This method will indicate the droppedRune is null or not.
     * If not null update current dropped location, else use current dropped location.
     * @return A DropRuneAction object
     */
    public DropAction getDropRuneAction() {
        if (this.droppedRune != null) {
            Location here = this.droppedRune.getDroppedLocation();
            here.removeItem(this.droppedRune);
        }
        this.droppedRune = this.rune;
        return new DropRuneAction(this.rune, holder.getLastLocation());
    }

    /**
     * Reset the Rune.
     */
    public void resetRune() {
        setRune(holder, new Rune());
    }
}
