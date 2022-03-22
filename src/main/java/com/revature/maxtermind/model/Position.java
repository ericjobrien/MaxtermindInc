package com.revature.maxtermind.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

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
    /*@OneToMany(mappedBy = "position", cascade = CascadeType.MERGE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Application> applications = new HashSet<>();*/
    @Transient
    private List<Application> applications = new ArrayList<>();

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
