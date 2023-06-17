package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ExchangeAction;
import game.actions.SellAction;
import game.utils.Status;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;
import game.weapons.Sellable;

/**
 * RemembranceOfGrafted is an item, which can be used to exchange the weapon or 200000 Rune.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class RemembranceOfGrafted extends Item implements Sellable {

    /**
     * The sell price of RemembranceOfGrafted
     */
    public final int sellPrice = 20000;
    /**
     * The sellAction of RemembranceOfGrafted
     */
    private final Action sellAction = new SellAction(this, sellPrice);
    /**
     * The exchangeAction for AxeOfGodrick
     */
    private final Action exchangeAxeOfGodrickAction = new ExchangeAction(this, new AxeOfGodrick());
    /**
     * The exchangeAction for GraftedDragon
     */
    private final Action exchangeGraftedDragonAction = new ExchangeAction(this, new GraftedDragon());

    /**
     * Constructor
     */
    public RemembranceOfGrafted() {
        super("Remembrance of the Grafted", 'O', true);
    }

    /**
     * Inform a carried RemembranceOfGrafted of the passage of time.
     *
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        boolean hasTrader = false;

        for (Exit exit: currentLocation.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.ACCEPT_REMEMBRANCES)) {
                hasTrader = true;
            }
        }

        if (hasTrader) {
            if (!getAllowableActions().contains(sellAction)) {
                addAction(sellAction);
                addAction(exchangeAxeOfGodrickAction);
                addAction(exchangeGraftedDragonAction);
            }
        } else {
            removeAction(sellAction);
            removeAction(exchangeAxeOfGodrickAction);
            removeAction(exchangeGraftedDragonAction);
        }
    }

    /**
     * Return the sell price of RemembranceOfGrafted
     *
     * @return The sell price of RemembranceOfGrafted
     */
    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }
}
