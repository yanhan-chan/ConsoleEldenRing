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
 * A simple weapon that can be used to attack the enemy.
 * It deals 115 damage with 85% hit rate.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class Grossmesser extends WeaponItem implements Sellable {

    /**
     * The sell price of Grossmesser
     */
    private final int sellPrice = 100;

    /**
     * The sell action of Grossmesser
     */
    private final Action sellAction = new SellAction(this, sellPrice);

    /**
     * Constructor
     */
    public Grossmesser() {
        super("Grossmesser", '?', 115, "slash", 85);
        addCapability(Status.ATTACK_SURROUNDING);
    }

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
    public Action getSkill(Actor holder) {
        return (new AttackSurroundingAction(this));
    }

    /**
     * Return the sell price of Grossmesser.
     *
     * @return The sell price of Grossmesser
     */
    @Override
    public int getSellPrice() {
        return this.sellPrice;
    }
}
