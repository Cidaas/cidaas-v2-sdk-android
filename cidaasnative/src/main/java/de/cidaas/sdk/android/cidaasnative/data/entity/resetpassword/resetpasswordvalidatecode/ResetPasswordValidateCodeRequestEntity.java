package de.cidaas.sdk.android.cidaasnative.data.entity.resetpassword.resetpasswordvalidatecode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResetPasswordValidateCodeRequestEntity implements Serializable {
    String code;
    String resetRequestId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResetRequestId() {
        return resetRequestId;
    }

    public void setResetRequestId(String resetRequestId) {
        this.resetRequestId = resetRequestId;
    }
}
