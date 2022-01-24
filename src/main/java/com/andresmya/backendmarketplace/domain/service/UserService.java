package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.Role;
import com.andresmya.backendmarketplace.domain.User;
import com.andresmya.backendmarketplace.domain.dto.request.create.CreateUserRequest;
import com.andresmya.backendmarketplace.domain.mapper.IUserMapper;
import com.andresmya.backendmarketplace.domain.repository.IUserRepository;
import com.andresmya.backendmarketplace.domain.exception.InvalidArgumentException;
import com.andresmya.backendmarketplace.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private IUserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User getUserById(Integer id) throws NotFoundException {
        return userRepository.getUserById(id).orElseThrow(() -> new NotFoundException("User ID " + id));
    }

    public void deleteUserById(Integer userId) throws NotFoundException {
        User user = getOptionalUserById(userId).orElseThrow(() -> new NotFoundException("User ID " + userId));
        userRepository.deleteUserById(userId);
    }

    public User createUser(CreateUserRequest request) throws Exception {
        checkIfArgumentsAreValid(request.getEmail(), request.getPassword());
        User user = fromCreateUserRequestToUser(request);
        return userRepository.createUser(user);
    }

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.getAllUsers(pageable);
    }

    protected User updateUser(User user) {
        return userRepository.updateUser(user);
    }

    protected Optional<User> getOptionalUserById(Integer id) {
        return userRepository.getUserById(id);
    }

    protected Optional<User> getUserByEmail(String email){
        return userRepository.getUserByEmail(email);
    }

    protected String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

    protected void deleteUser(User user){
        userRepository.deleteUser(user);
    }

    protected boolean passwordIsStrong(String password){
        String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(password);
        return m.find();
    }

    private User fromCreateUserRequestToUser(CreateUserRequest request) throws Exception {
        Role role = roleService.getRoleById(request.getRolId()).orElseThrow(() -> new InvalidArgumentException("Role ID " + request.getRolId()));
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return userMapper.toUser(request, role);
    }

    private boolean alreadyExistsByEmail(String email){
        return userRepository.existsByEmail(email);
    }

    private void checkIfArgumentsAreValid(String email, String password) throws InvalidArgumentException {
        String invalidArgumentMessage;
        if (alreadyExistsByEmail(email)) {
            invalidArgumentMessage = "Email " + email + " already used";
        } else if (passwordIsStrong(password)) {
            invalidArgumentMessage = "Weak password";
        } else {
            return;
        }
        throw new InvalidArgumentException(invalidArgumentMessage);
    }

}
