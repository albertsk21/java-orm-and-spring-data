package softuni.entities;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    void updateUserFromDto(UserDTO dto, @MappingTarget User entity);
}
