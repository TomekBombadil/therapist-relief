package pl.waw.psychologmaja.therapistrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.waw.psychologmaja.therapistrelief.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
