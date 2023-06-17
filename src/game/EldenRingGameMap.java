package game;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.GroundFactory;

import java.io.IOException;
import java.util.List;

/**
 * EldenRingGameMap is a class extends GameMap
 * Created by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class EldenRingGameMap extends GameMap {
    /**
     * The map name of EldenRingGameMap
     */
    private String mapName;

    /**
     * Constructor
     *
     * @param groundFactory
     * @param groundChar
     * @param width
     * @param height
     */
    public EldenRingGameMap(GroundFactory groundFactory, char groundChar, int width, int height) {
        super(groundFactory, groundChar, width, height);
    }

    /**
     * Constructor
     *
     * @param groundFactory
     * @param lines
     * @param mapName
     */
    public EldenRingGameMap(GroundFactory groundFactory, List<String> lines, String mapName) {
        super(groundFactory, lines);
        this.mapName = mapName;
    }

    /**
     * Constructor
     *
     * @param groundFactory
     * @param mapFile
     * @throws IOException
     */
    public EldenRingGameMap(GroundFactory groundFactory, String mapFile) throws IOException {
        super(groundFactory, mapFile);
    }

    /**
     * Return the string represent the EldenRingGameMap
     *
     * @return The string represent the EldenRingGameMap
     */
    @Override
    public String toString() {
        return this.mapName;
    }
}
