package com.devteria.indentity_service.service;

import com.devteria.indentity_service.dto.request.UserCreationRequest;
import com.devteria.indentity_service.dto.request.UserUpdateRequest;
import com.devteria.indentity_service.dto.response.UserResponse;
import com.devteria.indentity_service.enity.User;
import com.devteria.indentity_service.enums.Role;
import com.devteria.indentity_service.exception.AppException;
import com.devteria.indentity_service.exception.ErrorCode;
import com.devteria.indentity_service.mapper.UserMapper;
import com.devteria.indentity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j // de log debug
public class UserService {
     UserRepository userRepository;
     UserMapper userMapper;
     PasswordEncoder passwordEncoder;
    public User createUser(UserCreationRequest request){ // gọi xuống tầng Reponsitory để tạo user
        if(userRepository.existsByUsername(request.getUsername()))
            throw  new AppException(ErrorCode.USER_EXISTED);

        User user =  userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword())); // mã hóa bcrypt

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

    return userRepository.save(user); // các layer chỉ duoc goi layer phia dưới không được vượt cấp
    }
    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("user not found!"));
        userMapper.updateUser(user, request);
        return  userMapper.toUserResponse(userRepository.save(user));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public List<User> getUsers(){
        log.info("vao duoc  user k");
        return userRepository.findAll();
    }
    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
    @PostAuthorize("returnObject.username == authentication.name")// lấy user theo token dăng nhap
    public UserResponse getUsers(String id){
        log.info("vao duoc  user by id");
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found!")));
    }
    public UserResponse getMyinfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user  = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_EXISTED));
        return userMapper.toUserResponse(user);
    }

}
