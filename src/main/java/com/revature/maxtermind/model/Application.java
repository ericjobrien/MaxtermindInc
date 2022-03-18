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
        @UniqueConstraint(name = "applicationUniqueIndex", columnNames = {"position_id", "employee_id"})
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Application implements Serializable {

    @Id
    int id;

    @Column
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    Date date;

    @ManyToOne
    @JoinColumn(name = "position_id")
    Position position;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    Employee employee;

    @ManyToOne
    @JoinColumn(name = "status_id")
    Status status;

}
