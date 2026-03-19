package CatanGame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses human console input into structured game commands.
 */
public class HumanInputParser {

  /** Pattern for the "roll" command */
  private static final Pattern ROLL_PATTERN = Pattern.compile("^\\s*roll\\s*$", Pattern.CASE_INSENSITIVE);
  /** Pattern for the "go" command */
  private static final Pattern GO_PATTERN = Pattern.compile("^\\s*go\\s*$", Pattern.CASE_INSENSITIVE);
  /** Pattern for the "list" command */
  private static final Pattern LIST_PATTERN = Pattern.compile("^\\s*list\\s*$", Pattern.CASE_INSENSITIVE);
  /** Pattern for the "undo" command */
  private static final Pattern UNDO_PATTERN = Pattern.compile("^\\s*undo\\s*$", Pattern.CASE_INSENSITIVE);
  /** Pattern for the "redo" command */
  private static final Pattern REDO_PATTERN = Pattern.compile("^\\s*redo\\s*$", Pattern.CASE_INSENSITIVE);
  /** Pattern for "build settlement <nodeId>" */
  private static final Pattern BUILD_SETTLEMENT_PATTERN = Pattern.compile(
      "^\\s*build\\s+settlement\\s+(\\d+)\\s*$", Pattern.CASE_INSENSITIVE);
  /** Pattern for "build city <nodeId>" */
  private static final Pattern BUILD_CITY_PATTERN = Pattern.compile(
      "^\\s*build\\s+city\\s+(\\d+)\\s*$", Pattern.CASE_INSENSITIVE);
  /** Pattern for "build road <fromNodeId>, <toNodeId>" */
  private static final Pattern BUILD_ROAD_PATTERN = Pattern.compile(
      "^\\s*build\\s+road\\s+(\\d+)\\s*,\\s*(\\d+)\\s*$", Pattern.CASE_INSENSITIVE);

  /**
   * Parses one line of player input.
   * @param input The raw console input
   * @return A parsed command object, or null for invalid input
   */
  public CommandParsed parse(String input) {
    if (input == null) {
      return null;
    }

    Matcher matcher; // matcher used against each known command pattern

    matcher = ROLL_PATTERN.matcher(input);
    if (matcher.matches()) {
      return new CommandParsed("roll", null, null);
    }

    matcher = GO_PATTERN.matcher(input);
    if (matcher.matches()) {
      return new CommandParsed("go", null, null);
    }

    matcher = LIST_PATTERN.matcher(input);
    if (matcher.matches()) {
      return new CommandParsed("list", null, null);
    }

    matcher = UNDO_PATTERN.matcher(input);
    if (matcher.matches()) {
      return new CommandParsed("undo", null, null);
    }

    matcher = REDO_PATTERN.matcher(input);
    if (matcher.matches()) {
      return new CommandParsed("redo", null, null);
    }

    matcher = BUILD_SETTLEMENT_PATTERN.matcher(input);
    if (matcher.matches()) {
      int nodeId = Integer.parseInt(matcher.group(1));
      return new CommandParsed("build", "settlement", new int[]{nodeId});
    }

    matcher = BUILD_CITY_PATTERN.matcher(input);
    if (matcher.matches()) {
      int nodeId = Integer.parseInt(matcher.group(1));
      return new CommandParsed("build", "city", new int[]{nodeId});
    }

    matcher = BUILD_ROAD_PATTERN.matcher(input);
    if (matcher.matches()) {
      int fromNodeId = Integer.parseInt(matcher.group(1));
      int toNodeId = Integer.parseInt(matcher.group(2));
      return new CommandParsed("build", "road", new int[]{fromNodeId, toNodeId});
    }

    return null;
  }
}
