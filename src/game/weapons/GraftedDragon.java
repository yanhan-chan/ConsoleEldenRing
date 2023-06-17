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
 * It deals with 89 damages with 90% hit rate.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class GraftedDragon extends WeaponItem implements Sellable {
    /**
     * The sell price of GraftedDragon
     */
    public final int sellPrice = 200;
    /**
     * The sellAction of GraftedDragon
     */
    private final Action sellAction = new SellAction(this, sellPrice);

    /**
     * Constructor
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "slash", 90);
    }

    /**
     * Inform a carried GraftedDragon of the passage of time.
     *
     * This method will check the surrounding to indicate the trader.
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
     * Return the sell price of GraftedDragon
     *
     * @return The sell price of GraftedDragon
     */
    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }
}
