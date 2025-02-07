package lt.techin.dto;

import lt.techin.model.User;

import java.util.List;

public class UserMapper {
    public static List<UserDTO> toUserDTOList(List<User> userList) {
        return userList.stream()
                .map(user -> new UserDTO(user.getUsername(), user.getPassword(), RoleMapper.toRoleDTOList(user)))
                .toList();
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();

        user.setUsername(userDTO.username());
        user.setPassword(userDTO.password());
        user.setRoles(RoleMapper.toRoleListFromDTO(userDTO.roles()));

        return user;
    }

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getUsername(), user.getPassword(), RoleMapper.toRoleDTOList(user));
    }
}
