package CatanGame;

import java.util.Random;

/**
 * Single six-sided die
 */
public class RegularDice implements Dice {

  /** random number generator for rolls */
  private Random randomDieNum = new Random();

  /**
   * Rolls a single die
   * @return random number between 1 and 6
   */
  public int roll(){
    return randomDieNum.nextInt(6)+1;
  }
}