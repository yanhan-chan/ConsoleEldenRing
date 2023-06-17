package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.DespawnAction;
import game.actions.TransferAction;
import game.utils.RandomNumberGenerator;
import game.utils.Type;

/**
 * A class that represents the enemy PileOfBones
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class PileOfBones extends Enemy {

    /**
     * to determine the number of game turn this actor is on
     */
    private int gameTurn;

    /**
     * the actor that turned into the PileOfBones
     */
    private Actor actor;

    /**
     * Constructor
     * @param actor the actor that turns into the PileOfBones
     */
    public PileOfBones(Actor actor) {
        super("Pile of Bones", 'X', 1);
        this.actor = actor;
        this.gameTurn = 0;
        copyWeapon();
        addCapability(Type.GRAVEYARD_RESIDENT);
    }

    /**
     * This method first return a DespawnAction if a game reset is needed
     * otherwise returns a TransferAction if the PileOfBones is not conscious
     * otherwise removes the PileOfBones of the gameturn is greater than 2 and adds the actor that turns into the piles of bones back to the map
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (getIsReset()) {
            return (new DespawnAction());
        }

        if (!isConscious()) {
            return (new TransferAction(this));
        }

        this.gameTurn++;
        if (this.gameTurn > 2) {
            Location here = map.locationOf(this);
            map.removeActor(this);
            actor.increaseMaxHp(0);
            here.addActor(actor);
        }
        return (new DoNothingAction());
    }

    private void copyWeapon() {
        for (WeaponItem weaponItem : actor.getWeaponInventory()) {
            this.addWeaponToInventory(weaponItem);
        }
    }

    /**
     * this method returns the value of the rune between 35 and 892
     * @return an int representing the value of the rune
     */
    @Override
    public int generateRune() {
        return RandomNumberGenerator.getRandomInt(35, 892);
    }
}
