package city.olooe.hello.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import city.olooe.hello.model.TodoEntity;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

    // @Query -> 커스텀으로 활용할 때 사용
    List<TodoEntity> findByUserId(String userId);
}
