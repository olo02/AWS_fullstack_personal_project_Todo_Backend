package city.olooe.hello.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import city.olooe.hello.dto.ResponseDTO;
import city.olooe.hello.dto.TestRequestBodyDTO;

@RequestMapping("test")
@RestController
public class TestController {

    @GetMapping
    public String testController() {
        return "hello world";
    }

    @GetMapping("/testGetMapping")
    public String testControllerWithPath() {
        return "hello wold testGetMapping";
    }

    @GetMapping("{id}")
    public String testContollerWithPathVariables(@PathVariable String id) {
        return "Hello World id : " + id;
    }

    @GetMapping("/requestParam")
    public String testControllerRequestParam(@RequestParam(required = false, defaultValue = "3") int id) {
        return "Hello World ID : " + id;
    }

    @GetMapping("requestBody")
    public String testControllerRequestBody(@RequestBody TestRequestBodyDTO testRequestBodyDTO) {
        return "Hello World id : " + testRequestBodyDTO.getId() + ", message : " + testRequestBodyDTO.getMessage();
    }

    // 반환 테스트
    @GetMapping("testResponseBody")
    public ResponseDTO<String> testControllerResponseBody() {
        List<String> list = new ArrayList<>();

        list.add("hello world I'm responseDTO");
        ResponseDTO<String> responseDTO = ResponseDTO.<String>builder().data(list).build();
        return responseDTO;
    }

    @GetMapping("testResponseEntity")
    public ResponseEntity<?> testControllerResponseEntity() {
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm ResponseEntity. And you got 400");
        ResponseDTO<String> response = ResponseDTO.<String>builder().data(list).build();
        return ResponseEntity.badRequest().body(response);
    }

}
