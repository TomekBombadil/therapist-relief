package pl.waw.psychologmaja.therapistrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.waw.psychologmaja.therapistrelief.entity.Session;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

}
