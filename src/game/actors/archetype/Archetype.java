package game.actors.archetype;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Abstract class representing the Archetype
 *
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public abstract class Archetype {

    /**
     * The starting hit points of the Archetype
     */
    private int hitPoints;

    /**
     * The starting weapon of the Archetype
     */
    private WeaponItem weaponItem;

    /**
     * Constructor
     *
     * @param hitPoints The starting hit points of the Archetype
     * @param weaponItem The starting weapon of the Archetype
     */
    public Archetype(int hitPoints, WeaponItem weaponItem) {
        this.hitPoints = hitPoints;
        this.weaponItem = weaponItem;
    }

    /**
     * This method sets the starting weapon, and the hit points of the actor
     *
     * @param actor the actor to set the starting weapon and the hit points
     */
    public void setArchetype(Actor actor) {
        actor.resetMaxHp(this.hitPoints);
        actor.addWeaponToInventory(this.weaponItem);
    }
}
