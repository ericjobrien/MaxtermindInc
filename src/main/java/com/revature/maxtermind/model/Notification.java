package com.revature.maxtermind.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Notification {

    @Id
    int id;
    @OneToMany
    @JoinColumn(name = "from_employee_id")
    Employee fromEmployee;
    @OneToMany
    @JoinColumn(name = "to_employee_id")
    Employee toEmployee;
    @Column
    String description;
    @OneToMany
    @JoinColumn(name = "action_id")
    Action action;
}
