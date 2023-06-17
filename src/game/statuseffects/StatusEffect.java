package game.statuseffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * PoisonEffect is use by actor to attack others.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public abstract class StatusEffect {

    /**
     * Constructor
     */
    public StatusEffect() {}

    /**
     * Reduce the hp of actor when inflict effect being applied.
     *
     * @param actor The Actor acting
     * @param map The GameMap containing the Actor
     */
    public abstract void inflictEffect(Actor actor, GameMap map);
}
