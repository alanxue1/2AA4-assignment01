package CatanGame;

public class CommandParsed {
  private String typeOfCommand;
  private String typeOfBuild;
  private int[] arguments;

  public CommandParsed(String typeOfCommand, String typeOfBuild, int[] arguments) {
    this.typeOfCommand = typeOfCommand;
    this.typeOfBuild = typeOfBuild;
    this.arguments = arguments;
  }


  public String getTypeOfCommand() {
    return typeOfCommand;
  }

  public String getTypeOfBuild() {
    return typeOfBuild;
  }

  public int[] getArguments() {
    return arguments;
  }


}
