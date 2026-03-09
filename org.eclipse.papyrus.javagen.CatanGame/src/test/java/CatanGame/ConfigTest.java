package CatanGame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Suite 3: Config Tests")
class ConfigTest {

    @TempDir
    Path tempDir;

    private String writeConfigFile(String content) throws IOException {
        Path file = tempDir.resolve("config.txt");
        Files.writeString(file, content);
        return file.toString();
    }

    @Test
    @DisplayName("Boundary testing: turns 0 (below min), 1 (min), 8192 (max), 8193 (above max)")
    void testConfigBoundaryValues() throws IOException {
        // turns: 0 -> out of [1, 8192] -> default 50
        assertEquals(50, new Config(writeConfigFile("turns: 0")).getMaxRounds());

        // turns: 1 -> lower boundary -> accepted
        assertEquals(1, new Config(writeConfigFile("turns: 1")).getMaxRounds());

        // turns: 8192 -> upper boundary -> accepted
        assertEquals(8192, new Config(writeConfigFile("turns: 8192")).getMaxRounds());

        // turns: 8193 -> above upper boundary -> default 50
        assertEquals(50, new Config(writeConfigFile("turns: 8193")).getMaxRounds());
    }

    @Test
    @DisplayName("Nonexistent file path falls back to default 50")
    void testConfigMissingFile() {
        Config config = new Config("/no/such/file.txt");
        assertEquals(50, config.getMaxRounds());
    }

    @Test
    @DisplayName("Blank path falls back to default 50")
    void testConfigBlankPath() {
        Config config = new Config("   ");
        assertEquals(50, config.getMaxRounds());
    }
}
