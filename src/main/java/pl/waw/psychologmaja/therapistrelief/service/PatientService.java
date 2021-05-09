package pl.waw.psychologmaja.therapistrelief.service;

import org.springframework.stereotype.Service;
import pl.waw.psychologmaja.therapistrelief.entity.Patient;
import pl.waw.psychologmaja.therapistrelief.repository.PatientRepository;
import pl.waw.psychologmaja.therapistrelief.repository.SessionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<Patient> returnAllWithPayments() {
        List<Patient> patients = patientRepository.findAllWithSessions();
        patients.forEach(patient -> {
            patient.setPayment(patient.getSessions()
                    .stream().map(session -> session.getPaymentDiff())
                    .reduce(0.0, (subtotal, element) -> subtotal + element));
        });
        Set<Patient> patientsSet = patients.stream().collect(Collectors.toSet());
        return patientsSet;
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
