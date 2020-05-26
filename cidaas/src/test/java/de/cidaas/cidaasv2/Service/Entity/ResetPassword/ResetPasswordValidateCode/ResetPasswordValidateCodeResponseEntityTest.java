package de.cidaas.cidaasv2.Service.Entity.ResetPassword.ResetPasswordValidateCode;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class ResetPasswordValidateCodeResponseEntityTest {

    ResetPasswordValidateCodeDataEntity data;

    ResetPasswordValidateCodeResponseEntity resetPasswordValidateCodeResponseEntity;

    @Before
    public void setUp() {
        resetPasswordValidateCodeResponseEntity = new ResetPasswordValidateCodeResponseEntity();
    }

    @Test
    public void setSuccess() {
        resetPasswordValidateCodeResponseEntity.setSuccess(true);
        Assert.assertTrue(resetPasswordValidateCodeResponseEntity.isSuccess());

    }

    @Test
    public void setStatus() {
        resetPasswordValidateCodeResponseEntity.setStatus(27);
        Assert.assertEquals(27, resetPasswordValidateCodeResponseEntity.getStatus());

    }

    @Test
    public void setData() {
        data = new ResetPasswordValidateCodeDataEntity();

        data.setExchangeId("Test");
        resetPasswordValidateCodeResponseEntity.setData(data);
        Assert.assertEquals("Test", resetPasswordValidateCodeResponseEntity.getData().getExchangeId());

    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme