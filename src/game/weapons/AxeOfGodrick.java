package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellAction;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack actor.
 * It deals with 142 damages with 84% hit rate.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class AxeOfGodrick extends WeaponItem implements Sellable {
    /**
     * The sell price of AxeOfGodrick
     */
    public final int sellPrice = 100;
    /**
     * The sellAction of AxeOfGodrick
     */
    private final Action sellAction = new SellAction(this, sellPrice);

    /**
     * Constructor
     */
    public AxeOfGodrick() {
        super("Axe of Godrick", 'T', 142, "slash", 84);
    }

    /**
     * Inform a carried AxeOfGodrick of the passage of time.
     *
     * This methods will check the surrounding to indicate the trader.
     * If trader locates surrounding add sellAction, else remove sellAction.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        boolean hasTrader = false;

        for (Exit exit: currentLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.COMMERCE_WITH_PLAYER)) {
                hasTrader = true;
            }
        }

        if (hasTrader) {
            if (!getAllowableActions().contains(sellAction)){
                addAction(sellAction);
            }
        } else {
            removeAction(sellAction);
        }
    }

    /**
     * Return the sell price of AxeOfGodrick
     *
     * @return The sell price of AxeOfGodrick
     */
    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }
}
