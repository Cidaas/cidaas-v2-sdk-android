package com.example.cidaasv2.Service.Entity.MFA.AuthenticateMFA.SmartPush;

import com.example.cidaasv2.Helper.Entity.DeviceInfoEntity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class AuthenticateSmartPushRequestEntityTest {
    @Mock
    DeviceInfoEntity deviceInfo;
    @InjectMocks
    AuthenticateSmartPushRequestEntity authenticateSmartPushRequestEntity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getStatusID()
    {
        authenticateSmartPushRequestEntity.setStatusId("Status_ID");
        assertTrue(authenticateSmartPushRequestEntity.getStatusId().equals("Status_ID"));
    }

    @Test
    public void getVerifierPassword()
    {
        authenticateSmartPushRequestEntity.setVerifierPassword("Password");
        assertTrue(authenticateSmartPushRequestEntity.getVerifierPassword().equals("Password"));
    }


    @Test
    public void getDeviceInfoEntity()
    {
        DeviceInfoEntity deviceInfoEntity=new DeviceInfoEntity();
        deviceInfoEntity.setPushNotificationId("push");
        deviceInfoEntity.setDeviceId("deviceID");
        deviceInfoEntity.setDeviceMake("deviceMake");
        deviceInfoEntity.setDeviceModel("deviceModel");
        deviceInfoEntity.setDeviceVersion("deviceVersion");

        authenticateSmartPushRequestEntity.setDeviceInfo(deviceInfo);

/*        assertTrue(authenticateSmartPushRequestEntity.getDeviceInfo().getDeviceId().equals("deviceID"));
        assertTrue(authenticateSmartPushRequestEntity.getDeviceInfo().getDeviceMake().equals("deviceMake"));
        assertTrue(authenticateSmartPushRequestEntity.getDeviceInfo().getDeviceModel().equals("deviceModel"));
        assertTrue(authenticateSmartPushRequestEntity.getDeviceInfo().getDeviceVersion().equals("deviceVersion"));
        assertTrue(authenticateSmartPushRequestEntity.getDeviceInfo().getPushNotificationId().equals("push"));*/
    }


}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme