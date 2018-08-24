package com.example.cidaasv2.Service.Entity.MFA.AuthenticateMFA.BackupCode;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertTrue;

public class AuthenticateBackupCodeResponseEntityTest {
    @Mock
    AuthenticateBackupCodeResponseDataEntity data;
    @InjectMocks
    AuthenticateBackupCodeResponseEntity authenticateBackupCodeResponseEntity=new AuthenticateBackupCodeResponseEntity();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSuccess()
    {
        authenticateBackupCodeResponseEntity.setSuccess(true);
        assertTrue(authenticateBackupCodeResponseEntity.isSuccess());

    }
    @Test
    public void getStatus()
    {

        authenticateBackupCodeResponseEntity.setStatus(417);
        assertTrue(authenticateBackupCodeResponseEntity.getStatus()==417);

    }
    @Test
    public void getData()
    {

        authenticateBackupCodeResponseEntity.setSuccess(true);
        authenticateBackupCodeResponseEntity.setData(data);
        Assert.assertEquals(authenticateBackupCodeResponseEntity.getData(),data);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme