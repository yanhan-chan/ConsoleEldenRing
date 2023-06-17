package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DespawnAction;
import game.actors.Player;
import game.behaviours.AttackBehaviour;
import game.behaviours.WanderBehaviour;
import game.utils.RandomNumberGenerator;
import game.utils.Type;

/**
 * A class that represents the enemy Invader
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class Invader extends Enemy {

    /**
     * Constructor
     */
    public Invader() {
        super("Invader", 'ඞ', 0);
        setDespawnable(false);
        addBehaviour(999, new WanderBehaviour());
        addBehaviour(1, new AttackBehaviour());
        addCapability(Type.NOT_FRIENDLY);
    }

    /**
     * This method first checks if there is a reset to be executed in the game
     * then it checks if the player is unconscious, if it is then a despawn action would be returned
     * otherwise a transfer action would be returned if the game reset is not required and the invader is unconscious
     * else, an action would be returned based on the collection of behaviours
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (getIsReset()) {
            setReset(false);
            if (!Player.getInstance().isConscious()) {
                return (new DespawnAction());
            }
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * this method returns the value of the rune between 1358 and 5578
     * @return an int representing the value of the rune
     */
    @Override
    public int generateRune() {
        return RandomNumberGenerator.getRandomInt(1358, 5578);
    }

    /**
     * A method to register this resettable.
     */
    @Override
    public void addToResetManager() {
        super.addToResetManager();
    }
}
