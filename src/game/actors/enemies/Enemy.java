package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.DeathAction;
import game.actions.DespawnAction;
import game.actions.TransferAction;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.reset.Resettable;
import game.statuseffects.StatusEffect;
import game.statuseffects.StatusEffectFactory;
import game.statuseffects.StatusEffectType;
import game.utils.RandomNumberGenerator;
import game.utils.Status;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract class representing the Enemy. It implements the Resettable interface.
 *
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public abstract class Enemy extends Actor implements Resettable {

    /**
     * a hash map to map the Behaviours of the Enemy
     */
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * a hash map to map and store the status effects of the Enemy
     */
    private Map<StatusEffectType, StatusEffect> statusEffects = new HashMap<>();

    /**
     * a boolean value to determine if the Enemy is currently following an Actor
     */
    private boolean isFollowing;

    /**
     * a boolean value used to determine if a Game reset is needed
     */
    private boolean isReset;

    private boolean despawnable;

    /**
     * Constructor
     * @param name the name of the Enemy
     * @param displayChar the display character of the enemy
     * @param hitPoints the hit points of the Enemy
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        setIsFollowing(false);
        setReset(false);
        setDespawnable(true);
        addToResetManager();
    }

    /**
     * returns a hashmap containing the behaviours that the Enemy holds
     * @return a hashmap containing the behaviours that the Enemy holds
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return this.behaviours;
    }

    /**
     * Adds Behaviour that the Enemy has
     * @param key the key value of the Behaviour in the hashmap
     * @param behaviour the Behaviour to be added
     */
    public void addBehaviour(int key, Behaviour behaviour) {
        this.behaviours.put(key, behaviour);
    }

    /**
     * to determine if the Enemy is currently following an Actor
     * @return true if the Enemy is following an Actor, otherwise false
     */
    public boolean getIsFollowing() {
        return this.isFollowing;
    }

    /**
     * setter to set if the Enemy is following an Actor
     * @param flag the boolean value to be set
     */
    public void setIsFollowing(boolean flag) {
        this.isFollowing = flag;
    }

    /**
     * the getter to determine if the Game needs a reset for the Enemy
     * @return
     */
    public boolean getIsReset() {
        return this.isReset;
    }

    /**
     * the setter for isReset
     * @param flag
     */
    public void setReset(boolean flag) {
        this.isReset = flag;
    }

    /**
     * the getter for despawnable
     * @return
     */
    public boolean isDespawnable() {
        return this.despawnable;
    }

    /**
     * the setter for despawnable
     * @param flag
     */
    public void setDespawnable(boolean flag) {
        this.despawnable = flag;
    }

    /**
     * Get a copy of this Enemy's status effects
     * @return An unmodifiable wrapper of the status effects
     */
    public Map<StatusEffectType, StatusEffect> getStatusEffects() {
        return Collections.unmodifiableMap(this.statusEffects);
    }

    public void setStatusEffects(StatusEffectType statusEffectsType, StatusEffect statusEffect) {
        this.statusEffects.put(statusEffectsType, statusEffect);
    }

    /**
     * At each turn, select a valid action to perform.
     * The method first determines if the Game needs a reset, if true, it returns a DespawnAction
     * otherwise if the Enemy is unconscious, it returns a TransferAction
     * otherwise if the Enemy is not following another Actor
     * otherwise, it iterates through all the behaviours of the Enemy and returns the Action
     * otherwise, it returns a DoNothingAction
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     * @see DespawnAction
     * @see TransferAction
     * @see DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (isReset) {
            return (new DespawnAction());
        }

        if (!isConscious()) {
            return new TransferAction(this);
        }

        if (isDespawnable() && !getIsFollowing() && RandomNumberGenerator.getRandomInt(0, 100) <= 10) {
            return (new DespawnAction());
        }

        // check if the enemy is inflicted with any status effects, add into list if there is any
        List<StatusEffectType> statusEffectTypes = findCapabilitiesByType(StatusEffectType.class);
        for (StatusEffectType statusEffectType: statusEffectTypes) {
            StatusEffect statusEffect = StatusEffectFactory.createStatusEffect(statusEffectType);
            statusEffects.put(statusEffectType, statusEffect);
            removeCapability(statusEffectType);
        }

        // check if enemy has any status effects, inflict status effect if there is any
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
     * The enemy can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     * Returns a new collection of the Actions that the otherActor can do to the current Enemy
     *
     * @param otherActor the other Actor that might be performing the action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return an ActionList that contains the list of actions that can be performed on the current Enemy
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            addBehaviour(0, new FollowBehaviour(otherActor));
            setIsFollowing(true);
        }
        
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction));
            // HINT 1: The AttackAction above allows you to attack the enemy with your intrinsic weapon.
            // HINT 1: How would you attack the enemy with a weapon?
            if (!otherActor.getWeaponInventory().isEmpty()) {
                for (WeaponItem weapon: otherActor.getWeaponInventory()) {
                    actions.add(new AttackAction(this, direction, weapon));
                    if (weapon.getSkill(otherActor) != null) {
                        actions.add(weapon.getSkill(otherActor));
                    }
                    if (weapon.hasCapability(Status.HAS_SPECIAL_SKILL)) {
                        actions.add(weapon.getSkill(this, direction));
                    }
                }
            }
        }
        return actions;
    }

    /**
     * this method returns the value of the rune
     * @return an int representing the value of the rune
     */
    public abstract int generateRune();

    /**
     * this method sets the boolean value of isReset of the Enemy to be true
     */
    @Override
    public void reset() {
        this.isReset = true;
    }
}
