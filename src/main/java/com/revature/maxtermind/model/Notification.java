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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Notification implements Serializable {

    @Id
    int id;

    @ManyToOne
    @JoinColumn(name = "from_employee_id")
    Employee fromEmployee;

    @ManyToOne
    @JoinColumn(name = "to_employee_id")
    Employee toEmployee;

    @Column
    String description;

    @Column
    boolean unread;

    @Column
    @DateTimeFormat(pattern = "MM-dd-yyyy")
    Date date;

    @ManyToOne
    @JoinColumn(name = "action_id")
    Action action;
}
