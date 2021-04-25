package pl.waw.psychologmaja.therapistrelief.service;

import org.springframework.stereotype.Service;
import pl.waw.psychologmaja.therapistrelief.entity.Patient;
import pl.waw.psychologmaja.therapistrelief.entity.Session;
import pl.waw.psychologmaja.therapistrelief.repository.SessionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SessionService {

    private SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<Session> returnAll() {
        return sessionRepository.findAll();
    }

    public Optional<Session> read(long id) {
        return sessionRepository.findById(id);
    }

    public void save(Session session) {
        sessionRepository.save(session);
    }

    public void delete(Session session){
        sessionRepository.delete(session);
    }
}
