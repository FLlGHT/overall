package flight.overall.repository;

import flight.overall.entity.UserData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author FLIGHT
 * @creationDate 03.05.2022
 */
@Repository
public interface UserRepository extends CrudRepository<UserData, Long> {

    @Query("SELECT u " +
            " FROM UserData u" +
            " WHERE LOWER(u.username) =:nickname")
    Optional<UserData> findUserData(@Param("nickname") String username);
}
