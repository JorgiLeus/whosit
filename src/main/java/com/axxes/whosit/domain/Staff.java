package com.axxes.whosit.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "staff")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Staff {

    @Id
    private String id;

    @Column
    @JsonProperty("givenName")
    private String firstName;

    @Column
    @JsonProperty("surname")
    private String lastName;

    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Staff() {
        this.gender = Gender.MALE;
    }

    public Staff(String id, String firstName, String lastName, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
