package com.example.cidaasv2.Controller.Repository.Configuration.SMS;

import android.content.Context;

import com.example.cidaasv2.BuildConfig;
import com.example.cidaasv2.Helper.Enums.Result;
import com.example.cidaasv2.Helper.Extension.WebAuthError;
import com.example.cidaasv2.Service.Entity.LoginCredentialsEntity.LoginCredentialsResponseEntity;
import com.example.cidaasv2.Service.Entity.MFA.AuthenticateMFA.SMS.AuthenticateSMSRequestEntity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;


@RunWith(RobolectricTestRunner.class)

public class SMSConfigurationControllerTest {
    Context context;
    SMSConfigurationController shared;
    SMSConfigurationController sMSConfigurationController;

    @Before
    public void setUp() {
        context= RuntimeEnvironment.application;
        sMSConfigurationController=new SMSConfigurationController(context);
    }

    @Test
    public void testConfigureSMS() throws Exception {
        sMSConfigurationController.configureSMS("sub", null);
    }

    @Test
    public void testEnrollSMSMFA() throws Exception {
        sMSConfigurationController.enrollSMSMFA("code", "StatusId", null);
    }

    @Test
    public void testGetShared() throws Exception {
        SMSConfigurationController result = SMSConfigurationController.getShared(null);
        Assert.assertTrue(result instanceof SMSConfigurationController);
    }

    @Test
    public void testLoginWithSMS() throws Exception {
        sMSConfigurationController.loginWithSMS(null, null);
    }

    @Test
    public void testVerifySMS() throws Exception {
        sMSConfigurationController.verifySMS("baseurl", "clientId", new Result<LoginCredentialsResponseEntity>() {
            @Override
            public void success(LoginCredentialsResponseEntity result) {

            }

            @Override
            public void failure(WebAuthError error) {

            }
        });
    }


}
