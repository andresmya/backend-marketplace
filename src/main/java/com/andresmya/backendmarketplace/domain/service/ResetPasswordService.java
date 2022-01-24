package com.andresmya.backendmarketplace.domain.service;

import com.andresmya.backendmarketplace.domain.User;
import com.andresmya.backendmarketplace.domain.dto.request.ResetPasswordRequest;
import com.andresmya.backendmarketplace.domain.dto.request.update.UpdatePasswordRequest;
import com.andresmya.backendmarketplace.domain.exception.InvalidArgumentException;
import com.andresmya.backendmarketplace.domain.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService {

    @Autowired
    private UserService userService;

    @Autowired
    private ResetPasswordCacheService resetPasswordCacheService;

    @Autowired
    private MailSendgridService mailSendgridService;

    public void updatePassword(UpdatePasswordRequest request) throws Exception{
        User user = userService.getUserByEmail(request.getEmail()).orElseThrow(() -> new NotFoundException("User with mail: " + request.getEmail()));
        if (!resetPasswordCacheService.verificationCodeIsValid(user.getEmail(), request.getVerificationCode())){
            throw new InvalidArgumentException("Invalid verification code or email");
        }
        if (!userService.passwordIsStrong(request.getNewPassword())) throw new InvalidArgumentException("Weak password");

        resetPasswordCacheService.deleteCacheRecord(user.getEmail());
        String newEncodedPassword = userService.encodePassword(request.getNewPassword());
        user.setPassword(newEncodedPassword);
        userService.updateUser(user);
    }

    public void requestRecoveryPassword(ResetPasswordRequest request) throws Exception{
        User user = userService.getUserByEmail(request.getEmail()).orElseThrow(() -> new NotFoundException("User with mail: " + request.getEmail()));
        String verificationCode = generateVerificationCode();
        resetPasswordCacheService.addCacheRecord(user.getEmail(), verificationCode);
        mailSendgridService.sendResetPasswordMail(user.getEmail(), verificationCode);
    }

    private String generateVerificationCode(){
        // TODO: random code
        return "HEY-CODE-123";
    }
}
