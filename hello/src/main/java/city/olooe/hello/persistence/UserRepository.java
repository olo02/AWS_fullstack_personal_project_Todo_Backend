package city.olooe.hello.persistence;

import city.olooe.hello.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findByUsername(String username);
    boolean existsByUsername(String username);
    UserEntity findByUsernameAndPassword(String username, String password);
}
