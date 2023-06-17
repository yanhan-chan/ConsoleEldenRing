package game.actors.archetype;

import game.utils.Status;
import game.weapons.Uchigatana;

/**
 * A class representing a Samurai Archetype
 *
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class Samurai extends Archetype {

    /**
     * Constructor
     */
    public Samurai() {
        super(455, new Uchigatana());
    }
}
