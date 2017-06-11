package ua.mjd.testtask.model;

import com.sun.corba.se.impl.protocol.AddressingDispositionException;
import com.sun.deploy.security.ValidationState;
import ua.mjd.testtask.enums.UserType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static ua.mjd.testtask.enums.UserType.ADMIN;
import static ua.mjd.testtask.enums.UserType.USER;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String password;
    private UserType userType;

    public Account() {
    }

    public Account(String name, String password) {
        this.name = name;
        this.password = password;
        this.userType = USER;
        String s = name.substring(0,1).toLowerCase();
        if (name.substring(0,1).toLowerCase().equals("a"))
            this.userType = ADMIN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
