package game.items;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * An interface that indicate the item consumable.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public interface Consumable {

    /**
     * Consume some item to heal player's hit point.
     *
     * @param actor The actor
     * @return A message to indicate the actor consume it nor the item is not available
     */
    public String consumeEffect(Actor actor);
}
