package CatanGame;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("CatanGame — All Test Suites")
@SelectClasses({
    ResourceHandTest.class,
    BoardTest.class,
    ConfigTest.class,
    BuildingAndRoadTest.class,
    GameAndActionTest.class
})
class AllTestsSuite {
}
