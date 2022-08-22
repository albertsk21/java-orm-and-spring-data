package hospital.core;

import hospital.common.constants.ExceptionMessages;
import hospital.common.constants.OutputMessages;
import hospital.core.interfaces.Controller;
import hospital.entities.Medicament;
import hospital.entities.Patient;
import hospital.entities.Visitation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

public class ControllerImpl implements Controller {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hospital_db");
    EntityManager entityManager = entityManagerFactory.createEntityManager();


    @Override
    public String addPatient(String firstName, String lastName, String address, String email, LocalDate dateOfBirth, String picture, String haveMedicalInsurance) {
        this.entityManager.getTransaction().begin();
        Patient patient = new Patient( firstName, lastName, address, email, dateOfBirth, picture, haveMedicalInsurance);
        this.entityManager.persist(patient);
        this.entityManager.getTransaction().commit();
        return String.format(OutputMessages.ADDED_PATIENT,firstName,lastName,patient.getId());
    }

    @Override
    public String addVisitation(String comments, LocalDate date) {
        this.entityManager.getTransaction().begin();
        Visitation visitation = new Visitation(comments,date);
        this.entityManager.persist(visitation);
        this.entityManager.getTransaction().commit();
        return String.format(OutputMessages.ADDED_VISITATION,visitation.getId());
    }

    @Override
    public String addMedicament(String name) {
        this.entityManager.getTransaction().begin();
        Medicament medicament = new Medicament(name);
        this.entityManager.persist(medicament);
        this.entityManager.getTransaction().commit();
        return String.format(OutputMessages.ADDED_MEDICAMENT,name,medicament.getId());
    }



    @Override
    public String removePatient(String firstName, String lastName) {
        Patient patient =  this.findPatient(firstName,lastName);
        if(patient == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PATIENT,firstName,lastName));
        }
        this.entityManager.remove(patient);


        this.entityManager.getTransaction().commit();

        return String.format(OutputMessages.REMOVED_PATIENT,firstName,lastName,patient.getId());
    }

    @Override
    public String removeMedicament(String name) {
        Medicament medicament = findMedicament(name);
        if(medicament == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_MEDICAMENT,name));
        }
        this.entityManager.getTransaction().begin();;
        this.entityManager.remove(medicament);

        this.entityManager.getTransaction().commit();;

        return String.format(OutputMessages.REMOVED_MEDICAMENT,name,medicament.getId()) ;
    }

    @Override
    public String removeVisitation(LocalDate date) {

        Visitation visitation = this.findVisitation(date);


        if(visitation == null){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_VISITATION);
        }
        this.entityManager.getTransaction().begin();;

        this.entityManager.remove(visitation);

        this.entityManager.getTransaction().commit();;
        return String.format(OutputMessages.REMOVED_VISITATION,visitation.getId());
    }

    @Override
    public String printPatient(String firstName, String lastName) {
        Patient patient =  this.findPatient(firstName,lastName);
        if(patient == null){
          throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PATIENT,firstName,lastName));
        }
        return patient.printPatient();
    }

    @Override
    public String printMedicament(String name) {
        Medicament medicament = findMedicament(name);
        if(medicament == null){
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_MEDICAMENT,name));
        }
        return String.format(OutputMessages.MEDICAMENT_FOUND,name);
    }

    @Override
    public String printVisitation(LocalDate date) {
        Visitation visitation = this.findVisitation(date);
        if(visitation == null){
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_VISITATION);
        }
        return visitation.printVisitation();
    }

    @Override
    public String close() {
        return "Close";
    }
    private Patient findPatient(String firstName, String lastName){


        Query query = this.entityManager.createQuery("FROM Patient");

        List<Patient> patients = query.getResultList();

        for ( Patient patient : patients) {
            if (patient.getFirstName().equals(firstName) && patient.getLastName().equals(lastName)){
                return patient;
            }
        }

        return null;
    }
    private Medicament findMedicament(String name){
      Query query = this.entityManager.createQuery("FROM Medicament");
        List<Medicament> drugs = query.getResultList();


        for (Medicament medicament : drugs) {
            if(medicament.getName().equals(name)){
                return medicament;
            }
        }

        return null;
    }
    private Visitation findVisitation(LocalDate date){

       Query query = this.entityManager.createQuery("FROM Visitation");

        List<Visitation> visitations = query.getResultList();

        for (Visitation visitation : visitations) {

            if(visitation.getDate().equals(date)){
                return visitation;
            }
        }


            return null;

    }
}
