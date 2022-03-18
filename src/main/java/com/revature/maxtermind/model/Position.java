package com.revature.maxtermind.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Position implements Serializable {

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
