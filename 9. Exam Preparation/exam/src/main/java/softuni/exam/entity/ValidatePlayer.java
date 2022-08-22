package softuni.exam.entity;


import softuni.exam.enums.Position;

import java.math.BigDecimal;

public class ValidatePlayer {


    private String firstName;
    private String lastName;
    private int number;
    private Position position;
    private BigDecimal salary;


    public ValidatePlayer(String firstName, String lastName, int number, String position, BigDecimal salary) {

        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setNumber(number);
        this.setPosition(position);
        this.setSalary(salary);
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    private void setLastName(String lastName) {

        if(!(lastName.length() >= 3 && lastName.length() <= 15)){
            throw new IllegalArgumentException("Invalid Player");
        }

        this.lastName = lastName;
    }
    private void setNumber(int number) {

        if (!(number >= 3 && number <= 99)){
            throw new IllegalArgumentException("Invalid Player");
        }
        this.number = number;
    }
    private void setPosition(String position) {
        Position validatePosition = this.findPosition(position);

        if(validatePosition == null){
            throw new IllegalArgumentException("Invalid Player");
        }

        this.position = validatePosition;
    }


    private Position findPosition(String positionName){


        switch (positionName){
            case "GK":
                return Position.GK;
            case "CD":
                return Position.CD;
            case "RB":
                return Position.RB;
            case "LB":
                return Position.LB;
            case "CM":
                return Position.CM;
            case "DM":
                return Position.DM;
            case "CDM":
                return Position.CDM;
            case "LM":
                return Position.LM;
            case "RM":
                return Position.RM;
            case "ST":
                return Position.ST;
            case "CF":
                return Position.CF;
            case "RW":
                return Position.RW;
            case "LW":
                return Position.LW;
            default:
                return null;
        }

    }
    private void setSalary(BigDecimal salary) {
        if(!(salary.doubleValue() >= 0)){
            throw new IllegalArgumentException("Invalid Player");
        }
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public int getNumber() {
        return number;
    }
    public Position getPosition() {
        return position;
    }
    public BigDecimal getSalary() {
        return salary;
    }
}
