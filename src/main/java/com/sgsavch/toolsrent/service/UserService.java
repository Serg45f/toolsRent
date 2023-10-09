package com.sgsavch.toolsrent.service;

import com.sgsavch.toolsrent.domain.User;
import com.sgsavch.toolsrent.dto.UserDTO;

public interface UserService {
    UserDTO createUser(User user);
    UserDTO getUserByEmail(String email);

    void sendVerificationCode(UserDTO user);
}
