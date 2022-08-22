package hospital.core.interfaces;

import java.time.LocalDate;

public interface Controller {
    String addPatient(String firstName, String lastName, String address, String email, LocalDate dateOfBirth, String picture, String haveMedicalInsurance);
    String addVisitation(String comments, LocalDate date);
    String addMedicament(String name);
    String removePatient(String firstName,String lastName);
    String removeMedicament(String name);
    String removeVisitation(LocalDate date);
    String printPatient(String firstName, String lastName);
    String printMedicament(String name);
    String printVisitation(LocalDate date);
    String close();
}
