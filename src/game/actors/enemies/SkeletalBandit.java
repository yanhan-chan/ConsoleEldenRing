package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.BecomePileOfBonesAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.utils.Type;
import game.utils.RandomNumberGenerator;
import game.utils.Status;
import game.weapons.Scimitar;

/**
 * A class that represents the enemy SkeletalBandit
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class SkeletalBandit extends Enemy {

    public SkeletalBandit() {
        /**
         * Constructor
         */
        super("Skeletal Bandit", 'b', 184);
        addBehaviour(999, new WanderBehaviour());
        addBehaviour(1, new AttackBehaviour());
        addWeaponToInventory(new Scimitar());
        addCapability(Type.GRAVEYARD_RESIDENT);
        addCapability(Status.PILES_OF_BONES_CAPABLE);
    }

    /**
     * Returns a BecomePileOfBonesAction if the SkeletalBandit is not conscious
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (!isConscious()) {
            return (new BecomePileOfBonesAction());
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * this method returns the value of the rune
     * @return an int representing the value of the rune
     */
    @Override
    public int generateRune() {
        return RandomNumberGenerator.getRandomInt(35, 892);
    }
}
