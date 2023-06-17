package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.InflictPoisonAction;
import game.actions.SellAction;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack actor.
 * It deals with 120 damages with 88% hit rate.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class SerpentboneBlade extends WeaponItem implements Sellable {
    /**
     * The sell price of SerpentboneBlade
     */
    private final int sellPrice = 200;
    /**
     * The sellAction of SerpentboneBlade
     */
    private final Action sellAction = new SellAction(this, sellPrice);

    /**
     * Constructor
     */
    public SerpentboneBlade() {
        super("Serpentbone Blade", 't', 120, "slashes", 88);
        addCapability(Status.HAS_SPECIAL_SKILL);
    }

    /**
     * Inform a carried SerpentboneBlade of the passage of time.
     *
     * This methods will check the surrounding to indicate the trader.
     * If trader locates surrounding add sellAction, else remove sellAction.
     * @param currentLocation The location of the actor carrying this Item.
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
     * Return InflictPoisonAction object.
     *
     * @param target Target actor
     * @param direction The direction of actor
     * @return A new InflictPoisonAction object
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new InflictPoisonAction(target, this);
    }

    /**
     * Return the sell price of SerpentboneBlade
     *
     * @return The sell price of SerpentboneBlade
     */
    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }
}
