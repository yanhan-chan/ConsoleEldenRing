package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.reset.RespawnManager;

/**
 * An Action to where the player rests
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class RestAction extends Action {

    /**
     * The Ground the Actor rests
     */
    private Ground ground;
    private Location location;

    /**
     * The Ground that the Actor rests
     * @param ground
     */
    public RestAction(Ground ground, Location location) {
        this.ground = ground;
        this.location = location;
    }

    /**
     * When executed, a ResetAction would be executed
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String returnString = actor + " rested at " + ground;
        RespawnManager.getInstance().setLocation(this.location);
        ResetAction resetAction = new ResetAction();
        resetAction.execute(actor, map);
        return returnString;
    }

    /**
     * Describes which actor rests at which ground
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " rests at " + ground;
    }
}
