package com.example.cidaasv2.Service.Entity.MFA.SetupMFA.SMS;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class SetupSMSMFAResponseEntityTest {
    @Mock
    SetupSMSMFAResponseDataEntity data;
    @InjectMocks
    SetupSMSMFAResponseEntity setupSMSMFAResponseEntity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void setSuccess(){
        setupSMSMFAResponseEntity.setSuccess(true);
        Assert.assertTrue(setupSMSMFAResponseEntity.isSuccess());

    }

    @Test
    public void setStatus(){
        setupSMSMFAResponseEntity.setStatus(27);
        Assert.assertEquals(27,setupSMSMFAResponseEntity.getStatus());

    }

    @Test
    public void setData(){
        data=new SetupSMSMFAResponseDataEntity();

        data.setStatusId("Test");
        setupSMSMFAResponseEntity.setData(data);
        Assert.assertEquals("Test",setupSMSMFAResponseEntity.getData().getStatusId());

    }

}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme