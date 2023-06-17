package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.DeathAction;
import game.actions.ResetAction;
import game.utils.Status;

/**
 * A class that represent the Cliff in the game
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class Cliff extends Ground {
    /**
     * Constructor
     */
    public Cliff() {
        super('+');
    }

    /**
     * To indicate the actor allow to enter the ground or not.
     *
     * @param actor the Actor to check
     * @return True if the actor with HOSTILE_TO_ENEMY status
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * Ground can also experience the joy of time.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            GameMap map = location.map();
            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                new DeathAction(actor).execute(actor, map);
                actor.hurt(Integer.MAX_VALUE);
            }
        }
    }
}
