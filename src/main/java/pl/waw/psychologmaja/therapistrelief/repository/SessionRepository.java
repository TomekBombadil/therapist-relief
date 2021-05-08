package pl.waw.psychologmaja.therapistrelief.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.waw.psychologmaja.therapistrelief.entity.Session;
import pl.waw.psychologmaja.therapistrelief.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    @EntityGraph(attributePaths = {"user", "user.authorities"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("select s from Session s left join fetch s.user u left join fetch u.authorities where s.user=:user")
    List<Session> findAllByUserWithUserAndAuthorities(@Param("user") User user);

    @EntityGraph(attributePaths = {"user", "user.authorities"}, type= EntityGraph.EntityGraphType.FETCH)
    @Query("select s from Session s left join fetch s.user u left join fetch u.authorities")
    List<Session> findAllWithUserAndAuthorities();

    @Modifying
    @Query(value = "update sessions set users_id=null where users_id=:id", nativeQuery = true)
    void setSessionUserToNull(@Param("id") long id);

    @Modifying
    @Query(value = "delete from patients_sessions where patients_id=:id", nativeQuery = true)
    void setSessionPatientsToNull(@Param("id") long id);

    @Query(value = "select * from sessions where users_id=:users_id and timestamp(date, time)>=:datetime", nativeQuery = true)
    List<Session> findAllByUserAndDateTime(@Param("users_id") long users_id, @Param("datetime") LocalDateTime datetime);

}
