package br.com.kenzie.swagger_docs.modules.session;

import java.util.Arrays;

import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.kenzie.swagger_docs.modules.session.dtos.SessionCreate;
import br.com.kenzie.swagger_docs.modules.session.dtos.SessionReturn;
import br.com.kenzie.swagger_docs.modules.user.UserEntity;
import br.com.kenzie.swagger_docs.modules.user.UserRepository;
import br.com.kenzie.swagger_docs.modules.user.dtos.UserCreate;
import br.com.kenzie.swagger_docs.modules.user.dtos.UserReturn;
import br.com.kenzie.swagger_docs.security.JwtTokenProvider;

@Service
public class SessionService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ModelMapper modelMapper;

    public UserReturn register(UserCreate payload) throws Exception {
        var foundUser = userRepo.findByUsername(payload.getUsername());

        if (foundUser.isPresent()) {
            throw new BadRequestException("Username already exists");
        }

        var userBuilder = UserEntity.builder()
                .username(payload.getUsername())
                .password(encoder.encode(payload.getPassword()))
                .admin(payload.getAdmin());

        if (payload.getAdmin()) {
            var adminRole = Arrays.asList("ADMIN");
            var admin = userBuilder.roles(adminRole).build();

            var user = userRepo.save(admin);

            return modelMapper.map(user, UserReturn.class);
        }

        var commomRole = Arrays.asList("COMMON");
        var common = userBuilder.roles(commomRole).build();
        var user = userRepo.save(common);

        return modelMapper.map(user, UserReturn.class);
    }

    public SessionReturn login(@RequestBody SessionCreate payload) {
        var authToken = new UsernamePasswordAuthenticationToken(
                payload.getUsername(), payload.getPassword());

        var authentication = authenticationManager.authenticate(authToken);
        var token = new SessionReturn(jwtTokenProvider.createToken(authentication));

        return token;
    }
}
