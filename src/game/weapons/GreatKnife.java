package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.QuickstepAction;
import game.actions.SellAction;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack the enemy.
 * It deals 75 damage with 70% hit rate.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class GreatKnife extends WeaponItem implements Purchasable, Sellable {
    /**
     * The purchase price of GreatKnife
     */
    public final int purchasePrice = 3500;
    /**
     * The sell price of GreatKnife
     */
    public final int sellPrice = 350;
    /**
     * The sell action of GreatKnife
     */
    private final Action sellAction = new SellAction(this, sellPrice);

    /**
     * Constructor
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "stabs", 70);
        addCapability(Status.HAS_SPECIAL_SKILL);
    }

    /**
     * Inform a carried GreatKnife of the passage of time.
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
     * Return a QuickstepAction object.
     *
     * @param target The target actor
     * @param direction The direction of actor
     * @return A new QuickstepAction object
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new QuickstepAction(target, direction, this);
    }

    /**
     * Return the purchase price of GreatKnife
     *
     * @return The purchase price of GreatKnife
     */
    @Override
    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    /**
     * Return the sell price of GreatKnife
     *
     * @return The sell price of GreatKnife
     */
    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }
}
