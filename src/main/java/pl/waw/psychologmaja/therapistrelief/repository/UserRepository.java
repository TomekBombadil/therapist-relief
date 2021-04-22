package pl.waw.psychologmaja.therapistrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.waw.psychologmaja.therapistrelief.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
