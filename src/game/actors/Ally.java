package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.DeathAction;
import game.actions.DespawnAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.reset.Resettable;
import game.statuseffects.StatusEffect;
import game.statuseffects.StatusEffectFactory;
import game.statuseffects.StatusEffectType;
import game.utils.Type;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Ally is the class that can help the player defeat enemies or invaders.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class Ally extends Actor implements Resettable {
    /**
     * The behaviours of Ally
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();
    /**
     * The status effects of Ally
     */
    private Map<StatusEffectType, StatusEffect> statusEffects = new HashMap<>();
    /**
     * To indicate is reset or not
     */
    private boolean isReset;

    /**
     * Constructor
     */
    public Ally() {
        super("Ally", 'A', 0);
        this.behaviours.put(999, new WanderBehaviour());
        this.behaviours.put(0, new AttackBehaviour());
        addCapability(Type.FRIENDLY);
        addToResetManager();
    }

    /**
     * Return DespawnAction if it is reset
     * Return DeathAction if it is not conscious
     * Return DoNothingAction if doesn't fulfill any requirement
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (isReset) {
            isReset = false;
            if (!Player.getInstance().isConscious()) {
                return new DespawnAction();
            }
        }

        // check if ally is inflicted with any status effects, add into list if there is any
        List<StatusEffectType> statusEffectTypes = findCapabilitiesByType(StatusEffectType.class);
        for (StatusEffectType statusEffectType: statusEffectTypes) {
            StatusEffect statusEffect = StatusEffectFactory.createStatusEffect(statusEffectType);
            statusEffects.put(statusEffectType, statusEffect);
            removeCapability(statusEffectType);
        }

        // check if ally has any status effects, inflict status effect if there is any
        for (StatusEffect statusEffect: statusEffects.values()) {
            statusEffect.inflictEffect(this, map);
            if (!isConscious()) {
                return new DeathAction(this);
            }
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Reset the game.
     */
    @Override
    public void reset() {
        this.isReset = true;
    }
}
