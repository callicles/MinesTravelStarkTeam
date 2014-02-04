package org.springframework.samples.travel.domain.model.user;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A user who can book hotels.
 */
@Document(collection = "users")
public class User implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5302880772865047290L;

    @Id
    private String id;
    @Indexed(unique = true)
    private String username;

    private String password;

    private String name;

    private List<String> roles;

    public User() {
    }

    @PersistenceConstructor
    public User(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User(" + username + ")";
    }
}
