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

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name="Application", uniqueConstraints={
        @UniqueConstraint(name = "applicationUnique_index", columnNames = {"position_id", "employee_id"})
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private Date date;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "applicationPosition_fk"), name = "position_id")
    private Position position;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "applicationEmployee_fk"), name = "employee_id")
    private Employee employee;
    @Column
    private boolean recommended = false;
    @Column
    private boolean selected = false;
    @Column
    private boolean rejected = false;
    @Column
    private boolean approved = false;
    
}
