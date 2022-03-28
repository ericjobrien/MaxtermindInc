package com.revature.maxtermind.model;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Employee", uniqueConstraints={
        @UniqueConstraint(name = "employeeUnique_index", columnNames = "email")
})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(nullable = false)
    private String email;
    @Column(name = "phone_number")
    private long phoneNumber;
    @Column
    private String photo;
    @Column(name = "start_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE})
    @JoinColumn(foreignKey = @ForeignKey(name = "employeePosition_fk"), name = "current_position")
    private Position position;
    @Transient
    private List<Notification> notifications = new ArrayList<>();
    /*@OneToMany(mappedBy = "employee", cascade = CascadeType.MERGE)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Application> applications = new HashSet<>();*/
    @Transient
    private List<Application> applications = new ArrayList<>();

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", startDate=" + startDate +
                ", position={id=" + position.getId() +", name="+position.getName()+", isAdmin="+position.isAdmin()+
                ", manager="+((position.getManager()!=null)?position.getManager().getId():null)+"} "+
                '}';
    }
}
