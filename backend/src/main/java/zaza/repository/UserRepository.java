package zaza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zaza.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
