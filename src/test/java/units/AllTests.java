package units;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

import units.models.AllModelTests;
import units.wargear.AllWargearTests;

@Suite
@SelectClasses({
	AllUnitsTests.class,
	AllModelTests.class,
	AllWargearTests.class
})
public class AllTests {
}
