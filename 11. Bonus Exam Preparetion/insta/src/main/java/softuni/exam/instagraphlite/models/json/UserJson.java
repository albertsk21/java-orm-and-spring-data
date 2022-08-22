package softuni.exam.instagraphlite.models.json;

import com.google.gson.annotations.Expose;

public class UserJson {

    @Expose
    private String username;
    @Expose
    private String password;
    @Expose
    private String profilePicture;

    public UserJson() {
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getProfilePicture() {
        return profilePicture;
    }


}
