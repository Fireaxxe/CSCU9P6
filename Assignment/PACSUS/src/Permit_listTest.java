import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Permit_listTest {
	static Permit_list staticInstance;
	Permit_list localInstance;

	@BeforeEach
	public void setUp() {
		localInstance = new Permit_list();
	}

	@AfterEach
	public void tearDown() {
		// not really needed
	}

	/**
	 * Tests the constructor
	 * 
	 * @author Nicholas Whitton
	 */
	@Test
	public void testPermit_list() {
		// Check that there is no Permit_list already present.
		assertNull(staticInstance, "Test instance did not start as null.");
		staticInstance = new Permit_list();
		// Check that a Permit_list was created and that it is empty
		// We must assume that getAll functions as intended, presuming all other tests
		// pass, as its successfulness can only be measured in relation to other
		// methods
		assertNotNull(staticInstance, "A Permit_list was not successfully instantiated.");
		assertTrue(staticInstance.getAll().size() == 0, "The list was not empty when generated.");
	}

	/**
	 * Tests the addPermit method
	 * 
	 * @author Nicholas Whitton
	 */
	@Test
	public void testAddPermit() {
		// Check that a day visitor permit can be added correctly
		assertTrue(localInstance.addPermit(new Day_visitor_permit("Test1", "Test0", new Date(1)), null),
				"Incorrect return value");
		assertTrue(localInstance.getAll().size() == 1, "The list did not have one entry: " + localInstance.getAll());
		assertTrue(localInstance.get("Test1") instanceof Day_visitor_permit,
				"The list did not create the right type of entry: " + localInstance.get("Test1"));

		// Check that a regular visitor permit can be added correctly
		assertTrue(
				localInstance.addPermit(new Regular_visitor_permit("Test2", "Test0", new Date(1), new Date(3)), null),
				"Incorrect return value");
		assertTrue(localInstance.getAll().size() == 2, "The list did not have two entries: " + localInstance.getAll());
		assertTrue(localInstance.get("Test2") instanceof Regular_visitor_permit,
				"The list did not create the right type of entry: " + localInstance.get("Test2"));

		// Check that a permanent visitor permit can be added correctly
		assertTrue(localInstance.addPermit(new Permanent_visitor_permit("Test3"), null), "Incorrect return value");
		assertTrue(localInstance.getAll().size() == 3,
				"The list did not have three entries: " + localInstance.getAll());
		assertTrue(localInstance.get("Test3") instanceof Permanent_visitor_permit,
				"The list did not create the right type of entry: " + localInstance.get("Test3"));

		// Check that a university member permit can be added correctly
		assertTrue(localInstance.addPermit(new University_member_permit("Test4", new Date(1)), null),
				"Incorrect return value");
		assertTrue(localInstance.getAll().size() == 4, "The list did not have four entries: " + localInstance.getAll());
		assertTrue(localInstance.get("Test4") instanceof University_member_permit,
				"The list did not create the right type of entry: " + localInstance.get("Test4"));

		// Check that the system will not allow permits with duplicate names
		assertFalse(localInstance.addPermit(new Permanent_visitor_permit("Test1"), null), "Incorrect return value");
		assertTrue(localInstance.getAll().size() == 4, "The list added a duplicate name: " + localInstance.getAll());
		assertTrue(localInstance.get("Test1") instanceof Day_visitor_permit,
				"The list replaced an existing permit illegally: " + localInstance.get("Test1"));

		// Move on the date so that the day and regular visitor permits expire
		for (int i = 2; i <= 4; i++) {
			localInstance.informNewDate(new Date(i));
		}

		// Check that new permits may be added for holders of previously expired day
		// visitor permits
		localInstance.addPermit(new Regular_visitor_permit("Test1", "Test0", new Date(Date.yearLength), new Date(1)),
				null);
		assertTrue(localInstance.getAll().size() == 3,
				"The list did not allow a new permit to be created for someone with a previously expired Day visitor permit.");
		assertTrue(localInstance.get("Test1") instanceof Regular_visitor_permit,
				"The list did not create the right type of entry: " + localInstance.get("Test1"));

		// Check that new permits may be added for holders of previously expired regular
		// visitor permits
		localInstance.addPermit(new Day_visitor_permit("Test2", "Test0", new Date(Date.yearLength)), null);
		assertTrue(localInstance.getAll().size() == 4,
				"The list did not allow a new permit to be created for someone with a previously expired Regular visitor permit.");
		assertTrue(localInstance.get("Test2") instanceof Day_visitor_permit,
				"The list did not create the right type of entry: " + localInstance.get("Test2"));

		// Remove the permit belonging to Test4
		localInstance.removePermit(localInstance.get("Test4"), null);

		// Check that new permits may be added for people with previously manually
		// removed permits
		assertTrue(localInstance.addPermit(new Permanent_visitor_permit("Test4"), null), "Incorrect return value");
		assertTrue(localInstance.getAll().size() == 4,
				"The list did not allow a new permit to be created for someone with a previously removed permit.");
		assertTrue(localInstance.get("Test4") instanceof Permanent_visitor_permit,
				"The list did not create the right type of entry: " + localInstance.get("Test4"));
		
		// Check that attempting to add null throws a NullPointerException
		try {
			localInstance.addPermit(null, null);
			fail("Did not throw NullPointerException");
		}catch(NullPointerException e) {
		}
	}

	/**
	 * Tests the informNewDate method
	 * 
	 * @author Nicholas Whitton
	 */
	@Test
	public void testInformNewDate() {
		// Add an example of each type of permit to the list
		localInstance.addPermit(new Day_visitor_permit("Test1", "Test0", new Date(1)), null);
		localInstance.addPermit(new Regular_visitor_permit("Test2", "Test0", new Date(1), new Date(3)), null);
		localInstance.addPermit(new Permanent_visitor_permit("Test3"), null);
		localInstance.addPermit(new University_member_permit("Test4", new Date(1)), null);

		// Make sure the list is the correct size before running proper tests
		assertTrue(localInstance.getAll().size() == 4);

		// Inform the system that it is now day 2 and check that day 1 permits have
		// been removed correctly
		localInstance.informNewDate(new Date(2));
		assertTrue(localInstance.getAll().size() == 3,
				"The list did not delete the expired Day visitor permit correctly");
		assertNull(localInstance.get("Test1"), "The list did not delete the expired Day visitor permit");

		// Add a regular visitor permit which will run over the new year
		localInstance.addPermit(new Regular_visitor_permit("Test1", "Test0", new Date(Date.yearLength), new Date(1)),
				null);

		// Inform the system that it is now day 3 and check that no permits have been
		// removed (as none were to expire after day 2)
		localInstance.informNewDate(new Date(3));
		assertTrue(localInstance.getAll().size() == 4, "Permits were incorrectly deleted at new day.");

		// Inform the system that it is now day 4 and check that day 3 permits have
		// been removed correctly
		localInstance.informNewDate(new Date(4));
		assertTrue(localInstance.getAll().size() == 3,
				"The list did not delete the expired Regular visitor permit correctly");
		assertNull(localInstance.get("Test2"), "The list did not delete the expired Regular visitor permit");

		// Add a day visitor permit which will expire at the new year
		localInstance.addPermit(new Day_visitor_permit("Test2", "Test0", new Date(Date.yearLength)), null);

		// Add a regular visitor permit which will expire at the new year
		localInstance.addPermit(new Regular_visitor_permit("Test5", "Test0", new Date(5), new Date(Date.yearLength)),
				null);

		// Add a day visitor permit which will be valid after the new year
		localInstance.addPermit(new Day_visitor_permit("Test6", "Test0", new Date(1)), null);

		// Make sure the list is the correct size
		assertTrue(localInstance.getAll().size() == 6);

		// Check that no permits expire before the new year
		for (int i = 5; i <= Date.yearLength; i++) {
			localInstance.informNewDate(new Date(i));
			assertTrue(localInstance.getAll().size() == 6,
					"The list deleted an entry incorrectly between day 4 and the end of the year: "
							+ localInstance.getAll().toString() + ", Day: " + i);
		}

		// Check that permits which run over the new year are removed and replaced with
		// new permits and that permits which expire on the last day of the year are
		// removed correctly
		HashSet<Permit> permitsBeforeNewYear = new HashSet<Permit>();
		permitsBeforeNewYear.addAll(localInstance.getAll());
		localInstance.informNewDate(new Date(1));
		permitsBeforeNewYear.retainAll(localInstance.getAll());// retainAll - set intersection
		assertTrue(localInstance.getAll().size() == 4, // Check 4 permits were renewed
				"The 2 permits which should expire at the new year were not deleted correctly.");
		assertTrue(permitsBeforeNewYear.size() == 0, // Check that permits did not carry over
				"The permits carried over instead of being renewed as new permits.");

		// Check that the permits which were renewed and had to expire at the end of day
		// 1 do so
		localInstance.informNewDate(new Date(2));
		assertTrue(localInstance.getAll().size() == 2,
				"The 2 permits which should expire after carrying over were not deleted correctly.");
	}

	/**
	 * Test the get method
	 */
	@Test
	public void testGet() {
		// Add an example of each type of permit to the list
		localInstance.addPermit(new Day_visitor_permit("Test1", "Test0", new Date(1)), null);
		localInstance.addPermit(new Regular_visitor_permit("Test2", "Test0", new Date(1), new Date(3)), null);
		localInstance.addPermit(new Permanent_visitor_permit("Test3"), null);
		localInstance.addPermit(new University_member_permit("Test4", new Date(1)), null);

		// Make sure the list is the correct size before running proper tests
		assertTrue(localInstance.getAll().size() == 4);

		// Check that for every permit on the list, calling get on its holder produces
		// that permit
		for (Permit p : localInstance.getAll()) {
			assertTrue(localInstance.get(p.getHolder()) == p, "The get method fails to retrieve the correct permit: "
					+ p.getHolder() + ", " + localInstance.get(p.getHolder()).getHolder());
		}
		// Check that getting a value which is not on the list results in null.
		assertNull(localInstance.get("TestFail"), "Retrieved an invalid value: TestFail");

		// Check that attempting to call get on null results in a NullPointerException
		try {
			localInstance.get(null);
			fail("Did not throw NullPointerException.");
		} catch (NullPointerException e) {
		}
	}

	/**
	 * Test the remove permit method
	 * @author Nicholas Whitton
	 */
	@Test
	public void testRemovePermit() {
		// Add an example of each type of permit to the list
		localInstance.addPermit(new Day_visitor_permit("Test1", "Test0", new Date(1)), null);
		localInstance.addPermit(new Regular_visitor_permit("Test2", "Test0", new Date(1), new Date(3)), null);
		localInstance.addPermit(new Permanent_visitor_permit("Test3"), null);
		localInstance.addPermit(new University_member_permit("Test4", new Date(1)), null);

		// Check that the list is the correct size before running proper tests
		assertTrue(localInstance.getAll().size() == 4);

		// Check that the permit belonging to Test4 can be removed correctly.
		assertTrue(localInstance.removePermit(localInstance.get("Test4"), null), "Incorrect return value");
		assertNull(localInstance.get("Test4"), "The permit belonging to Test4 was not removed.");
		assertTrue(localInstance.getAll().size() == 3, "The permit belonging to Test4 was not removed correctly.");

		// Check that the permit belonging to Test2 can be removed correctly.
		assertTrue(localInstance.removePermit(localInstance.get("Test2"), null), "Incorrect return value");
		assertNull(localInstance.get("Test2"), "The permit belonging to Test2 was not removed.");
		assertTrue(localInstance.getAll().size() == 2, "The permit belonging to Test2 was not removed correctly.");

		// Check that it is impossible to remove a permit that is not in the list
		assertFalse(localInstance.removePermit(new Permanent_visitor_permit("Test2"), null), "Incorrect return value");
		assertNull(localInstance.get("Test2"), "Something went wrong, introducing a new Test2 permit to the list.");
		assertTrue(localInstance.getAll().size() == 2, "The list had a permit incorrectly added or removed.");

		// Check that attempting to remove null results in a NullPointerException
		try {
			localInstance.removePermit(null, null);
			fail("Did not throw NullPointerException");
		} catch (NullPointerException e) {
		}
	}

}
