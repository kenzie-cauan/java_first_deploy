package br.com.kenzie.swagger_docs.modules.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kenzie.swagger_docs.modules.user.dtos.UserReturn;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<List<UserReturn>> read() {
        return ResponseEntity.status(200).body(userService.read());
    }
}
