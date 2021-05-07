package pl.waw.psychologmaja.therapistrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.waw.psychologmaja.therapistrelief.entity.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

    @Modifying
    @Query(value = "delete from users_authorities where users_id=:id", nativeQuery = true)
    void deleteAuthoritiesByUserId(@Param("id") long id);
}
