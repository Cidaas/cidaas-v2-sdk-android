package com.example.cidaasv2.Service.Entity.MFA.AuthenticateMFA.TOTP;

import com.example.cidaasv2.Service.Entity.MFA.AuthenticateMFA.TOTP.AuthenticateTOTPResponseDataEntity;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class AuthenticateTOTPResponseDataEntityTest {
    AuthenticateTOTPResponseDataEntity authenticateTOTPResponseDataEntity=new AuthenticateTOTPResponseDataEntity();
    @Test
    public void getSub()
    {
        authenticateTOTPResponseDataEntity.setSub("test");
        assertTrue(authenticateTOTPResponseDataEntity.getSub()=="test");
    }
    @Test
    public void geDescription()
    {
        authenticateTOTPResponseDataEntity.setTrackingCode("test");
        assertTrue(authenticateTOTPResponseDataEntity.getTrackingCode()=="test");

    }
    @Test
    public void getUsageType()
    {
        authenticateTOTPResponseDataEntity.setUsageType("UsageType");
        assertTrue(authenticateTOTPResponseDataEntity.getUsageType()=="UsageType");

    }
    @Test
    public void getVerificationType()
    {
        authenticateTOTPResponseDataEntity.setVerificationType("verificationType");
        assertTrue(authenticateTOTPResponseDataEntity.getVerificationType()=="verificationType");

    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme