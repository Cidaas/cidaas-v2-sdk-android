package com.example.cidaasv2.VerificationV2.domain.Service.Push.PushAcknowledge;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cidaasv2.Helper.CommonError.CommonError;
import com.example.cidaasv2.Helper.Enums.Result;
import com.example.cidaasv2.Helper.Enums.WebAuthErrorCode;
import com.example.cidaasv2.Helper.Extension.WebAuthError;
import com.example.cidaasv2.Helper.Logger.LogFile;

import com.example.cidaasv2.VerificationV2.data.Entity.Push.PushAcknowledge.PushAcknowledgeEntity;
import com.example.cidaasv2.VerificationV2.data.Entity.Push.PushAcknowledge.PushAcknowledgeResponse;
import com.example.cidaasv2.VerificationV2.data.Service.CidaasSDK_V2_Service;
import com.example.cidaasv2.VerificationV2.data.Service.ICidaasSDK_V2_Services;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PushAcknowledgeService {
    private Context context;

    public static PushAcknowledgeService shared;

    CidaasSDK_V2_Service service;


    public PushAcknowledgeService(Context contextFromCidaas) {
        context = contextFromCidaas;
        if (service == null) {
            service = new CidaasSDK_V2_Service();
        }

        //Todo setValue for authenticationType

    }

    public static PushAcknowledgeService getShared(@NonNull Context contextFromCidaas) {
        try {

            if (shared == null) {
                shared = new PushAcknowledgeService(contextFromCidaas);
            }
        }
        catch (Exception e) {
            LogFile.getShared(contextFromCidaas).addFailureLog("PushAcknowledgeService instance Creation Exception:-" + e.getMessage());
        }
        return shared;
    }

    //call pushAcknowledge Service
    public void callPushAcknowledgeService(@NonNull String pushAcknowledgeURL, Map<String, String> headers, PushAcknowledgeEntity pushAcknowledgeEntity,
                                    final Result<PushAcknowledgeResponse> pushAcknowledgeCallback)
    {
        final String methodName = "PushAcknowledgeService:-callPushAcknowledgeService()";
        try {
            //call service
            ICidaasSDK_V2_Services cidaasSDK_v2_services = service.getInstance();
            cidaasSDK_v2_services.pushAcknowledge(pushAcknowledgeURL, headers, pushAcknowledgeEntity).enqueue(new Callback<PushAcknowledgeResponse>() {
                @Override
                public void onResponse(Call<PushAcknowledgeResponse> call, Response<PushAcknowledgeResponse> response) {
                    if(response.isSuccessful())
                    {
                        pushAcknowledgeCallback.success(response.body());
                    }
                    else
                    {
                        pushAcknowledgeCallback.failure(CommonError.getShared(context).generateCommonErrorEntity(WebAuthErrorCode.PUSH_ACKNOWLEDGE_FAILURE,
                                response,"Error:- "+methodName));
                    }
                }

                @Override
                public void onFailure(Call<PushAcknowledgeResponse> call, Throwable t) {
                    pushAcknowledgeCallback.failure(WebAuthError.getShared(context).serviceCallFailureException(WebAuthErrorCode.PUSH_ACKNOWLEDGE_FAILURE,
                            t.getMessage(),"Error:- "+methodName));
                }
            });
        } catch (Exception e) {
            pushAcknowledgeCallback.failure(WebAuthError.getShared(context).methodException("Exception :" + methodName,
                    WebAuthErrorCode.PUSH_ACKNOWLEDGE_FAILURE, e.getMessage()));
        }
    }
}