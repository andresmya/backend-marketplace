package com.andresmya.backendmarketplace.persistence;

import com.andresmya.backendmarketplace.domain.User;
import com.andresmya.backendmarketplace.domain.repository.IUserRepository;
import com.andresmya.backendmarketplace.persistence.entity.UserEntity;
import com.andresmya.backendmarketplace.persistence.jpa.repository.IUserJpaRepository;
import com.andresmya.backendmarketplace.persistence.mapper.IUserPersistenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public class UserPersistence implements IUserRepository {

    @Autowired
    private IUserJpaRepository userJpaRepository;

    @Autowired
    private IUserPersistenceMapper userPersistenceMapper;

    @Override
    public User createUser(User user) {
        UserEntity newUserEntity = userJpaRepository.save(userPersistenceMapper.toUserEntity(user));
        newUserEntity.setCreatedAt(new Date());
        newUserEntity.setUpdatedAt(new Date());
        return userPersistenceMapper.toUser(newUserEntity);
    }

    @Override
    public User updateUser(User user) {
        UserEntity newUserEntity = userJpaRepository.save(userPersistenceMapper.toUserEntity(user));
        return userPersistenceMapper.toUser(newUserEntity);
    }

    @Override
    public Optional<User> getUserById(Integer id) {
        return userJpaRepository.findById(id).map(userEntity -> userPersistenceMapper.toUser(userEntity));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(userEntity -> userPersistenceMapper.toUser(userEntity));
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userJpaRepository.findAll(pageable).map(userEntity -> userPersistenceMapper.toUser(userEntity));
    }

    @Override
    public void deleteUserById(Integer id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public void deleteUser(User user) {
        userJpaRepository.delete(userPersistenceMapper.toUserEntity(user));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }
}
