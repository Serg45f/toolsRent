package com.sgsavch.toolsrent.repository.implementation;

import com.sgsavch.toolsrent.domain.User;
import com.sgsavch.toolsrent.dto.UserDTO;
import com.sgsavch.toolsrent.dtomapper.UserDTOMapper;
import com.sgsavch.toolsrent.repository.UserRepository;
import com.sgsavch.toolsrent.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository<User> userRepository;
    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(userRepository.create(user));
    }
}
