package city.olooe.hello.controller;

import city.olooe.hello.dto.ResponseDTO;
import city.olooe.hello.dto.UserDTO;
import city.olooe.hello.model.UserEntity;
import city.olooe.hello.security.TokenProvider;
import city.olooe.hello.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("auth")
public class UserController {

    // Client ID 933ca5226344ef4de70f
    @Autowired
    private UserService userService;
    @Autowired
    private TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO) {
        try {
            if (userDTO == null || userDTO.getPassword() == null) {
                throw new RuntimeException("Invalid password value");
            }
            // 요청을 이용해 저장할 유저 만들기
            UserEntity userEntity = UserEntity.builder()
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword()))
                    .build();
            // 서비스를 이용해 리포지터리에 유저 저장
            UserEntity registeredUser = userService.create(userEntity);
            UserDTO responseUserDTO = UserDTO.builder()
                    .id(registeredUser.getId())
                    .username(registeredUser.getUsername())
                    .build();
            // 반환값
            return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception e) {
            // 유저 정보는 항상 하나이므로 리스트로 만들어야 하는 ResponseDTO를 사용하지 않고 그냥 UserDTO 리턴
            log.info(e.getMessage());
            return ResponseEntity.badRequest()
                    .body(ResponseDTO.builder().error(e.getMessage()).build());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO) {
        UserEntity userEntity = userService.getByCredentials(userDTO.getUsername(), userDTO.getPassword(), passwordEncoder);

        if(userEntity != null){
            // 토큰 생성
            final String token = tokenProvider.create(userEntity);
            final UserDTO responseUserDTO =
                    UserDTO.builder()
                            .username(userEntity.getUsername())
                            .id(userEntity.getId())
                            .token(token)
                            .build();
            return ResponseEntity.ok().body(responseUserDTO);
        }
        else {
            ResponseDTO<Object> responseDTO = ResponseDTO.builder().error("Login failed").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
