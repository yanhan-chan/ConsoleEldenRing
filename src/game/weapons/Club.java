package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellAction;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 103 damage with 80% hit rate.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class Club extends WeaponItem implements Purchasable, Sellable {
    /**
     * The purchase price of Club
     */
    private final int purchasePrice = 600;
    /**
     * The sell price of Club
     */
    private final int sellPrice = 100;
    /**
     * The sell action of Club
     */
    private final Action sellAction = new SellAction(this, sellPrice);

    /**
     * Constructor
     */
    public Club() {
        super("Club", '!', 103, "bonks", 80);
    }

    /**
     * Inform a carried Club of the passage of time.
     *
     * This methods will check the surrounding to indicate the trader.
     * If trader locates surrounding add sellAction, else remove sellAction.
     * @param currentLocation The location of the actor carrying this Item
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
     * Return the purchase price of Club.
     *
     * @return The purchase price of Club
     */
    @Override
    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    /**
     * Return the sell price of Club.
     *
     * @return The sell price of Club
     */
    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }
}
