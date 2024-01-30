package br.com.kenzie.swagger_docs.modules.user;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.kenzie.swagger_docs.modules.user.dtos.UserReturn;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserReturn> read() {
        return userRepo.findAll().stream()
                .map((u) -> modelMapper.map(u, UserReturn.class))
                .toList();
    }
}
