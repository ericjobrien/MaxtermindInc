package com.revature.maxtermind.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
public class Position {

    @Id
    int id;
    @Column
    String name;
    @Column
    BigDecimal salary;
    @Column(name = "is_admin")
    boolean isAdmin;
    @OneToMany(mappedBy = "position")
    Set<Application> applications;
}
