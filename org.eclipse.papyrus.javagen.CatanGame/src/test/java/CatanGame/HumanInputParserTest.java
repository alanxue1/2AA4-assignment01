package CatanGame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HumanInputParserTest {

    private HumanInputParser parser;

    @BeforeEach
    void setUp() {
        parser = new HumanInputParser();
    }

    @Test
    void testParseRoll() {
        CommandParsed result = parser.parse("roll");
        assertNotNull(result);
        assertEquals("roll", result.getTypeOfCommand());
        assertNull(result.getTypeOfBuild());
        assertNull(result.getArguments());
    }

    @Test
    void testParseRollCaseInsensitive() {
        CommandParsed upper = parser.parse("ROLL");
        assertNotNull(upper);
        assertEquals("roll", upper.getTypeOfCommand());

        CommandParsed mixed = parser.parse("Roll");
        assertNotNull(mixed);
        assertEquals("roll", mixed.getTypeOfCommand());
    }

    @Test
    void testParseGo() {
        CommandParsed result = parser.parse("go");
        assertNotNull(result);
        assertEquals("go", result.getTypeOfCommand());
    }

    @Test
    void testParseList() {
        CommandParsed result = parser.parse("list");
        assertNotNull(result);
        assertEquals("list", result.getTypeOfCommand());
    }

    @Test
    void testParseBuildSettlement() {
        CommandParsed result = parser.parse("build settlement 5");
        assertNotNull(result);
        assertEquals("build", result.getTypeOfCommand());
        assertEquals("settlement", result.getTypeOfBuild());
        assertArrayEquals(new int[]{5}, result.getArguments());
    }

    @Test
    void testParseBuildCity() {
        CommandParsed result = parser.parse("build city 12");
        assertNotNull(result);
        assertEquals("build", result.getTypeOfCommand());
        assertEquals("city", result.getTypeOfBuild());
        assertArrayEquals(new int[]{12}, result.getArguments());
    }

    @Test
    void testParseBuildRoad() {
        CommandParsed result = parser.parse("build road 3, 7");
        assertNotNull(result);
        assertEquals("build", result.getTypeOfCommand());
        assertEquals("road", result.getTypeOfBuild());
        assertArrayEquals(new int[]{3, 7}, result.getArguments());
    }

    @Test
    void testParseBuildRoadNoSpaceAfterComma() {
        CommandParsed result = parser.parse("build road 3,7");
        assertNotNull(result);
        assertEquals("build", result.getTypeOfCommand());
        assertEquals("road", result.getTypeOfBuild());
        assertArrayEquals(new int[]{3, 7}, result.getArguments());
    }

    @Test
    void testParseInvalidCommand() {
        assertNull(parser.parse("attack"));
        assertNull(parser.parse("trade"));
        assertNull(parser.parse(""));
    }

    @Test
    void testParseBuildMissingArgument() {
        assertNull(parser.parse("build settlement"));
        assertNull(parser.parse("build road 3"));
        assertNull(parser.parse("build city"));
    }

    @Test
    void testParseWithWhitespace() {
        CommandParsed result = parser.parse("  roll  ");
        assertNotNull(result);
        assertEquals("roll", result.getTypeOfCommand());
    }

    @Test
    void testParseNull() {
        assertNull(parser.parse(null));
    }
}
