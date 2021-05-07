package pl.waw.psychologmaja.therapistrelief.service;

import org.springframework.stereotype.Service;
import pl.waw.psychologmaja.therapistrelief.entity.Patient;
import pl.waw.psychologmaja.therapistrelief.repository.PatientRepository;
import pl.waw.psychologmaja.therapistrelief.repository.SessionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService {

    private PatientRepository patientRepository;
    private SessionRepository sessionRepository;

    public PatientService(PatientRepository patientRepository, SessionRepository sessionRepository) {
        this.patientRepository = patientRepository;
        this.sessionRepository = sessionRepository;
    }

    public List<Patient> returnAll() {
        return patientRepository.findAll();
    }

    public Optional<Patient> read(long id) {
        return patientRepository.findById(id);
    }

    public void save(Patient patient) {
        patientRepository.save(patient);
    }

    public void delete(Patient patient) {
        sessionRepository.setSessionPatientsToNull(patient.getId());
        patientRepository.delete(patient);
    }

}
