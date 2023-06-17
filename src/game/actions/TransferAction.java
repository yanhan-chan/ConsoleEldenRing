package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.enemies.Enemy;
import game.items.RuneManager;

/**
 * An Action to transfer runes
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class TransferAction extends Action {

    /**
     * The enemy used to determine the amount of runes to be transferred
     */
    private Enemy enemy;

    /**
     * The value of the runes to be transferred
     */
    private int rune;

    /**
     * RuneManager used for managing runes
     */
    private RuneManager runeManager;

    /**
     * Constructor
     *
     * @param enemy the enemy to determine the value of the runes transferred
     */
    public TransferAction(Enemy enemy) {
        this.enemy = enemy;
        this.rune = enemy.generateRune();
        this.runeManager = RuneManager.getInstance();
    }

    /**
     * When executed, the value of runes for the Player would be updated
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the result of the action
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        runeManager.setRuneValue(runeManager.getRuneValue() + rune);
        return menuDescription(actor);
    }

    /**
     * Describes which actor drops how many runes
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return enemy + " drops " + rune + " runes.";
    }
}
