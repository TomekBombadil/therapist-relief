package pl.waw.psychologmaja.therapistrelief.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private LocalDateTime datetime;
    @Column(columnDefinition = "TEXT")
    private String notes;
    @NotNull
    @Column(name = "payment_due")
    private double paymentDue;
    @Column(name = "payment_actual")
    private double paymentActual;
    @ManyToMany(mappedBy = "sessions")
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

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
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
