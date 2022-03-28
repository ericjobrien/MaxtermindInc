package com.revature.maxtermind.model;

import com.fasterxml.jackson.annotation.*;
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
@Table(name="Application", uniqueConstraints={
        @UniqueConstraint(name = "applicationUnique_index", columnNames = {"position_id", "employee_id"})
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Application implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "applicationPosition_fk"), name = "position_id")
    @JsonIgnoreProperties(value = "applications")
    private Position position;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "applicationEmployee_fk"), name = "employee_id")
    @JsonIgnoreProperties(value = "applications")
    private Employee employee;
    @Column
    private boolean recommended = false;
    @Column
    private boolean selected = false;
    @Column
    private boolean rejected = false;
    @Column
    private boolean approved = false;

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", date=" + date +
                ", position={id=" + position.getId()+", name="+position.getName()+", isAdmin="+position.isAdmin() +
                            ", manager="+((position.getManager()!=null)?position.getManager().getId():null)+'}'+
                ", employee={id=" + employee.getId()+", name="+employee.getFirstName()+" "+employee.getLastName()+
                            ", position="+employee.getPosition().getId()+'}'+
                ", recommended=" + recommended +
                ", selected=" + selected +
                ", rejected=" + rejected +
                ", approved=" + approved +
                '}';
    }
}
