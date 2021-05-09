package pl.waw.psychologmaja.therapistrelief.entity;

import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    @Column(name="rodo_file", columnDefinition = "LONGBLOB")
    @Lob
    private byte[] rodoFile;
    @Column(name="rodo_file_name")
    private String rodoFileName;
    @Column(columnDefinition = "TEXT")
    private String description;
    @ManyToMany(mappedBy = "patients")
    private List<Session> sessions = new ArrayList<>();
    @Transient
    private double payment;
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

    public String getFullName(){
        return this.firstName + " " + this.lastName;
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

    public String getRodoFileName() {
        return rodoFileName;
    }

    public void setRodoFileName(String rodoFileName) {
        this.rodoFileName = rodoFileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
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
