package bookbob.functions;

import bookbob.entity.Patient;
import bookbob.entity.Records;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CommandHandler {
    private static final Logger logger = Logger.getLogger(CommandHandler.class.getName());
    private final FileHandler fileHandler = new FileHandler();

    public CommandHandler() throws IOException {
    }
  
    // Prints output for help command
    //@@author coraleaf0602 &yentheng0110 &kaboomzxc
    public void help() {
        System.out.println("""
                +-----------+---------------------------------------+---------------------------------+
                | Action    | Format                                | Example                         |
                +-----------+---------------------------------------+---------------------------------+
                | Help      | help                                  | help                            |
                +-----------+---------------------------------------+---------------------------------+
                | Add       | add n/NAME ic/NRIC [p/PHONE_NUMBER]   | add n/James Ho ic/S9534567A     |
                |           | [d/DIAGNOSIS] [m/MEDICATION]          | p/91234567 d/Asthma m/Albuterol |
                |           | [ha/HOME_ADDRESS] [dob/DATE_OF_BIRTH] | ha/NUS-PGPR dob/1990-01-01      |
                |          |[al/ALLERGY] [s/SEX] [mh/MEDICALHISTORY]| al/Pollen s/Male mh/diabetes    |
                +-----------+---------------------------------------+---------------------------------+
                | List      | list                                  | list                            |
                +-----------+---------------------------------------+---------------------------------+
                | Find      | find n/NAME          OR               | find n/John Doe                 |
                |           | find ic/NRIC         OR               | find ic/S1234                   |
                |           | find p/PHONE_NUMBER  OR               | find p/91234567                 |
                |           | find d/DIAGNOSIS     OR               | find d/Fever                    |
                |           | find m/MEDICATION    OR               | find m/Panadol                  |
                |           | find ha/HOME_ADDRESS OR               | find ha/NUS PGPR                |
                |           | find dob/DATE_OF_BIRTH OR             | find dob/1990-01-01             |
                |           | find al/ALLERGY       OR              | find al/Peanuts                 |
                |           | find s/SEX           OR               | find s/Female                   |
                |           | find mh/MEDICAL_HISTORY               | find mh/Diabetes                |
                +-----------+---------------------------------------+---------------------------------+
                | Delete    | delete NRIC                           | delete S9534567A                |
                +-----------+---------------------------------------+---------------------------------+
                | Save      | save(automatic)                       | save                            |
                +-----------+---------------------------------------+---------------------------------+
                | Retrieve/ | retrieve or import                    | retrieve                        |
                | Import    | (automatic)                           |                                 |
                +-----------+---------------------------------------+---------------------------------+
                | Exit      | exit                                  | exit                            |
                +-----------+---------------------------------------+---------------------------------+""");
    }

    //@@author yentheng0110
    public void add(String input, Records records) throws IOException {
        String name = "";
        String nric = "";
        String dateOfBirth = "";
        String phoneNumber = "";
        String homeAddress = "";
        String diagnosis = "";
        List<String> medications = new ArrayList<>();
        String allergy = "";
        String sex = "";
        String medicalHistory = "";


        // Extract name
        int nameStart = input.indexOf("n/");
        int nricStart = input.indexOf("ic/");

        assert nameStart != -1 :
                "Please provide a valid patient name.";

        if (nameStart == -1) {
            System.out.println("Please provide the patient's name.");
            return;
        }

        int nameEnd = findNextFieldStart(input, nameStart + 2);
        name = input.substring(nameStart + 2, nameEnd).trim();

        assert nricStart != -1 :
                "Please provide a valid patient NRIC.";

        if (nricStart == -1) {
            System.out.println("Please provide the patient's NRIC.");
            return;
        }

        int nricEnd = findNextFieldStart(input, nricStart + 3);
        nric = input.substring(nricStart + 3, nricEnd).trim();

        // Extract phone number
        int phoneStart = input.indexOf("p/");
        if (phoneStart != -1) {
            int phoneEnd = findNextFieldStart(input, phoneStart + 2);
            phoneNumber = input.substring(phoneStart + 2, phoneEnd).trim();
        }

        // Extract diagnosis
        int diagnosisStart = input.indexOf("d/");
        if (diagnosisStart != -1) {
            int diagnosisEnd = findNextFieldStart(input, diagnosisStart + 2);
            diagnosis = input.substring(diagnosisStart + 2, diagnosisEnd).trim();
        }

        // Extract medications (split by comma)
        int medicationStart = input.indexOf("m/");
        if (medicationStart != -1) {
            int medicationEnd = findNextFieldStart(input, medicationStart + 2);
            String meds = input.substring(medicationStart + 2, medicationEnd).trim();
            String[] medsArray = meds.split(",\\s*");
            for (String med : medsArray) {
                medications.add(med.trim());
            }
        }

        // Extract home address
        int homeAddressStart = input.indexOf("ha/");
        if (homeAddressStart != -1) {
            int homeAddressEnd = findNextFieldStart(input, homeAddressStart + 3);
            homeAddress = input.substring(homeAddressStart + 3, homeAddressEnd).trim();
        }

        // Extract date of birth
        int dobStart = input.indexOf("dob/");
        if (dobStart != -1) {
            int dobEnd = findNextFieldStart(input, dobStart + 4);
            dateOfBirth = input.substring(dobStart + 4, dobEnd).trim();
        }
        // @@author kaboomzxc
        // Extract allergy
        int allergyStart = input.indexOf("al/");
        if (allergyStart != -1) {
            int allergyEnd = findNextFieldStart(input, allergyStart + 3);
            allergy = input.substring(allergyStart + 3, allergyEnd).trim();
        }
        // @@author kaboomzxc
        // Extract sex
        int sexStart = input.indexOf("s/");
        if (sexStart != -1) {
            int sexEnd = findNextFieldStart(input, sexStart + 2);
            sex = input.substring(sexStart + 2, sexEnd).trim();
        }
        // @@author kaboomzxc
        // Extract medical history
        int medicalHistoryStart = input.indexOf("mh/");
        if (medicalHistoryStart != -1) {
            int medicalHistoryEnd = findNextFieldStart(input, medicalHistoryStart + 3);
            medicalHistory = input.substring(medicalHistoryStart + 3, medicalHistoryEnd).trim();
        }


        Patient patient = new Patient(name, nric);
        patient.setPhoneNumber(phoneNumber);
        patient.setDiagnosis(diagnosis);
        patient.setMedication(medications);
        patient.setHomeAddress(homeAddress);
        patient.setDateOfBirth(dateOfBirth);
        patient.setAllergy(allergy);
        patient.setSex(sex);
        patient.setMedicalHistory(medicalHistory);

        records.addPatient(patient);
        System.out.println("Patient " + name + " with NRIC " + nric + " added.");

        FileHandler.autosave(records);
    }

    //@@author yentheng0110
    // Utility method to find the start of the next field or the end of the input string
    private int findNextFieldStart(String input, int currentIndex) {
        int nextIndex = input.length(); // Default to end of input
        String[] prefixes = {"ic/", "p/", "d/", "m/", "ha/", "dob/", "al/", "s/", "mh/"};
        for (String prefix : prefixes) {
            int index = input.indexOf(prefix, currentIndex);
            if (index != -1 && index < nextIndex) {
                nextIndex = index;
            }
        }
        return nextIndex;
    }

    //@author yentheng0110 & kaboomzxc
    public void list(Records records) {
        List<Patient> patients = records.getPatients();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        for (Patient patient : patients) {
            System.out.println("Name: " + patient.getName() + ", NRIC: " + patient.getNric() +
                    ", Phone: " + patient.getPhoneNumber() + ", Diagnosis: " + patient.getDiagnosis() +
                    ", Medication: " + patient.getMedication() + ", Address: " + patient.getHomeAddress() +
                    ", DOB: " + patient.getDateOfBirth() + ", Allergy: " + patient.getAllergy() +
                    ", Sex: " + patient.getSex() + ", Medical History: " + patient.getMedicalHistory());
        }
    }

    // @@author G13nd0n
    public void delete(String nric, Records records) throws IOException {
        assert nric != null:
                "Please provide a valid NRIC";

        List<Patient> patients = records.getPatients();
        double initialSize = patients.size();
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            if (patient.getNric().equals(nric)) {
                patients.remove(i);
                System.out.println("Patient " + patient.getName() + ", " + nric + ", has been deleted.");
                break;
            }
        }

        if (patients.size() == initialSize) {
            System.out.println("Patient with " + nric + " not found");
        }
        FileHandler.autosave(records);
    }


    // Takes in an input string and determines whether to exit the program
    public void exit(String input) {
        if(input.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
    }

    // @@author kaboomzxc
    public void find(String input, Records records) {

        // Assertion to ensure input is not null
        assert input != null : "Input cannot be null";

        logger.log(Level.INFO, "Starting 'find' command processing.");

        // Input validation
        if (input == null || input.trim().isEmpty()) {
            logger.log(Level.WARNING, "Input cannot be null or empty");
            throw new IllegalArgumentException("Input cannot be null or empty");
        }

        Map<String, String> searchParams = extractSearchParams(input);

        if (searchParams.isEmpty()) {
            logger.log(Level.WARNING, "No valid search parameters provided.");
            System.out.println("Invalid search parameters. Please use the format: find n/NAME ic/NRIC [p/PHONE] " +
                    "[d/DIAGNOSIS] [m/MEDICATION] [ha/ADDRESS] [dob/DOB] [a/ALLERGY] [s/SEX] [mh/MEDICAL_HISTORY]");

            return;
        }

        List<Patient> matchedPatients = records.getPatients().stream()
                .filter(patient -> matchesSearchCriteria(patient, searchParams))
                .collect(Collectors.toList());

        displayResults(matchedPatients);
        logger.log(Level.INFO, "Successfully processed 'find' command.");
        logger.log(Level.INFO, "End of 'find' command processing.");
    }

    private Map<String, String> extractSearchParams(String input) {
        Map<String, String> params = new HashMap<>();
        String[] parts = input.split("\\s+");
        for (String part : parts) {
            if (part.contains("/")) {
                String[] keyValue = part.split("/", 2);
                if (keyValue.length == 2 && !keyValue[1].trim().isEmpty()) {
                    String key = keyValue[0].trim();
                    String value = keyValue[1].trim();
                    if (isValidSearchKey(key)) {
                        params.put(key, value.toLowerCase().trim());
                    } else {
                        logger.log(Level.WARNING, "Invalid search key encountered: {0}", key);
                        throw new IllegalArgumentException("Invalid search key: " + key);
                    }
                }
            }
        }
        return params;
    }

    private boolean isValidSearchKey(String key) {
        return Arrays.asList("n", "ic", "p", "d", "m", "ha", "dob", "al", "s", "mh").contains(key);
    }


    private boolean matchesSearchCriteria(Patient patient, Map<String, String> searchParams) {
        logger.log(Level.FINE, "Checking if patient matches search criteria: {0}", patient);

        boolean matches = searchParams.entrySet().stream().allMatch(entry -> {
            String key = entry.getKey();
            String value = entry.getValue();
            switch (key) {
            case "n":
                return patient.getName().toLowerCase().contains(value);
            case "ic":
                return patient.getNric().toLowerCase().contains(value);
            case "p":
                return patient.getPhoneNumber().toLowerCase().contains(value);
            case "d":
                return patient.getDiagnosis().toLowerCase().contains(value);
            case "m":
                return patient.getMedication().stream()
                        .anyMatch(med -> med.toLowerCase().contains(value));
            case "ha":
                return patient.getHomeAddress().toLowerCase().contains(value);
            case "dob":
                return patient.getDateOfBirth().toLowerCase().contains(value);
            case "al":
                return patient.getAllergy().toLowerCase().contains(value);
            case "s":
                return patient.getSex().toLowerCase().contains(value);
            case "mh":
                return patient.getMedicalHistory().toLowerCase().contains(value);
            default:
                return false;
            }
        });

        logger.log(Level.FINE, "Patient {0} matches criteria: {1}", new Object[]{patient.getNric(), matches});
        return matches;
    }

    private void displayResults(List<Patient> patients) {
        if (patients.isEmpty()) {
            System.out.println("No matching patients found.");
        } else {
            System.out.println("Matching patients:");
            for (Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }
}

