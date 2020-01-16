package pl.edu.uph.tpsi.mappers;

import org.mapstruct.Mapper;
import pl.edu.uph.tpsi.dto.UserDTO;
import pl.edu.uph.tpsi.models.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User DTOtoUser(UserDTO userDTO);

    UserDTO UserToDTO(User user);
}