package br.com.kenzie.swagger_docs.modules.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kenzie.swagger_docs.modules.session.dtos.SessionCreate;
import br.com.kenzie.swagger_docs.modules.session.dtos.SessionReturn;
import br.com.kenzie.swagger_docs.modules.user.dtos.UserCreate;
import br.com.kenzie.swagger_docs.modules.user.dtos.UserReturn;

@RestController
@RequestMapping("/api/auth")
public class SessionController {
    @Autowired
    private SessionService sessionService;

    @PostMapping("/register")
    public ResponseEntity<UserReturn> register(@RequestBody UserCreate payload)
            throws Exception {
        var user = sessionService.register(payload);
        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<SessionReturn> login(@RequestBody SessionCreate payload) {
        var token = sessionService.login(payload);
        return ResponseEntity.status(200).body(token);
    }
}
