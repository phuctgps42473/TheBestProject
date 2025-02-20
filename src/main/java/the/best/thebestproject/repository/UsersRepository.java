package the.best.thebestproject.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import the.best.thebestproject.model.Users;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, String> {

    Optional<Users> findUserByEmail(String email);

    Optional<Users> findByEmail(String email);

    boolean existsByEmail(String s);
}
