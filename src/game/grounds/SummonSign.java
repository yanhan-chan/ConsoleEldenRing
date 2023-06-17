package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.SummonAction;

/**
 * A class that represent the SummonSign in the game
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class SummonSign extends Ground {

    /**
     * Constructor
     */
    public SummonSign() {
        super('=');
    }

    /**
     * This method returns a list of allowable actions that can be performed on the current ground
     * It adds a SummonAction to the list of allowable actions
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a list of allowable actions that can be performed on the current ground
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        actions.add(new SummonAction(location));
        return actions;
    }
}
