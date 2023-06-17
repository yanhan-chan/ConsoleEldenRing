package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

/**
 * An Action to travel to another map
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class TravelAction extends Action {

    /**
     * The destination map
     */
    private GameMap enterMap;

    /**
     * The destination location of the destination map
     */
    private Location destination;

    /**
     * Constructor
     *
     * @param map The destination map
     * @param destination destination location of the destination map
     */
    public TravelAction(GameMap map, Location destination) {
        this.enterMap = map;
        this.destination = destination;
    }

    /**
     * When executed, the actor would be removed from the source map
     * then added to the destination map
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the result of the TravelAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        this.enterMap.addActor(actor, this.destination);
        return menuDescription(actor);
    }

    /**
     * Describes which actor has travelled to which map
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels to " + enterMap;
    }
}
