package pl.waw.psychologmaja.therapistrelief.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDate date;
    @NotNull
    private LocalTime time;
    @Column(columnDefinition = "TEXT")
    private String notes;
    @NotNull
    @Column(name = "payment_due")
    private double paymentDue;
    @Column(name = "payment_actual")
    private double paymentActual;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "patients_sessions"
            , joinColumns = @JoinColumn(name = "sessions_id")
            , inverseJoinColumns = @JoinColumn(name="patients_id"))
    private List<Patient> patients = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name="users_id")
    private User user;
    private LocalDateTime created;
    private LocalDateTime updated;

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDateTime getDateTime(){
        return LocalDateTime.of(date, time);
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getPaymentDue() {
        return paymentDue;
    }

    public void setPaymentDue(double paymentDue) {
        this.paymentDue = paymentDue;
    }

    public double getPaymentActual() {
        return paymentActual;
    }

    public void setPaymentActual(double paymentActual) {
        this.paymentActual = paymentActual;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public String getPatientsNames() {
        return String.join(", ", patients.stream().
                map(Patient::getFullName).collect(Collectors.toSet()));
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public double getPaymentDiff() {
        return paymentDue - paymentActual;
    }

    public boolean isPaymentOk() {
        return getPaymentDiff() <= 0;
    }

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updated = LocalDateTime.now();
    }

}
