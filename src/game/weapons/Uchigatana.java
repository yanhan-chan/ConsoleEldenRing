package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.SellAction;
import game.actions.UnsheatheAction;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack actor.
 * It deals with 115 damages with 80% hit rate.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class Uchigatana extends WeaponItem implements Purchasable, Sellable {
    /**
     * The purchase price of Uchigatana
     */
    private final int purchasePrice = 5000;
    /**
     * The sell price of Uchigatana
     */
    private final int sellPrice = 500;
    /**
     * The sellAction of Uchigatana
     */
    private final Action sellAction = new SellAction(this, sellPrice);

    /**
     * Constructor
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "slashes", 80);
        addCapability(Status.HAS_SPECIAL_SKILL);
    }

    /**
     * Inform a carried Uchigatana of the passage of time.
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
     * Return UnsheatheAction object.
     *
     * @param target Target actor
     * @param direction The direction of actor
     * @return A new UnsheatheAction object
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new UnsheatheAction(target, this);
    }

    /**
     * Return the purchase price of Uchigatana.
     *
     * @return The purchase price of Uchigatana
     */
    @Override
    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    /**
     * Return the sell price of Uchigatana.
     *
     * @return The sell price of Uchigatana
     */
    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }
}
