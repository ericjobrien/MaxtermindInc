package com.revature.maxtermind.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private BigDecimal salary;
    @Column(name = "is_admin")
    private boolean isAdmin;
    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Employee> employees;
    @OneToMany(mappedBy = "id", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Application> applications;
}
