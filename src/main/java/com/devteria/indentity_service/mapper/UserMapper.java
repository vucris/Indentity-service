package com.devteria.indentity_service.mapper;

import com.devteria.indentity_service.dto.request.UserCreationRequest;
import com.devteria.indentity_service.dto.request.UserUpdateRequest;
import com.devteria.indentity_service.dto.response.UserResponse;
import com.devteria.indentity_service.enity.User;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    @Mapping(source = "firstName", target = "lastName") // máp để có the trung nhau
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request); // kế thừa cho update
}
