package bookbob;

import bookbob.functions.CommandHandler;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import java.io.File;

public class MainTest {
    private static final String VALID_NRIC = "S9876543A";
    private static final String VALID_NAME = "John Doe";
    private static final String VALID_VISIT = "01-11-2024 14:30";

    // Test Basic Add Command
    @Test
    void testAddCommandBasic() throws IOException {
        // Add a patient
        String addCommand = String.format("add n/%s ic/%s v/%s",
                VALID_NAME, VALID_NRIC, VALID_VISIT);
        Main.processCommand(addCommand);

        // Verify by trying to find the added patient
        boolean findResult = Main.processCommand("find ic/" + VALID_NRIC);
        Assertions.assertFalse(findResult);

        // Delete test data
        Main.processCommand("delete " + VALID_NRIC);
    }

    // Test Add Command with Full Details
    @Test
    void testAddCommandFullDetails() throws IOException {
        // Add patient with all fields
        String addCommand = String.format("add n/%s ic/%s p/91234567 d/Fever m/Paracetamol " +
                        "ha/123 Main St dob/01-01-1990 v/%s al/Peanuts s/Male mh/Asthma",
                VALID_NAME, VALID_NRIC, VALID_VISIT);
        Main.processCommand(addCommand);

        // Verify by finding
        boolean findResult = Main.processCommand("find ic/" + VALID_NRIC);
        Assertions.assertFalse(findResult);

        // Clean up
        Main.processCommand("delete " + VALID_NRIC);
    }

    // Test Edit Command
    @Test
    void testEditCommand() throws IOException {
        // First add a patient
        Main.processCommand(String.format("add n/%s ic/%s v/%s",
                VALID_NAME, VALID_NRIC, VALID_VISIT));

        // Edit the patient
        String editCommand = String.format("edit ic/%s /to n/Jane Doe p/98765432", VALID_NRIC);
        Main.processCommand(editCommand);

        // Verify by finding
        boolean findResult = Main.processCommand("find ic/" + VALID_NRIC);
        Assertions.assertFalse(findResult);

        // Clean up
        Main.processCommand("delete " + VALID_NRIC);
    }

    // Test Add Visit Command
    @Test
    void testAddVisitCommand() throws IOException {
        // Add initial patient
        Main.processCommand(String.format("add n/%s ic/%s v/%s",
                VALID_NAME, VALID_NRIC, VALID_VISIT));

        // Add new visit
        String newVisitCommand = String.format("addVisit ic/%s v/02-11-2024 15:30 " +
                "d/Headache m/Ibuprofen", VALID_NRIC);
        Main.processCommand(newVisitCommand);

        // Verify by finding visits
        boolean findResult = Main.processCommand("findVisit " + VALID_NRIC);
        Assertions.assertFalse(findResult);

        // Clean up
        Main.processCommand("delete " + VALID_NRIC);
    }

    // Test Find Commands
    @Test
    void testFindCommands() throws IOException {
        // Add test patient
        Main.processCommand(String.format("add n/%s ic/%s v/%s",
                VALID_NAME, VALID_NRIC, VALID_VISIT));

        // Test different find commands
        Assertions.assertFalse(Main.processCommand("find n/John"));
        Assertions.assertFalse(Main.processCommand("find ic/" + VALID_NRIC));

        // Clean up
        Main.processCommand("delete " + VALID_NRIC);
    }

    // Test Delete Command
    @Test
    void testDeleteCommand() throws IOException {
        // Add patient
        Main.processCommand(String.format("add n/%s ic/%s v/%s",
                VALID_NAME, VALID_NRIC, VALID_VISIT));

        // Delete patient
        Main.processCommand("delete " + VALID_NRIC);

        // Verify deletion by trying to find
        boolean findResult = Main.processCommand("find ic/" + VALID_NRIC);
        Assertions.assertFalse(findResult);
    }

    // Test Invalid Commands
    @Test
    void testInvalidCommands() throws IOException {
        // Test various invalid commands
        Assertions.assertFalse(Main.processCommand("invalid command"));
        Assertions.assertFalse(Main.processCommand("add")); // missing required fields
        Assertions.assertFalse(Main.processCommand("find")); // missing search criteria
    }

    // Test Empty and Null Commands
    @Test
    void testEmptyAndNullCommands() throws IOException {
        Assertions.assertFalse(Main.processCommand(""));
        Assertions.assertFalse(Main.processCommand("  "));
    }

    // Test List Commands
    @Test
    void testListCommands() throws IOException {
        // Add test data
        Main.processCommand(String.format("add n/%s ic/%s v/%s",
                VALID_NAME, VALID_NRIC, VALID_VISIT));

        // Test list commands
        Assertions.assertFalse(Main.processCommand("list"));
        Assertions.assertFalse(Main.processCommand("listAppointments"));

        // Clean up
        Main.processCommand("delete " + VALID_NRIC);
    }

    // Test Help Command
    @Test
    void testHelpCommand() throws IOException {
        Assertions.assertFalse(Main.processCommand("help"));
    }

    // Test Exit Command
    @Test
    void testExitCommand() throws IOException {
        Assertions.assertTrue(Main.processCommand("exit"));
    }
}