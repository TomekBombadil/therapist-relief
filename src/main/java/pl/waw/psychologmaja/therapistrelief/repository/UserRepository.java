package pl.waw.psychologmaja.therapistrelief.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.waw.psychologmaja.therapistrelief.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u left join fetch u.authorities where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("select u from User u left join fetch u.authorities")
    List<User> findAllWithAuthorities();

    Optional<User> findByUsername(@Param("username") String username);
}
