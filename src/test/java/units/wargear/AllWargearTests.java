package units.wargear;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ 
	WargearDescriptionTest.class, 
	WargearTypeTest.class 
	})
public class AllWargearTests {

}
