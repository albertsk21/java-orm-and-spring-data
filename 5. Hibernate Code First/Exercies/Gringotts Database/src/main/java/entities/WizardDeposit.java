package entities;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "wizard_deposits")
@Inheritance(strategy = InheritanceType.JOINED)
public class WizardDeposit {

//    --------------------------VARIABLES------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @Column(name = "first_name" ,length = 50)
    private String firstName;
    @Column(name = "last_name", length = 60)
    private String lastName;
    @Column(name = "notes",length = 1000)
    private String notes;
    @Column(name = "magic_wand_creator",length = 100)
    private String magicWandCreator;
    @Column(name = "magic_wand_size")
    private int magicWandSize;
    @Column(name = "deposit_group",length = 20)
    private String depositGroup;
    @Column(name = "deposit_start_date")
    private LocalDate depositStartDate;
    @Column(name = "deposit_amount")
    private double depositAmount;
    @Column(name = "deposit_interest")
    private double depositInterest;
    @Column(name = "deposit_charge")
    private double depositCharge;
    @Column(name = "deposit_expiration_date")
    private LocalDate depositExpirationDate;
    @Column(name = "is_deposit_expired")
    boolean isDepositExpired;


//    --------------------------CONSTRUCTORS------------------------------------------


    public WizardDeposit() {}

    public WizardDeposit(String firstName,
                         String lastName,
                         String notes,
                         String magicWandCreator,
                         int magicWandSize,
                         String depositGroup,
                         LocalDate depositStartDate,
                         double depositAmount,
                         double depositInterest,
                         double depositCharge,
                         LocalDate depositExpirationDate,
                         boolean isDepositExpired) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNotes(notes);
        this.setMagicWandCreator(magicWandCreator);
        this.setMagicWandSize(magicWandSize);
        this.setDepositGroup(depositGroup);
        this.setDepositStartDate(depositStartDate);
        this.setDepositAmount(depositAmount);
        this.setDepositInterest(depositInterest);
        this.setDepositCharge(depositCharge);
        this.setDepositExpirationDate(depositExpirationDate);
        this.setDepositExpired(isDepositExpired);
    }



//    --------------------------SETTERS------------------------------------------

    private void setId(int id) {
        this.id = id;
    }
    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    private void setLastName(String lastName) {
        this.lastName = lastName;
    }
    private void setNotes(String notes) {
        this.notes = notes;
    }
    private void setMagicWandCreator(String magicWandCreator) {
        this.magicWandCreator = magicWandCreator;
    }
    private void setMagicWandSize(int magicWandSize) {
        this.magicWandSize = magicWandSize;
    }
    private void setDepositGroup(String depositGroup) {
        this.depositGroup = depositGroup;
    }
    private void setDepositStartDate(LocalDate depositStartDate) {
        this.depositStartDate = depositStartDate;
    }
    private void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }
    private void setDepositInterest(double depositInterest) {
        this.depositInterest = depositInterest;
    }
    private void setDepositCharge(double depositCharge) {
        this.depositCharge = depositCharge;
    }
    private void setDepositExpirationDate(LocalDate depositExpirationDate) {
        this.depositExpirationDate = depositExpirationDate;
    }
    private void setDepositExpired(boolean depositExpired) {
        isDepositExpired = depositExpired;
    }

//    --------------------------GETTERS------------------------------------------

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getNotes() {
        return notes;
    }
    public String getMagicWandCreator() {
        return magicWandCreator;
    }
    public int getMagicWandSize() {
        return magicWandSize;
    }
    public String getDepositGroup() {
        return depositGroup;
    }
    public LocalDate getDepositStartDate() {
        return depositStartDate;
    }
    public double getDepositAmount() {
        return depositAmount;
    }
    public double getDepositInterest() {
        return depositInterest;
    }
    public double getDepositCharge() {
        return depositCharge;
    }
    public LocalDate getDepositExpirationDate() {
        return depositExpirationDate;
    }
    public boolean isDepositExpired() {
        return isDepositExpired;
    }
}
