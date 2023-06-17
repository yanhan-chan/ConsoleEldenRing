package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.RuneManager;
import game.weapons.Sellable;

/**
 * An Action to Sell a WeaponItem
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class SellAction extends Action {

    /**
     * The WeaponItem to be sold
     */
    private WeaponItem weapon;

    /**
     * The item to be sold
     */
    private Item item;

    /**
     * The selling price of the WeaponItem
     */
    private int price;

    /**
     * RuneManager used for managing runes
     */
    private RuneManager runeManager;

    /**
     * Constructor
     *
     * @param weapon the weapon to sell
     * @param price the selling price of the weapon
     */
    public SellAction(WeaponItem weapon, int price) {
        this.weapon = weapon;
        this.price = price;
        this.runeManager = RuneManager.getInstance();
    }

    public SellAction(Item item, int price) {
        this.item = item;
        this.price = price;
        this.runeManager = RuneManager.getInstance();
    }

    /**
     * When executed, the weapon would be removed from the actor's inventory
     * the runes of the actor that sells  the weapon would be updated
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (this.weapon != null) {
            actor.removeWeaponFromInventory(weapon);
        } else {
            actor.removeItemFromInventory(item);
        }
        runeManager.setRuneValue(runeManager.getRuneValue() + price);
        return menuDescription(actor);
    }

    /**
     * Describes which actor sells what weapon for how many runes
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " sells " + (weapon != null ? weapon : item) + " for " + price + " runes.";
    }
}

