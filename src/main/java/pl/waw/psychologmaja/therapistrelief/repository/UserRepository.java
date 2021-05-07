package pl.waw.psychologmaja.therapistrelief.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.waw.psychologmaja.therapistrelief.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u left join fetch u.authorities where u.id=:id")
    Optional<User> findByIdWithAuthorities(@Param("id") long id);

    @EntityGraph(attributePaths = "authorities")
    @Query("select u from User u left join fetch u.authorities where u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("select u from User u left join fetch u.authorities")
    Set<User> findAllWithAuthorities();


    Optional<User> findByUsername(@Param("username") String username);

}
