package bookbob;

import bookbob.functions.CommandHandler;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;

public class MainTest {

    //@@author kaboomzxc
    @Test
    void testCommandHandlerInitialization() {
        Assertions.assertDoesNotThrow(() -> {
            CommandHandler handler = new CommandHandler();
        }, "CommandHandler initialization should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testCommandHandlerHelp() throws IOException {
        CommandHandler handler = new CommandHandler();
        Assertions.assertDoesNotThrow(() -> {
            handler.help();
        }, "CommandHandler help should not throw exception");
    }

    //@@author kaboomzxc
    // Core Command Tests
    @Test
    void testMainProcessCommandExit() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("exit");
            Assertions.assertTrue(result, "Exit command should return true");
        }, "Processing exit command should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandHelp() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("help");
            Assertions.assertFalse(result, "Help command should return false");
        }, "Processing help command should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandUnknown() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("unknowncommand");
            Assertions.assertFalse(result, "Unknown command should return false");
        }, "Processing unknown command should not throw exception");
    }

    //@@author kaboomzxc
    // List Related Tests
    @Test
    void testMainProcessCommandList() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("list");
            Assertions.assertFalse(result, "List command should return false");
        }, "Processing list command should not throw exception");
    }

    //@@author kaboomzxc
    // Basic Add Operations
    @Test
    void testMainProcessCommandBasicAdd() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("add n/John Doe ic/S9876543A p/91234567 v/01-11-2024 14:30");
            Assertions.assertFalse(result, "Add command should return false");
        }, "Processing add command should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandAddWithAllFields() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand(
                    "add n/John Doe ic/S9876543A p/91234567 d/Fever m/Paracetamol " +
                            "ha/123 Main St dob/01-01-1990 v/01-11-2024 14:30 al/Peanuts s/Male mh/Asthma");
            Assertions.assertFalse(result, "Add command with all fields should return false");
        }, "Processing detailed add command should not throw exception");
    }

    //@@author kaboomzxc
    // Find Operations
    @Test
    void testMainProcessCommandFind() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("find n/John");
            Assertions.assertFalse(result, "Find command should return false");
        }, "Processing find command should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandFindByNRIC() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("find ic/S9876543A");
            Assertions.assertFalse(result, "Find by NRIC command should return false");
        }, "Processing find by NRIC command should not throw exception");
    }

    //@@author kaboomzxc
    // Find Related Tests
    @Test
    void testMainProcessCommandFindDiagnosis() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("findDiagnosis Fever");
            Assertions.assertFalse(result, "Find diagnosis command should return false");
        }, "Processing find diagnosis command should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandFindMedication() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("findMedication Paracetamol");
            Assertions.assertFalse(result, "Find medication command should return false");
        }, "Processing find medication command should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandFindByName() throws IOException {
        boolean result = Main.processCommand("find n/John");
        Assertions.assertFalse(result, "Find by name should return false");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandFindByPhone() throws IOException {
        boolean result = Main.processCommand("find p/91234567");
        Assertions.assertFalse(result, "Find by phone should return false");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandFindByInvalidCriteria() throws IOException {
        boolean result = Main.processCommand("find xyz/123");
        Assertions.assertFalse(result, "Find with invalid criteria should return false");
    }

    //@@author kaboomzxc
    // Visit Operations
    @Test
    void testMainProcessCommandAddVisit() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand(
                    "addVisit ic/S9876543A v/01-11-2024 14:30 d/Fever m/Paracetamol");
            Assertions.assertFalse(result, "Add visit command should return false");
        }, "Processing add visit command should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandFindVisit() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("findVisit S9876543A");
            Assertions.assertFalse(result, "Find visit command should return false");
        }, "Processing find visit command should not throw exception");
    }

    //@@author kaboomzxc
    // Appointment Operations
    @Test
    void testMainProcessCommandAddAppointment() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand(
                    "appointment n/John Doe ic/S9876543A date/01-11-2024 time/14:30");
            Assertions.assertFalse(result, "Add appointment command should return false");
        }, "Processing add appointment command should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandListAppointments() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("listAppointments");
            Assertions.assertFalse(result, "List appointments command should return false");
        }, "Processing list appointments command should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandAppointment() throws IOException {
        boolean result = Main.processCommand(
                "appointment n/John Doe ic/S9876543A date/01-11-2024 time/14:30");
        Assertions.assertFalse(result, "Add appointment command should return false");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandAppointmentMissingFields() throws IOException {
        boolean result = Main.processCommand("appointment n/John Doe ic/S9876543A");
        Assertions.assertFalse(result, "Appointment with missing fields should return false");
    }

    //@@author kaboomzxc
    // Edit Operations
    @Test
    void testMainProcessCommandEdit() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand(
                    "edit ic/S9876543A /to n/Jane Doe");
            Assertions.assertFalse(result, "Edit command should return false");
        }, "Processing edit command should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandEditVisit() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand(
                    "editVisit ic/S9876543A date/01-11-2024 14:30 d/Severe Fever");
            Assertions.assertFalse(result, "Edit visit command should return false");
        }, "Processing edit visit command should not throw exception");
    }

    //@@author kaboomzxc
    // Edit Related Tests
    @Test
    void testMainProcessCommandEditMissingNRIC() {
        Assertions.assertThrows(AssertionError.class, () -> {
            Main.processCommand("edit /to n/Jane Doe");
        }, "Edit without NRIC should throw AssertionError");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandEditMissingToKeyword() throws IOException {
        boolean result = Main.processCommand("edit ic/S9876543A n/Jane Doe");
        Assertions.assertFalse(result, "Edit without /to keyword should return false");
    }

    //@@author kaboomzxc
    // Delete Operations
    @Test
    void testMainProcessCommandDelete() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("delete S9876543A");
            Assertions.assertFalse(result, "Delete command should return false");
        }, "Processing delete command should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandDeleteAppointment() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand(
                    "deleteAppointment ic/S9876543A date/01-11-2024 time/14:30");
            Assertions.assertFalse(result, "Delete appointment command should return false");
        }, "Processing delete appointment command should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandDeleteNoNRIC() throws IOException {
        boolean result = Main.processCommand("delete");
        Assertions.assertFalse(result, "Delete without NRIC should return false");
    }

    //@@author kaboomzxc
    // Error Cases - Missing Fields
    @Test
    void testMainProcessCommandAddMissingName() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("add ic/S9876543A p/91234567 v/01-11-2024 14:30");
            Assertions.assertFalse(result, "Add command with missing name should return false");
        }, "Add command with missing name handled correctly");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandAddMissingNRIC() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("add n/John Doe p/91234567 v/01-11-2024 14:30");
            Assertions.assertFalse(result, "Add command with missing NRIC should return false");
        }, "Add command with missing NRIC handled correctly");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandAddMissingVisit() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("add n/John Doe ic/S9876543A p/91234567");
            Assertions.assertFalse(result, "Add command with missing visit should return false");
        }, "Add command with missing visit handled correctly");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandAddVisitMissingNRIC() throws IOException {
        boolean result = Main.processCommand("addVisit v/01-11-2024 14:30 d/Fever");
        Assertions.assertFalse(result, "Add visit without NRIC should return false");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandAddVisitMissingDate() throws IOException {
        boolean result = Main.processCommand("addVisit ic/S9876543A d/Fever");
        Assertions.assertFalse(result, "Add visit without date should return false");
    }

    //@@author kaboomzxc
    // Invalid Format Tests
    @Test
    void testMainProcessCommandInvalidNRICFormat() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("add n/John Doe ic/12345 v/01-11-2024 14:30");
            Assertions.assertFalse(result, "Add command with invalid NRIC should return false");
        }, "Processing add command with invalid NRIC should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandInvalidDateFormat() {
        Assertions.assertDoesNotThrow(() -> {
            Main.processCommand("add n/John Doe ic/S9876543A p/91234567 v/2024-11-01 14:30");
            Main.processCommand("list");
        }, "Processing add command with invalid date format should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandInvalidTimeFormat() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("add n/John Doe ic/S9876543A p/91234567 v/01-11-2024 25:00");
            Assertions.assertFalse(result, "Add command with invalid time should return false");
        }, "Add command with invalid time format handled correctly");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandValidRequiredFields() {
        Assertions.assertDoesNotThrow(() -> {
            Main.processCommand("add n/John Doe ic/S9876543A v/01-11-2024 14:30");
            Main.processCommand("list");
        }, "Processing add command with valid required fields should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandValidDateTimeFormat() {
        Assertions.assertDoesNotThrow(() -> {
            Main.processCommand("add n/John Doe ic/S9876543A p/91234567 v/01-11-2024 14:30");
            Main.processCommand("list");
        }, "Processing add command with valid date/time should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandMissingFields() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("add");
            Assertions.assertFalse(result, "Add command with no fields should return false");
        }, "Add command with no fields handled correctly");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandIncompleteCommand() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("add n/");
            Assertions.assertFalse(result, "Incomplete add command should return false");
        }, "Incomplete add command handled correctly");
    }

    //@@author kaboomzxc
    // Edge Cases - Empty or Special Characters
    @Test
    void testMainProcessCommandEmptyName() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("add n/ ic/S9876543A v/01-11-2024 14:30");
            Assertions.assertFalse(result, "Add command with empty name should return false");
        }, "Processing add command with empty name should not throw exception");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandNameWithSpecialChars() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("add n/John@Doe#123 ic/S9876543A v/01-11-2024 14:30");
            Assertions.assertFalse(result, "Add command with special characters should return false");
        }, "Processing add command with special characters should not throw exception");
    }

    //@@author kaboomzxc
    //Input validation Tests
    @Test
    void testMainProcessCommandEmptyString() throws IOException {
        boolean result = Main.processCommand("");
        Assertions.assertFalse(result, "Empty command should return false");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandWithExtraSpaces() throws IOException {
        boolean result = Main.processCommand("   help   ");
        Assertions.assertFalse(result, "Command with extra spaces should return false");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandCaseInsensitive() throws IOException {
        boolean result = Main.processCommand("HELP");
        Assertions.assertFalse(result, "Case insensitive command should return false");
    }

    //@@author kaboomzxc
    // Appointment Time Conflict Tests
    @Test
    void testMainProcessCommandOverlappingAppointments() {
        Assertions.assertDoesNotThrow(() -> {
            Main.processCommand("appointment n/John Doe ic/S9876543A date/01-11-2024 time/14:30");
            boolean result = Main.processCommand("appointment n/Jane Smith ic/S8765432B date/01-11-2024 time/14:30");
            Assertions.assertFalse(result, "Overlapping appointment command should return false");
        }, "Processing overlapping appointments should not throw exception");
    }

    //@@author kaboomzxc
    // Boundary Tests for Date and Time
    @Test
    void testMainProcessCommandDateBoundary() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("add n/John Doe ic/S9876543A v/31-12-2024 23:59");
            Assertions.assertFalse(result, "Add command with boundary date/time should return false");
        }, "Processing add command with boundary date/time should not throw exception");
    }

    //@@author kaboomzxc
    // Multiple Field Updates
    @Test
    void testMainProcessCommandMultipleEdits() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand(
                    "edit ic/S9876543A /to n/Jane Doe p/98765432 ha/New Address s/Female");
            Assertions.assertFalse(result, "Edit command with multiple fields should return false");
        }, "Processing edit command with multiple fields should not throw exception");
    }

    //@@author kaboomzxc
    // Search Edge Cases
    @Test
    void testMainProcessCommandPartialSearch() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("find n/Jo");
            Assertions.assertFalse(result, "Partial name search should return false");
        }, "Processing partial name search should not throw exception");
    }

    //@@author kaboomzxc
    // Command Combinations
    @Test
    void testMainProcessCommandSequence() {
        Assertions.assertDoesNotThrow(() -> {
            Main.processCommand("add n/John Doe ic/S9876543A v/01-11-2024 14:30");
            Main.processCommand("addVisit ic/S9876543A v/02-11-2024 14:30 d/Fever");
            boolean result = Main.processCommand("findVisit S9876543A");
            Assertions.assertFalse(result, "Command sequence should complete successfully");
        }, "Processing sequence of commands should not throw exception");
    }

    //@@author kaboomzxc
    // Delete Validation Tests
    @Test
    void testMainProcessCommandDeleteNonexistent() {
        Assertions.assertDoesNotThrow(() -> {
            boolean result = Main.processCommand("delete S9999999X");
            Assertions.assertFalse(result, "Delete nonexistent record should return false");
        }, "Processing delete of nonexistent record should not throw exception");
    }

    //@@author kaboomzxc
    // Visit History Tests
    @Test
    void testMainProcessCommandMultipleVisits() {
        Assertions.assertDoesNotThrow(() -> {
            Main.processCommand("add n/John Doe ic/S9876543A v/01-11-2024 14:30");
            Main.processCommand("addVisit ic/S9876543A v/02-11-2024 14:30 d/Fever");
            Main.processCommand("addVisit ic/S9876543A v/03-11-2024 14:30 d/Follow-up");
            boolean result = Main.processCommand("findVisit S9876543A");
            Assertions.assertFalse(result, "Multiple visits should be handled correctly");
        }, "Processing multiple visits should not throw exception");
    }

    //@@author kaboomzxc
    // Complex Data Tests
    @Test
    void testMainProcessCommandWithSpecialCharacters() throws IOException {
        boolean result = Main.processCommand(
                "add n/John@#$% ic/S9876543A v/01-11-2024 14:30");
        Assertions.assertFalse(result, "Command with special characters should return false");
    }

    //@@author kaboomzxc
    @Test
    void testMainProcessCommandSequentialOperations() throws IOException {
        Main.processCommand("add n/John Doe ic/S9876543A v/01-11-2024 14:30");
        Main.processCommand("edit ic/S9876543A /to n/Jane Doe");
        boolean result = Main.processCommand("delete S9876543A");
        Assertions.assertFalse(result, "Sequential operations should be handled correctly");
    }

}
