package com.example.cidaasv2.Controller.Repository.Configuration.SmartPush;

import android.content.Context;

import com.example.cidaasv2.BuildConfig;
import com.example.cidaasv2.Helper.Entity.DeviceInfoEntity;
import com.example.cidaasv2.Helper.Enums.Result;
import com.example.cidaasv2.Helper.Extension.WebAuthError;
import com.example.cidaasv2.Helper.Genral.DBHelper;
import com.example.cidaasv2.Models.DBModel.AccessTokenModel;
import com.example.cidaasv2.Service.Entity.MFA.EnrollMFA.SmartPush.EnrollSmartPushMFAResponseEntity;
import com.example.cidaasv2.Service.Entity.MFA.SetupMFA.SmartPush.SetupSmartPushMFARequestEntity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class SmartPushConfigurationControllerTest {
    Context context;
    SmartPushConfigurationController shared;

    SmartPushConfigurationController smartPushConfigurationController;

    @Before
    public void setUp() {
        context= RuntimeEnvironment.application;

        DBHelper.setConfig(context);

        AccessTokenModel accessTokenModel=new AccessTokenModel();
        accessTokenModel.setAccessToken("New Access Token");
        accessTokenModel.setUserId("userId");
        accessTokenModel.setEncrypted(false);
        accessTokenModel.setRefreshToken("RefreshToken");
        accessTokenModel.setPlainToken("PlainToken");
        accessTokenModel.setSeconds(System.currentTimeMillis()+1000000);

        DBHelper.getShared().setAccessToken(accessTokenModel);

        smartPushConfigurationController=new SmartPushConfigurationController(context);
    }

    @Test
    public void testGenerateChallenge() throws Exception {
        smartPushConfigurationController.generateChallenge();
    }

    @Test
    public void testGetShared() throws Exception {
        SmartPushConfigurationController result = SmartPushConfigurationController.getShared(null);
        Assert.assertTrue(result instanceof SmartPushConfigurationController);
    }

    @Test
    public void testConfigureSmartPush() throws Exception {

        SetupSmartPushMFARequestEntity setupSmartPushMFARequestEntity=new SetupSmartPushMFARequestEntity();
        setupSmartPushMFARequestEntity.setClient_id("ClientId");
        setupSmartPushMFARequestEntity.setLogoUrl("Logourl");
        setupSmartPushMFARequestEntity.setDeviceInfo(new DeviceInfoEntity());
        smartPushConfigurationController.configureSmartPush("userId", "baseurl",setupSmartPushMFARequestEntity, new Result<EnrollSmartPushMFAResponseEntity>() {
            @Override
            public void success(EnrollSmartPushMFAResponseEntity result) {

            }

            @Override
            public void failure(WebAuthError error) {

            }
        });
    }

    @Test
    public void testLoginWithSmartPush() throws Exception {
        smartPushConfigurationController.LoginWithSmartPush("baseurl", "clientId", "trackId", "requestId", null, null);
    }


    @Test
    public void testLoginWithSmart() throws Exception {
        smartPushConfigurationController.LoginWithSmartPush("baseurl", "clientId", "trackId", "requestId", null, null);
    }

    @Test
    public void testLoginWithSmartPushnull() throws Exception {
        AccessTokenModel accessTokenModel=new AccessTokenModel();
        accessTokenModel.setAccessToken("New Access Token");
        accessTokenModel.setUserId("userId");
        accessTokenModel.setEncrypted(true);
        accessTokenModel.setRefreshToken("RefreshToken");
        accessTokenModel.setPlainToken("PlainToken");
        accessTokenModel.setSeconds(System.currentTimeMillis()+1000000);

        smartPushConfigurationController.LoginWithSmartPush("baseurl", "clientId", "trackId", "requestId", null, null);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme