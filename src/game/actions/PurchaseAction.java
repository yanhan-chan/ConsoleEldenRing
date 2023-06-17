package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.RuneManager;
import game.weapons.Purchasable;

/**
 * An Action to purchase a Purchasable
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class PurchaseAction extends Action {

    /**
     * the Purchasable to be purchased
     */
    private Purchasable weapon;

    /**
     * RuneManager used for managing runes
     */
    private RuneManager runeManager;

    /**
     * Constructor
     *
     * @param weapon the Purchasable to be purchased
     */
    public PurchaseAction(Purchasable weapon) {
        this.weapon = weapon;
        this.runeManager = RuneManager.getInstance();
    }

    /**
     * When executed, if the price of the Purchasable is affordable, the Purchasable would be added to the actor's inventory
     * otherwise, it would not be added to the actor's inventory
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String outStr = "";

        if (runeManager.getRuneValue() >= weapon.getPurchasePrice()) {
            actor.addWeaponToInventory((WeaponItem) weapon);
            runeManager.setRuneValue(runeManager.getRuneValue() - weapon.getPurchasePrice());
            outStr = actor + " purchases " + weapon + " for " + weapon.getPurchasePrice();
        } else {
            outStr = actor + " does not have enough runes to purchase " + weapon;
        }

        return outStr;
    }

    /**
     * Describes which actor purchases which Purchasable for what price
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor  + " purchases " + weapon + " for " + weapon.getPurchasePrice();
    }
}
