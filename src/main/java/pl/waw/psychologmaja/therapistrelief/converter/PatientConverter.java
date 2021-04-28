package pl.waw.psychologmaja.therapistrelief.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.waw.psychologmaja.therapistrelief.entity.Patient;
import pl.waw.psychologmaja.therapistrelief.repository.PatientRepository;

public class PatientConverter implements Converter<String, Patient> {

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient convert(String s) {
        return patientRepository.findById(Long.parseLong(s)).get();
    }
}
