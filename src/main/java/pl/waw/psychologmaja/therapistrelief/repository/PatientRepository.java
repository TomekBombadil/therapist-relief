package pl.waw.psychologmaja.therapistrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.waw.psychologmaja.therapistrelief.entity.Patient;

import java.util.List;


public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = "select p from Patient p left join fetch p.sessions")
    List<Patient> findAllWithSessions();
}
