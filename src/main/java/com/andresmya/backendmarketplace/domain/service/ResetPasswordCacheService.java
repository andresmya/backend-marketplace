package com.andresmya.backendmarketplace.domain.service;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class ResetPasswordCacheService {

    private final Cache<String, String> emailVerificationCodeCache;

    public ResetPasswordCacheService() {
        //super();
        emailVerificationCodeCache = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.HOURS).build();
    }

    public void deleteCacheRecord(String email) {
        emailVerificationCodeCache.invalidate(email);
    }

    public void addCacheRecord(String email, String verificationCode) {
        emailVerificationCodeCache.put(email, verificationCode);
    }

    public boolean verificationCodeIsValid(String email, String verificationCodeRequest) {
        String verificationCodeCached = emailVerificationCodeCache.getIfPresent(email);
        return verificationCodeRequest.equals(verificationCodeCached);
    }

}
