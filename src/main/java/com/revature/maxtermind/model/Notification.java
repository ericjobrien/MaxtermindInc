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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String description;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "notificationFromEmployee_fk"), name = "from_employee_id")
    private Employee fromEmployee;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "notificationToEmployee_fk"), name = "to_employee_id")
    private Employee toEmployee;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "notificationPosition_fk"), name = "position_id")
    private Position position;
    @Column
    private boolean unread;
    @Column
    @DateTimeFormat(pattern = "mm-dd-yyyy")
    private Date date;
    @Enumerated(EnumType.STRING)
    private Action action;
}
