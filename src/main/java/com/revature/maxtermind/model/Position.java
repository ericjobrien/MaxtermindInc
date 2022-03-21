package com.revature.maxtermind.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Position implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column(precision = 8, scale = 2)
    private BigDecimal salary;
    @Column(name = "is_admin")
    private boolean admin;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "manager_id", foreignKey = @ForeignKey(name = "positionManager_fk"))
    private Employee manager;
    @OneToMany(mappedBy = "position", cascade = CascadeType.MERGE)
    @Fetch(value= FetchMode.SELECT)
    @JsonIgnoreProperties(value = "position")
    private Set<Application> applications = new HashSet<>();

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", admin=" + admin +
                ", manager={" + ((manager!=null)?
                   "id="+manager.getId()+", name="+manager.getFirstName()+" "+manager.getLastName()+
                           ", position="+manager.getPosition().getId():null) +'}'+
                '}';
    }
}
