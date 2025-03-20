package com.devteria.indentity_service.service;

import com.devteria.indentity_service.dto.request.UserCreationRequest;
import com.devteria.indentity_service.dto.request.UserUpdateRequest;
import com.devteria.indentity_service.dto.response.UserResponse;
import com.devteria.indentity_service.enity.User;
import com.devteria.indentity_service.exception.AppException;
import com.devteria.indentity_service.exception.ErrorCode;
import com.devteria.indentity_service.mapper.UserMapper;
import com.devteria.indentity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
     UserRepository userRepository;
     UserMapper userMapper;
    public User createUser(UserCreationRequest request){ // gọi xuống tầng Reponsitory để tạo user
        if(userRepository.existsByUsername(request.getUsername()))
            throw  new AppException(ErrorCode.USER_EXISTED);
        User user =  userMapper.toUser(request);
    return userRepository.save(user); // các layer chỉ duoc goi layer phia dưới không được vượt cấp
    }
    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("user not found!"));
        userMapper.updateUser(user, request);
        return  userMapper.toUserResponse(userRepository.save(user));
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
    public UserResponse getUsers(String id){
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(()-> new RuntimeException("user not found!")));
    }


}
