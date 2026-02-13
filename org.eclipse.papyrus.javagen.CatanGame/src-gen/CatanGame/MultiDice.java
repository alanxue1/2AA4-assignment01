package CatanGame;

import java.util.List;
import java.util.ArrayList;

/**
 * Multiple dice rolled together
 */
public class MultiDice implements Dice {

  /** list of dice to roll */
  private List<Dice> diceList;

  /**
   * Constructor that creates two regular dice
   */
  public MultiDice() {
    diceList = new ArrayList<>();
    diceList.add(new RegularDice());
    diceList.add(new RegularDice());
  }

  /**
   * Rolls all dice and sums the results
   * @return total sum of all dice
   */
  public int roll() {
    int sum=0; // running total
    for (Dice die: diceList) {
      sum += die.roll();
    }
    return sum;
  }
}