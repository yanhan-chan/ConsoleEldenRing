package game.actors.traders;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SellAction;
import game.utils.Status;

/**
 * A class that represent the FingerReaderEnia.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class FingerReaderEnia extends Actor {

    /**
     * Constructor
     */
    public FingerReaderEnia() {
        super("Finger Reader Enia", 'E', 69420);
        addCapability(Status.COMMERCE_WITH_PLAYER);
        addCapability(Status.ACCEPT_REMEMBRANCES);
    }

    /**
     * Return DoNothingAction
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return A new DoNothingAction object
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Return a list of actions.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        return actions;
    }
}
