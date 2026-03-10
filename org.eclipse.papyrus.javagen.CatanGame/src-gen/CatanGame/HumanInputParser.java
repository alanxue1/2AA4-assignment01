package CatanGame;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HumanInputParser {

  private static final Pattern ROLL_PATTERN = Pattern.compile("^\\s*roll\\s*$", Pattern.CASE_INSENSITIVE);
  private static final Pattern GO_PATTERN = Pattern.compile("^\\s*go\\s*$", Pattern.CASE_INSENSITIVE);
  private static final Pattern LIST_PATTERN = Pattern.compile("^\\s*list\\s*$", Pattern.CASE_INSENSITIVE);
  private static final Pattern BUILD_SETTLEMENT_PATTERN = Pattern.compile(
      "^\\s*build\\s+settlement\\s+(\\d+)\\s*$", Pattern.CASE_INSENSITIVE);
  private static final Pattern BUILD_CITY_PATTERN = Pattern.compile(
      "^\\s*build\\s+city\\s+(\\d+)\\s*$", Pattern.CASE_INSENSITIVE);
  private static final Pattern BUILD_ROAD_PATTERN = Pattern.compile(
      "^\\s*build\\s+road\\s+(\\d+)\\s*,\\s*(\\d+)\\s*$", Pattern.CASE_INSENSITIVE);

  public CommandParsed parse(String input) {
    if (input == null) {
      return null;
    }

    Matcher matcher;

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
