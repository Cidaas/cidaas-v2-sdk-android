package de.cidaas.cidaasv2.Service.Entity.MFA.AuthenticateMFA.TOTP;

import org.junit.Test;


import static junit.framework.TestCase.assertTrue;

public class AuthenticateTOTPRequestDeviceTest {
    @Test
    public void getDeviceId() {

        AuthenticateTOTPRequestDevice authenticateTOTPRequestDevice = new AuthenticateTOTPRequestDevice();
        authenticateTOTPRequestDevice.setDeviceId("Device ID");
        assertTrue(authenticateTOTPRequestDevice.getDeviceId() == "Device ID");

    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme