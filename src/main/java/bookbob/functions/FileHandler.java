package bookbob.functions;

import bookbob.entity.Patient;
import bookbob.entity.Records;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileHandler {

    private static final Logger logger = Logger.getLogger(FileHandler.class.getName());
    private static String filePath = "data" + File.separator + "bookbob_data.txt";

    public static void initFile(Records records){
        try {

            String directoryName = "data";
            String currentDirectory = System.getProperty("user.dir");
            String directory = currentDirectory + File.separator + directoryName;
            File directoryFile = new File(directory);

            if(directoryFile.mkdirs()) {           //directory was not created
                File file = new File(filePath);
                file.createNewFile();              //create new data file
            } else {                               //directory already created
                logger.log(Level.INFO, "Directory existed, creating new file");
                File file = new File(filePath);
                if(file.createNewFile()) {         //file was not created
                    logger.log(Level.INFO, "Directory existed, creating new file");
                } else {
                    retrieveData(records);
                }
            }
        } catch(Exception e){
            logger.log(Level.WARNING, "Error initializing file", e);
            e.printStackTrace();
        }
    }

    public static String convertPatientToOutputText(Patient patient) {
        String output = "";
        output += "Name: " + patient.getName() + " | " + "NRIC: " + patient.getNric() + " | "
                + "Phone Number: " + patient.getPhoneNumber() + " | " + "Date_Of_Birth: " + patient.getDateOfBirth()
                + " | " + "Home Address: " + patient.getHomeAddress() + " | "
                + "Diagnosis: " + patient.getDiagnosis() + " | " + "Medication: ";
        List<String> medications = patient.getMedication();
        for (String medication : medications) {
            output += medication + ";";
        }

        output += " | " + "Allergy: " + patient.getAllergy();
        output += " | " + "Sex: " + patient.getSex();
        output += " | " + "Medical History: " + patient.getMedicalHistory();

        return output;
    }

    public static void autosave(Records records) throws IOException {
        List<Patient> patients = records.getPatients();
        FileWriter fw = new FileWriter(filePath);
        for (Patient currPatient : patients) {
            String toWrite = convertPatientToOutputText(currPatient);
            fw.write(toWrite + "\n");
        }
        fw.close();
        logger.log(Level.INFO, "Autosaved successfully");
    }

    public static void retrieveData(Records records){
        try {
            File file = new File(filePath);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split("\\|");

                String name = data[0].substring(6).trim();
                String nric = data[1].substring(6).trim();
                String phoneNumber = data[2].substring(15).trim();
                String dateOfBirth = data[3].substring(16).trim();
                String homeAddress = data[4].substring(15).trim();
                String diagnosis = data[5].substring(12).trim();

                ArrayList<String> medications = new ArrayList<>();
                String[] rawMedications = data[6].substring(12).trim().split(";");
                for(int i = 0; i < rawMedications.length; i++) {
                    medications.add(rawMedications[i].trim());
                }

                String allergy = data[7].substring(9).trim();
                String sex = data[8].substring(5).trim();
                String medicalHistory = data[9].substring(17).trim();


                Patient patient = new Patient(name, nric, phoneNumber, dateOfBirth, homeAddress,
                        diagnosis, medications, allergy, sex, medicalHistory);
                records.addPatient(patient);
            }
            logger.log(Level.INFO, "Retrieved successfully");
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "File not found", e);
            throw new RuntimeException(e);
        }
    }

}
