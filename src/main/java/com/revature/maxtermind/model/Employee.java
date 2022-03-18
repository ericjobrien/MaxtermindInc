package com.revature.maxtermind.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(unique = true, nullable = false)
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
    private Employee manager;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(name = "current_position")
    private Position position;
    @OneToMany(mappedBy = "toEmployee", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Notification> notifications;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Set<Application> applications;
}
