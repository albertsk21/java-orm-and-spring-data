package hospital.entities;

import hospital.common.constants.ExceptionMessages;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String address;
    private String email;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    private String picture;
    @Column(name = "have_medical_insurance")
    private String haveMedicalInsurance;



//    --------------------------------CONSTRUCTOR------------------------------------------

    public Patient() {}

    public Patient(String firstName, String lastName, String address, String email, LocalDate dateOfBirth, String picture, String haveMedicalInsurance) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setEmail(email);
        this.setDateOfBirth(dateOfBirth);
        this.setPicture(picture);
        this.setHaveMedicalInsurance(haveMedicalInsurance);

    }


//    ----------------------------------SETTERS--------------------------------------------

    private void setId(int id) {
        this.id = id;
    }
    private void setFirstName(String firstName) {

        if(firstName.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_FIRSTNAME);
        }

        this.firstName = firstName;
    }
    private void setLastName(String lastName) {
        if(lastName.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_LASTNAME);
        }

        this.lastName = lastName;
    }
    private void setAddress(String address) {
        if(address.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_ADDRESS);
        }

        this.address = address;
    }
    private void setEmail(String email) {
        Pattern pattern = Pattern.compile("[@]");
        Matcher matcher = pattern.matcher(email);

        if(email.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.EMAIL_IS_EMPTY);
        }else if(!matcher.find()){
            throw new IllegalArgumentException(ExceptionMessages.EMAIL_NOT_CONTAIN_AROUND);
        }

        this.email = email;
    }
    private void setDateOfBirth(LocalDate dateOfBirth) {

        if(dateOfBirth == null){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_DATE_OF_BIRTH);
        }

        this.dateOfBirth = dateOfBirth;
    }
    private void setPicture(String picture) {

        if(picture.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PICTURE);
        }

        this.picture = picture;
    }
    private void setHaveMedicalInsurance(String haveMedicalInsurance) {

        if(haveMedicalInsurance.trim().isEmpty()){
            throw new IllegalArgumentException(ExceptionMessages.INVALID_MEDICAL_INSURANCE);
        }

        this.haveMedicalInsurance = haveMedicalInsurance;
    }


    //    ----------------------------------GETTERS--------------------------------------------
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getAddress() {
        return address;
    }
    public String getEmail() {
        return email;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public String getPicture() {
        return picture;
    }
    public String getHaveMedicalInsurance() {
        return haveMedicalInsurance;
    }


    public String printPatient(){
        StringBuilder stringBuilder = new StringBuilder("");


        stringBuilder.append(String.format("Patient: %s %s\n",this.getFirstName(),this.getLastName()));
        stringBuilder.append(String.format("Address: %s\n",this.getAddress()));
        stringBuilder.append(String.format("Email: %s\n",this.getEmail()));
        stringBuilder.append(String.format("Date of birth: %s\n",this.getDateOfBirth()));
        stringBuilder.append(String.format("Have medical insurance: %s",this.getHaveMedicalInsurance()));

        return stringBuilder.toString();

    }

}
