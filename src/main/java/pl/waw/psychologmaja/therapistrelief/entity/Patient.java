package pl.waw.psychologmaja.therapistrelief.entity;

import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
    @Email(message = "{patient.email.invalid}")
    @Column(length = 50)
    private String email;
    @NotNull
    @Column(length = 20)
    private String phone;
    @NotNull
    @PESEL(message = "{patient.pesel.invalid}")
    @Column(length = 11)
    private String pesel;
    @Column(name="rodo_file")
    private byte[] rodoFile;
    @Column(length = 65535)
    private String description;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public byte[] getRodoFile() {
        return rodoFile;
    }

    public void setRodoFile(byte[] rodoFile) {
        this.rodoFile = rodoFile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @PrePersist
    public void prePersist(){
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate(){
        this.updated = LocalDateTime.now();
    }
}
