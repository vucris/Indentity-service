package com.devteria.indentity_service.controller;

import com.devteria.indentity_service.dto.request.ApiResponse;
import com.devteria.indentity_service.dto.request.UserCreationRequest;
import com.devteria.indentity_service.dto.request.UserUpdateRequest;
import com.devteria.indentity_service.dto.response.UserResponse;
import com.devteria.indentity_service.enity.User;
import com.devteria.indentity_service.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController { //gọi xuống layer service => user Service
     UserService userService;
        @PostMapping
        ApiResponse<User> createUser(@Valid @RequestBody  UserCreationRequest request){ // ở controller gọi xuống service  => service gọi xuống reponsitory
         ApiResponse<User> apiResponse = new ApiResponse<>();
         apiResponse.setResult(userService.createUser(request));
           return apiResponse;//created user
        }
        @GetMapping
        List<User> getUsers(){
            var authentication = SecurityContextHolder.getContext().getAuthentication();
            log.info("Username: {}", authentication.getName());
              authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
            return  userService.getUsers();
        }
        @GetMapping("/{userId}")
        UserResponse getUser(@PathVariable("userId") String userId){
            return userService.getUsers(userId);
        }
        @PutMapping("/{userId}")
        UserResponse updateUser (@PathVariable String userId, @RequestBody UserUpdateRequest request){
                return  userService.updateUser(userId, request);
        }
        @DeleteMapping("/{userId}")
        String deleteUser(@PathVariable("userId") String userId){
            userService.deleteUser(userId);
            return "users has been deleted";
        }
           @GetMapping("/Myinfo")
                ApiResponse<UserResponse> getMyinfo(){
            return ApiResponse.<UserResponse>builder()
                    .result(userService.getMyinfo())
                    .build();

        }
    }

