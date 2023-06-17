package game.utils;

import edu.monash.fit2099.engine.displays.Display;
import game.actors.*;
import game.actors.archetype.*;

import java.util.Scanner;

/**
 * MenuManager is a class allow the player to choose what's the next step they tend to proceed.
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class MenuManager {
    /**
     * Display the menu on console and allow player to choose Combat Archetypes.
     *
     * This method will receive the choice from players.
     * Assign the Combat Archetypes according to their choice.
     * @return A player object (either Bandit, Samurai or Wretch)
     */
    public static Player playerClassMenu() {
        Scanner sel = new Scanner(System.in);
        new Display().println("Select your role: ");
        new Display().println("a: Astrologer");
        new Display().println("b: Bandit");
        new Display().println("s: Samurai");
        new Display().println("w: Wretch");

        String choice = sel.nextLine();
        Player actor = Player.getInstance();
        Archetype archetype = ArchetypeFactory.createArchetype(choice.toLowerCase());
        archetype.setArchetype(actor);
        return actor;
    }
}
