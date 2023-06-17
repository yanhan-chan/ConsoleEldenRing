package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackSurroundingAction;
import game.actions.SellAction;
import game.utils.Status;

/**
 * A simple weapon that can be used to attack the actor.
 * It deals 118 damage with 88% hit rate.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */

public class Scimitar extends WeaponItem implements Purchasable, Sellable {
    /**
     * The purchase price of Scimitar
     */
    private final int purchasePrice = 600;
    /**
     * The sell price of Scimitar
     */
    private final int sellPrice = 100;
    /**
     * The sellAction of Scimitar
     */
    private final Action sellAction = new SellAction(this, sellPrice);

    /**
     * Constructor
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "slash", 88);
        addCapability(Status.ATTACK_SURROUNDING);
    }

    /**
     * Informed a carried Scimitar of the passage of Scimitar.
     *
     * This methods will check the surrounding to indicate the trader.
     * If the trader locates surrounding, add sellAction, else remove sellAction.
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
     * Return AttackSurroundingAction object.
     *
     * @param holder Weapon holder
     * @return A new AttackSurroundingAction object
     */
    @Override
    public Action getSkill(Actor holder) {
        return new AttackSurroundingAction(this);
    }

    /**
     * Return the purchase price of Scimitar.
     *
     * @return The purchase price of Scimitar
     */
    @Override
    public int getPurchasePrice() {
        return this.purchasePrice;
    }

    /**
     * Return the sell price of Scimitar.
     *
     * @return The sell price of Scimitar
     */
    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }
}
