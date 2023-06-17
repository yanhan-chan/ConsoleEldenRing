package game.actors.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.PurchaseAction;
import game.items.RuneManager;
import game.utils.Status;
import game.weapons.Club;
import game.weapons.GreatKnife;
import game.weapons.Scimitar;
import game.weapons.Uchigatana;

/**
 * A class that represents the Trader
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class MerchantKale extends Actor {

    /**
     * RuneManager used for managing runes
     */
    private RuneManager runeManager;

    /**
     * Constructor
     */
    public MerchantKale() {
        super("Merchant Kale", 'K', 69420);
        addCapability(Status.COMMERCE_WITH_PLAYER);
        this.runeManager = RuneManager.getInstance();
    }

    /**
     * returns a DoNothingAction
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Add Actions to the list of allowable actions that can be performed on the Trader
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return the list of actions that can be performed on the Trader
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        actions.add(new PurchaseAction(new Uchigatana()));
        actions.add(new PurchaseAction(new GreatKnife()));
        actions.add(new PurchaseAction(new Club()));
        actions.add(new PurchaseAction(new Scimitar()));

        return actions;
    }
}
