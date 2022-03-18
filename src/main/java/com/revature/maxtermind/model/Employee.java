package com.revature.maxtermind.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column
    private String email;
    @Column(name = "phone_number")
    private long phoneNumber;
    @Column
    private String photo;
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private Date startDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "manager_id")
    private Employee managerId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "current_role")
    private Employee currentRole;
    @OneToMany(mappedBy = "id", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Notification> notifications;
    @OneToMany(mappedBy = "id", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Application> applications;
}
