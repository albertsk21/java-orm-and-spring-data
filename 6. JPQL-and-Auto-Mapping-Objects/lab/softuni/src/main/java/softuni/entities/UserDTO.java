package softuni.entities;

import softuni.contraints.Errors;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDTO {


    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
    private LocalDate created;
    private LocalDate modified;


    public UserDTO() {
    }

    public UserDTO(String username,String fullName, String email, String password, String confirmPassword) {
        this.username = username;
        this.fullName = fullName;
        this.setEmail(email);
        this.setPassword(password);
        this.setConfirmPassword(confirmPassword);
        this.created = LocalDate.now();
    }

    public void setUsername(String username) {



        this.username = username;

    }
    public void setEmail(String email) {

        if(!email.contains("@")){
            throw new IllegalArgumentException(Errors.INVALID_EMAIL);

        }

        this.email = email;

    }
    public void setPassword(String password) {
        this.password = password;


        if(!this.containOneDigit(password)){
            throw new IllegalArgumentException(Errors.PASSWORD_DOES_NOT_CONTAIN_A_NUMBER);

        }else if(!this.containOneUpperCase(password)){
            throw new IllegalArgumentException(Errors.PASSWORD_DOES_NOT_CONTAIN_A_UPPERCASE);

        }else if(!this.containOneLowerCase(password)){
            throw new IllegalArgumentException(Errors.PASSWORD_DOES_NOT_CONTAIN_A_LOWERCASE);
        }else if(password.length() < 6){
            throw new IllegalArgumentException(Errors.PASSWORD_DOES_NOT_HAVE_MORE_THAN_6SYMBOLS);

        }

        this.password = password;

    }
    public void setCreated(LocalDate created) {
        this.created = created;

    }
    public void modifiedDate() {
        this.modified = LocalDate.now();
    }
    public void setConfirmPassword(String confirmPassword) {

        this.confirmPassword = confirmPassword;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void checkConfirmPassword(String confirmPassword){
        if(!this.getPassword().equals(confirmPassword)){
            throw new IllegalArgumentException(Errors.PASSWORD_DO_NOT_MATCH);
        }
        this.confirmPassword = confirmPassword;
    }
    public String getFullName() {
        return fullName;
    }
    public String getConfirmPassword() {
        return confirmPassword;
    }
    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public LocalDate getCreated() {
        return created;
    }
    public LocalDate getModified() {
        return modified;
    }





    private boolean containOneUpperCase(String password){

        String regex = "[A-Z]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher =pattern.matcher(password);


        return matcher.find();
    }
    private boolean containOneLowerCase(String password){
        String regex = "[a-z]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher =pattern.matcher(password);


        return matcher.find();
    }
    private boolean containOneDigit(String password){
        String regex = "[0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher =pattern.matcher(password);


        return matcher.find();
    }


}
