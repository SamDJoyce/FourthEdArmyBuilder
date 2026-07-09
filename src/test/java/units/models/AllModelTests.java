package units.models;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ 
	StatLineInfantryTest.class,
	StatLineVehicleTest.class,
	StatLineWalkerTest.class,
	ModelDescriptionTest.class
	 })
public class AllModelTests {

}
