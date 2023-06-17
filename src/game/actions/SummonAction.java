package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.*;
import game.actors.archetype.*;
import game.actors.enemies.Invader;
import game.utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * An Action to summon an Ally or Invader
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 *
 */
public class SummonAction extends Action {

    /**
     * The location of the actor that performs the summon action
     */
    private Location location;

    /**
     * Constructor
     *
     * @param location location of the actor that performs the summon action
     */
    public SummonAction(Location location) {
        this.location = location;
    }

    /**
     * When executed, the archetype for the ally/invader would be determined randomly
     * then either an ally or invader would be summoned one of the possible exits
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the result of the SummonAction
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String outStr = "";

        // Randomly select an archetype for an ally/invader
        String[] roles = {"a", "b", "s", "w"};
        String role = roles[RandomNumberGenerator.getRandomInt(0, 3)];

        Actor summonActor;
        if (RandomNumberGenerator.getRandomInt(0, 100) <= 50) {
            summonActor = new Ally();
        } else {
            summonActor = new Invader();
        }

        Archetype archetype = ArchetypeFactory.createArchetype(role);
        archetype.setArchetype(summonActor);

        List<Location> locations = new ArrayList<>();
        for (Exit exit: location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(summonActor) && !destination.containsAnActor()) {
                locations.add(destination);
            }
        }

        if (!locations.isEmpty()) {
            int idx = RandomNumberGenerator.getRandomInt(0, locations.size()-1);
            locations.get(idx).addActor(summonActor);
            outStr += menuDescription(actor);
        } else {
            outStr += "Cannot summon guest as all the exits are occupied.";
        }
        return outStr;
    }

    /**
     * Describes which actor has summoned the guest
     *
     * @param actor The actor performing the action.
     * @return a description used for the menu UI
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " summons a guest from another realm.";
    }
}
