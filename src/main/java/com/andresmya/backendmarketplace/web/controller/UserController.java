package com.andresmya.backendmarketplace.web.controller;

import com.andresmya.backendmarketplace.domain.User;
import com.andresmya.backendmarketplace.domain.dto.request.CreateUserRequest;
import com.andresmya.backendmarketplace.domain.service.RoleService;
import com.andresmya.backendmarketplace.domain.service.UserService;
import com.andresmya.backendmarketplace.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize(RoleService.HAS_ROLE_ADMIN)
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<Page<User>> getUsers(@RequestParam("page") int page, @RequestParam("size")int size){
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<Page<User>>(userService.getUsers(pageable), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) throws Exception {
        return new ResponseEntity<User>(userService.createUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Integer userId) throws NotFoundException {
        return new ResponseEntity<User>(userService.    getUserById(userId), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId") Integer userId) throws NotFoundException {
        userService.deleteUserById(userId);
    }
}
