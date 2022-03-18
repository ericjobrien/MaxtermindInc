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
    int id;

    @Column(nullable = false)
    String password;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(unique = true, nullable = false)
    String email;

    @Column(name = "phone_number")
    int phoneNumber;

    @Column(name = "start_date")
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    Date startDate;

    @Column
    String photo;

    @ManyToOne
    @JoinColumn(name = "current_position")
    Position position;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    Employee manager;

    @OneToMany(mappedBy = "employee")
    Set<Application> applications = new HashSet<>();

    @OneToMany(mappedBy = "toEmployee")
    Set<Notification> notifications= new HashSet<>();


}
