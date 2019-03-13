package models.requests;

import models.ModelBase;
import play.data.validation.Constraints.Required;

public class UserLogin extends ModelBase {

    @Required
    private String email;

    @Required
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
