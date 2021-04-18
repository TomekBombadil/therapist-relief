package pl.waw.psychologmaja.therapistrelief.entity;

import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name="patients")
public class Patient {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min=2, message = "{patient.first_name.size.min}")
    @Column(name="first_name", length = 50)
    private String firstName;
    @NotNull
    @Size(min=2, message = "{patient.last_name.size.min}")
    @Column(name="last_name", length = 50)
    private String lastName;
    @NotNull
    @PESEL(message = "{patient.pesel.invalid}")
    @Column(length = 11)
    private String pesel;
    @Column(name="rodo_file")
    private byte[] rodoFile;
    private LocalDateTime created;
    private LocalDateTime updated;

    @PrePersist
    public void prePersist(){
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updated = LocalDateTime.now();
    }
}
