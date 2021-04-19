package pl.waw.psychologmaja.therapistrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.waw.psychologmaja.therapistrelief.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
