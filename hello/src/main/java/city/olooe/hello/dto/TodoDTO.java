package city.olooe.hello.dto;

import city.olooe.hello.model.TodoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    private String id;
    private String title;
    private boolean done;

    // entity를 dto로
    public TodoDTO(final TodoEntity entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.done = entity.isDone();
    }

    // dto를 entity로
    public static TodoEntity todoEntity(final TodoDTO dto) {
        return TodoEntity.builder().id(dto.id).title(dto.title).done(dto.done).build();
    }

}