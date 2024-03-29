package com.sgsavch.toolsrent.service.implementation;

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

    @Override
    public UserDTO getUserByEmail(String email) {
        return UserDTOMapper.fromUser(userRepository.getUserByEmail(email));
    }

    @Override
    public void sendVerificationCode(UserDTO user) {
        userRepository.sendFerificationCode(user);
    }
}
