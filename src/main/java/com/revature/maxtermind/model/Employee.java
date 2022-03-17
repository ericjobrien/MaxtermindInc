package com.revature.maxtermind.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "Employee")
public class Employee {

    @Id
    int id;
    @Column
    String password;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "last_name")
    String lastName;
    @Column(unique = true)
    String email;
    @Column(name = "phone_number")
    int phoneNumber;
    @Column(name = "start_date")
    Date startDate;
    @Column(name = "current_position")
    @ManyToOne
    @JoinColumn(name = "position_id")
    Position currentPosition;
    @OneToMany(mappedBy = "employee")
    Set<Application> applications;
    @OneToMany(mappedBy = "toEmployee")
    Set<Notification> notifications;
    @ManyToOne
    @JoinColumn(name = "manager_id")
    Employee manager;



}
