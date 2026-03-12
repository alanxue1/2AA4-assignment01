package CatanGame;

/**
 * Class that represents a parsed command from the user input
 */
public class CommandParsed {


  /** The type of command parsed from the user input */
  private String typeOfCommand;

  /** The type of build command, if applicable */
  private String typeOfBuild;

  /** The arguments parsed from the user input, if applicable */
  private int[] arguments;

  public CommandParsed(String typeOfCommand, String typeOfBuild, int[] arguments) {
    this.typeOfCommand = typeOfCommand;
    this.typeOfBuild = typeOfBuild;
    this.arguments = arguments;
  }

  /**
   * Returns the type of command parsed from the user input.
   * @return the type of command
   */
  public String getTypeOfCommand() {
    return typeOfCommand;
  }

  /**
   * Returns the type of build command, if applicable.
   * @return the type of build command
   */
  public String getTypeOfBuild() {
    return typeOfBuild;
  }

  /**
   * Returns the arguments parsed from the user input, if applicable.
   * @return the arguments as an array of integers
   */
  public int[] getArguments() {
    return arguments;
  }


}
