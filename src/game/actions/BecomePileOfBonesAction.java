package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.PileOfBones;

/**
 * An Action to attack another Actor.
 * Created by: Jui Kai
 * @author Jui Kai
 * Modified by: Jui Kai
 *
 */
public class BecomePileOfBonesAction extends Action {

    /**
     * When executed, the actor to become Piles of Bones would be removed from the map,
     * a new instance of PileOfBone would be instantiated and added to the current location
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        map.removeActor(actor);
        map.addActor(new PileOfBones(actor), here);
        return menuDescription(actor);
    }

    /**
     * Describes which actor turned into Pile of Bones
     *
     * @param actor The actor performing the action.
     * @return description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " turns to a pile of bones!";
    }
}
