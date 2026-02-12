package CatanGame;

import java.util.Random;

/**
 * Simple dice utility used by the game loop.
 */
public class Dice {
    // RNG instance for generating roll outcomes
    public Random rng = new Random();

    /**
     * Simulates a die roll. Catan uses 2d6; this returns one die (1-6).
     * @return value between 1 and 6 inclusive
     */
    public int roll() {
        return rng.nextInt(6) + 1;
    }
}
