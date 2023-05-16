package city.olooe.hello.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import city.olooe.hello.dto.ResponseDTO;
import city.olooe.hello.dto.TodoDTO;
import city.olooe.hello.model.TodoEntity;
import city.olooe.hello.service.TodoService;

@RestController
@RequestMapping("todo")
public class TodoController {

    @Autowired
    private TodoService service;

    // @GetMapping
    // public ResponseEntity<?> testTodo() {

    // List<String> list = new ArrayList<>();
    // list.add(service.testService());

    // ResponseDTO<String> response =
    // ResponseDTO.<String>builder().data(list).build();

    // return ResponseEntity.ok().body(response);
    // }

    @GetMapping
    public ResponseEntity<?> retrieveTodo() {
        String temporaryUserId = "temporary-user";

        // (1) 서비스 메서드의 retrieve 메서드를 사용해 Todo 리스트를 가져온다
        List<TodoEntity> entities = service.retrieve(temporaryUserId);

        // (2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다
        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        // (3) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화 -> ResponseDTO 반환
        return ResponseEntity.ok().body(ResponseDTO.<TodoDTO>builder().data(dtos).build());
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto) {
        try {
            String temporaryUserId = "temporary-user";

            // (1) TodoEntity로 변환
            TodoEntity entity = TodoDTO.todoEntity(dto);

            // (2) id를 null로 초기화. 생성 당시 id가 없어야 하기 때문
            entity.setId(null);

            // (3) 임시 유저 아이디 설정. -> 인증과 인가에서 나중에 다시 설정
            entity.setUserId(temporaryUserId);

            // (4) 서비스를 이용해 Todo 엔티티 생성
            List<TodoEntity> entities = service.create(entity);

            // (5) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환
            List<TodoDTO> dtos = entities.stream().map(e -> new TodoDTO(e)).collect(Collectors.toList());

            // (6) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화 -> ResponseDTO 반환
            return ResponseEntity.ok().body(ResponseDTO.<TodoDTO>builder().data(dtos).build());

        } catch (Exception e) {

            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();

            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateTodo(@RequestBody TodoDTO dto) {
        String temporaryUserId = "temporary-user";

        // (1) dto를 entity로 변환
        TodoEntity entity = TodoDTO.todoEntity(dto);

        // (2) 아이디를 temporaryUserId로 초기화. -> 나중에 인가에서 수정
        entity.setUserId(temporaryUserId);

        // (3) 서비스를 이용해 Todo 엔티티 업데이트
        List<TodoEntity> entities = service.update(entity);

        // (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환
        List<TodoDTO> dtos = entities.stream().map(e -> new TodoDTO(e)).collect(Collectors.toList());

        // (5) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화 -> ResponseDTO 반환
        return ResponseEntity.ok().body(ResponseDTO.<TodoDTO>builder().data(dtos).build());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteTodo(@RequestBody TodoDTO dto) {
        try {
            String temporaryUserId = "temporary-user";

            // (1) TodoEntity로 변환
            TodoEntity entity = TodoDTO.todoEntity(dto);

            // (2) 임시 유저 아이디 설정. -> 인증과 인가에서 나중에 다시 설정
            entity.setUserId(temporaryUserId);

            // (3) 서비스를 이용해 엔티티 삭제
            List<TodoEntity> entities = service.delete(entity);

            // (4) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환
            List<TodoDTO> dtos = entities.stream().map(e -> new TodoDTO(e)).collect(Collectors.toList());

            // (5) 변환된 TodoDTO 리스트를 이용해 ResponseDTO를 초기화 -> ResponseDTO 반환
            return ResponseEntity.ok().body(ResponseDTO.<TodoDTO>builder().data(dtos).build());

        } catch (Exception e) {

            // (6) 예외 발생 시 dto 대신 error 메시지를 넣어 리턴
            String error = e.getMessage();
            ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
            return ResponseEntity.badRequest().body(response);
        }
    }
}
