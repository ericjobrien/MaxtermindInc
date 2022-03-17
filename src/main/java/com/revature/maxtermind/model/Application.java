package com.revature.maxtermind.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Application {

    @Id
    int id;
    @Column
    Date date;
    @OneToMany
    @JoinColumn(name = "position_id")
    Position position;
    @OneToMany
    @JoinColumn(name = "employee_id")
    Employee employee;
    @OneToMany
    @JoinColumn(name = "status_id")
    Status status;

}
