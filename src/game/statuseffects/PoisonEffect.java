package game.statuseffects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * PoisonEffect is use by actor to attack others.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class PoisonEffect extends StatusEffect {

    /**
     * The damage of PoisonEffect
     */
    private final int damage = 12;

    /**
     * Constructor
     */
    public PoisonEffect() {}

    /**
     * Reduce the hp of actor when inflict effect being applied.
     *
     * @param actor The Actor acting
     * @param map The GameMap containing the Actor
     */
    @Override
    public void inflictEffect(Actor actor, GameMap map) {
        actor.hurt(damage);
        new Display().println(actor + " is hurt by poison.");
    }
}
