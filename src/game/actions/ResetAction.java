package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.items.RuneManager;
import game.reset.ResetManager;
import game.reset.RespawnManager;
import game.utils.FancyMessage;

/**
 * An Action to reset the game
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class ResetAction extends Action {

    private Location lastLocation;

    /**
     * Constructor
     */
    public ResetAction() {}

    /**
     * Constructor
     *
     * @param location the location
     */
//    public ResetAction(Location location) {
//        this.lastLocation = location;
//    }

    /**
     * When executed, if the actor is not conscious, it would be removed from the map,
     * then added back to the map at the location saved from the RespawnManager
     * then the runes would also be reset for the Player
     * and finally the ResetManager is going to execute the reset method for all of the Resettables
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if (!actor.isConscious()) {
            map.removeActor(actor);
            RespawnManager respawnManager = RespawnManager.getInstance();
            map.addActor(actor, respawnManager.getLocation());
            RuneManager.getInstance().resetRune();
            new Display().println(FancyMessage.YOU_DIED);
        }
        ResetManager.getInstance().run();
        return result;
    }


    /**
     * @param actor The actor performing the action.
     * @return null
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
