package game.utils;

import java.util.Random;

/**
 * A random number generator
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 * @author Wai Jin, Jui Kai, Yanhan
 */
public class RandomNumberGenerator {
    /**
     * Generate a random integer larger than 0 but smaller than the bound.
     *
     * @param bound An integer that indicate the maximum bound
     * @return A random integer larger than 0 and smaller than bound
     */
    public static int getRandomInt(int bound) {
        return bound > 0 ? new Random().nextInt(bound) : 0;
    }

    /**
     * Generate a random integer between lower bound and upper bound.
     *
     * @param lowerBound An integer that indicate the minimum bound
     * @param upperBound An integer that indicate the maximum bound
     * @return A random integer that between the lower bound and larger bound
     */
    public static int getRandomInt(int lowerBound, int upperBound) {
        int range = upperBound - lowerBound + 1;
        return new Random().nextInt(range) + lowerBound;
    }
}
