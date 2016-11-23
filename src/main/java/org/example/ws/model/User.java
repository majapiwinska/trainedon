package org.example.ws.model;

import com.sun.istack.internal.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by maja on 19.10.16.
 */

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany
    @JoinTable(
            name = "user_training",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "training_id")})
    @Nullable
    private List<Training> trainingList = new ArrayList<Training>();

    public enum Role {
        USER, ADMIN
    }
    public User(){};

    public User(String firstName, String lastName, String email, String password, Role role, List<Training> trainingList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.trainingList = trainingList;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Training> getTrainingList() {
        return trainingList;
    }

    public void setTrainingList(List<Training> trainingList) {
        this.trainingList = trainingList;
    }
}

/*

create )
*/
