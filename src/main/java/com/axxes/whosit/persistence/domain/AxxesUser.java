package com.axxes.whosit.persistence.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "axxesuser")
public class AxxesUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, mappedBy = "axxesUser")
    private List<Score> scores;

    public AxxesUser() {
    }

    public AxxesUser(long id, Staff staff, String email, String password) {
        this.id = id;
        this.staff = staff;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
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
}
