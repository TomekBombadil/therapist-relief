package pl.waw.psychologmaja.therapistrelief.service;

import org.springframework.stereotype.Service;
import pl.waw.psychologmaja.therapistrelief.entity.Patient;
import pl.waw.psychologmaja.therapistrelief.repository.PatientRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Optional<Patient> read(long id){
        return patientRepository.findById(id);
    }

    public void save(Patient patient) {
        patientRepository.save(patient);
    }
}
