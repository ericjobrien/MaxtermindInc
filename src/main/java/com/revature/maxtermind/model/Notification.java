package com.revature.maxtermind.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
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
    @JsonIgnoreProperties(value = "notifications")
    private Employee fromEmployee;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "notificationToEmployee_fk"), name = "to_employee_id")
    @JsonIgnoreProperties(value = "notifications")
    private Employee toEmployee;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "notificationPosition_fk"), name = "position_id")
    private Position position;
    @Column
    private boolean unread;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private Action action;

    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", fromEmployee={id=" + fromEmployee.getId()+", name="+fromEmployee.getFirstName()+
                                " "+fromEmployee.getLastName() + ", position="+fromEmployee.getPosition().getId()+ '}' +
                ", toEmployee={id=" + toEmployee.getId()+", name="+toEmployee.getFirstName()+
                              " "+toEmployee.getLastName() + ", position="+toEmployee.getPosition().getId()+ '}' +
                ", position={id=" + position.getId()+", name="+position.getName()+", isAdmin="+position.isAdmin()+
                            ", manager="+((position.getManager()!=null)?position.getManager().getId():null)+ '}' +
                ", unread=" + unread +
                ", date=" + date +
                ", action=" + action.name() +
                '}';
    }
}
