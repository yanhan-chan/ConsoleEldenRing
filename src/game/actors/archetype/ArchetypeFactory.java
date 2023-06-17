package game.actors.archetype;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that is responsible for creating Archetype instance
 *
 * Created by: Wai Jin, Jui Kai, Yanhan
 * @author Wai Jin, Jui Kai, Yanhan
 * Modified by: Wai Jin, Jui Kai, Yanhan
 */
public class ArchetypeFactory {

    /**
     * Factory method that creates Archetype
     *
     * @param role the string representation used to determine the Archetype
     * @return the Archetype instance created based on the role
     */
    public static Archetype createArchetype(String role) {
        Map<String, Archetype> archetypeMap = new HashMap<>();
        archetypeMap.put("a", new Astrologer());
        archetypeMap.put("b", new Bandit());
        archetypeMap.put("s", new Samurai());
        archetypeMap.put("w", new Wretch());

        return archetypeMap.get(role);
    }
}
