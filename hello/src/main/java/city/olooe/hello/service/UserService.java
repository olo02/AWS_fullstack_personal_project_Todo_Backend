package city.olooe.hello.service;

import city.olooe.hello.model.UserEntity;
import city.olooe.hello.persistence.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserEntity create(final UserEntity userEntity) {
        if(userEntity == null || userEntity.getUsername() == null) {
            throw new RuntimeException("Invalid arguments");
        }
        final String username = userEntity.getUsername();

        if(userRepository.existsByUsername(username)) {
            String msg = "User name already exist";
            log.warn(msg + "{}", username);
            throw new RuntimeException(msg);
        }
        return userRepository.save(userEntity);
    }

    public UserEntity getByCredentials(final String username, final String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
