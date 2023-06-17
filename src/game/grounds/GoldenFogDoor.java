package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TravelAction;
import game.utils.Status;

/**
 * A class represent the GoldenFogDoor in the game
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class GoldenFogDoor extends Ground {

    /**
     * The GameMap of the GoldenFogDoor
     */
    private GameMap enterMap;
    /**
     * The Location of the GoldenFogDoor
     */
    private Location destination;

    /**
     * Constructor
     *
     * @param map The map of the GoldenFogDoor
     * @param destination The destination of GoldenFogDoor
     */
    public GoldenFogDoor(GameMap map, Location destination) {
        super('D');
        this.enterMap = map;
        this.destination = destination;
    }

    /**
     * This method returns a list of allowable actions that can be performed on the current ground
     * It adds a TravelAction to the list of allowable actions
     *
     * @param actor The Actor acting
     * @param location The current Location
     * @param direction The direction of the Ground from the Actor
     * @return A list of allowable actions that can be performed on the current ground
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        actions.add(new TravelAction(enterMap, destination));
        return actions;
    }

    /**
     * This method returns the String representation of the ground
     *
     * @param actor the Actor to check
     * @return The string representation of the ground
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }
}
