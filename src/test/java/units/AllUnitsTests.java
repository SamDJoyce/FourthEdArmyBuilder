package units;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ 
	UnitDescriptionTest.class,
	UnitRoleTest.class})
public class AllUnitsTests {
}
