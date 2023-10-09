package com.sgsavch.toolsrent.dtomapper;

import com.sgsavch.toolsrent.domain.Role;
import com.sgsavch.toolsrent.domain.User;
import com.sgsavch.toolsrent.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserDTOMapper {
    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    public static UserDTO fromUser(User user, Role role) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userDTO.setRoleName(role.getName());
        userDTO.setPermissions(role.getPermission());
        return userDTO;
    }

    public static User toUser(UserDTO userDto) {
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        return user;
    }
}
