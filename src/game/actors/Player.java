package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.DeathAction;
import game.actions.ResetAction;
import game.items.FlaskOfCrimsonTears;
import game.items.Rune;
import game.items.RuneManager;
import game.statuseffects.StatusEffect;
import game.statuseffects.StatusEffectFactory;
import game.statuseffects.StatusEffectType;
import game.utils.Type;
import game.reset.Resettable;
import game.utils.Status;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class Player extends Actor implements Resettable {

	/**
	 * The menu
	 */
	private final Menu menu = new Menu();

	/**
	 * RuneManager used for managing runes
	 */
	private RuneManager runeManager;

	/**
	 * The Location that represents the last location of the Player
	 */
	private Location lastLocation;

	/**
	 * The instance variable of Player
	 */
	private static Player instance;

	/**
	 * The is reset variable of Player
	 */
	private boolean isReset;

	/**
	 * The status effects of Player
	 */
	private Map<StatusEffectType, StatusEffect> statusEffects = new HashMap<>();

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	private Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		addCapability(Status.HOSTILE_TO_ENEMY);
		addCapability(Type.FRIENDLY);
		addItemToInventory(FlaskOfCrimsonTears.getInstance());
		this.runeManager = RuneManager.getInstance();
		runeManager.setRune(this, new Rune());
		addToResetManager();
	}

	/**
	 * A factory method that allow a caller to obtain an instance that provide public constructors.
	 *
	 * @return The instance of Player
	 */
	public static Player getInstance() {
		if (instance == null) {
			instance = new Player("Tarnished", '@', 300);
		}
		return instance;
	}

	/**
	 * This method returns last Location of the Player
	 * @return Location that represents the last Location of the Player
	 */
	public Location getLastLocation() {
		return this.lastLocation;
	}

	/**
	 * Select and return an action to perform on the current turn.
	 *
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return the Action to be performed
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		if (isReset) {
			resetMaxHp(getMaxHp());
			isReset = false;
		}

		// check if player is inflicted with any status effects, add into list if there is any
		List<StatusEffectType> statusEffectTypes = findCapabilitiesByType(StatusEffectType.class);
		for (StatusEffectType statusEffectType: statusEffectTypes) {
			StatusEffect statusEffect = StatusEffectFactory.createStatusEffect(statusEffectType);
			statusEffects.put(statusEffectType, statusEffect);
			removeCapability(statusEffectType);
		}

		// check if player has any status effects, inflict status effect if there is any
		for (StatusEffect statusEffect: statusEffects.values()) {
			statusEffect.inflictEffect(this, map);
			if (!isConscious()) {
				new Display().println(new DeathAction(this).execute(this, map));
			}
		}

		// check if the player is conscious
		if (!isConscious()) {
			return new ResetAction();
		}

		// update last location
		this.lastLocation = map.locationOf(this);

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		String s = this.name + " " + this.printHp() + ", runes: " + runeManager.getRuneValue();
		display.println(s);
		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	/**
	 * Creates and returns an intrinsic weapon that punches a damage of 11
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11, "punches");
	}

	/**
	 * This method sets the boolean value of isReset of the PLayer to be true
	 */
	@Override
	public void reset() {
		isReset = true;
	}
}
