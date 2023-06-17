package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.RestAction;
import game.reset.RespawnManager;

/**
 * A class that represents the SiteOfLostGrace in the game
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class SiteOfLostGrace extends Ground {

    /**
     * Constructor
     */
    public SiteOfLostGrace() {
        super('U');
    }

    /**
     * This method returns a list of allowable actions that can be performed on the current ground
     * It adds a RestAction to the list of allowable actions
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return A list of allowable actions that can be performed on the current ground
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        actions.add(new RestAction(this, location));
        return actions;
    }

    /**
     * This method returns the String representation of the ground
     * @return  String representation of the ground
     */
    @Override
    public String toString() {
        return "The First Step Lost of Site Grace";
    }
}
