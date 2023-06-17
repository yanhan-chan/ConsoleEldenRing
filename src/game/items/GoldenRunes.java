package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.reset.Resettable;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * GoldenRunes is an item that can be consumed by actor.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class GoldenRunes extends Item implements Consumable {

    /**
     * Consume Action for Golden Runes
     */
    private final Action consumeAction = new ConsumeAction(this);

    /**
     * Constructor
     */
    public GoldenRunes() {
        super("Golden Rune", '*', true);
    }

    /**
     * Randomly increasing the player's rune between 200 and 10000.
     *
     * @param actor The actor
     * @return The string represent actor has successfully consumed it
     */
    @Override
    public String consumeEffect(Actor actor) {
        int generateRune = RandomNumberGenerator.getRandomInt(200, 10000);
        RuneManager runeManager = RuneManager.getInstance();
        runeManager.setRuneValue(runeManager.getRuneValue() + generateRune);
        actor.removeItemFromInventory(this);
        return actor + " consumed " + this;
    }

    /**
     * Checks if the Golden Runes is in the Player's inventory, if it is provide a Consume Action
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (actor.getItemInventory().contains(this) && !getAllowableActions().contains(consumeAction)) {
            addAction(consumeAction);
        }
    }
}
