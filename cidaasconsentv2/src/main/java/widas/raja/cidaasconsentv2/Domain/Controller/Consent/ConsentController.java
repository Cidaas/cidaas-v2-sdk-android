package widas.raja.cidaasconsentv2.Domain.Controller.Consent;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.cidaasv2.Controller.Repository.AccessToken.AccessTokenController;
import com.example.cidaasv2.Helper.CidaasProperties.CidaasProperties;
import com.example.cidaasv2.Helper.Enums.Result;
import com.example.cidaasv2.Helper.Enums.WebAuthErrorCode;
import com.example.cidaasv2.Helper.Extension.WebAuthError;
import com.example.cidaasv2.Helper.URLHelper.URLHelper;
import com.example.cidaasv2.Service.Entity.AccessToken.AccessTokenEntity;
import com.example.cidaasv2.Service.Entity.ConsentManagement.ConsentDetailsResultEntity;
import com.example.cidaasv2.Service.Entity.ConsentManagement.ConsentManagementAcceptResponseEntity;
import com.example.cidaasv2.Service.Entity.ConsentManagement.ConsentManagementAcceptedRequestEntity;
import com.example.cidaasv2.Service.Entity.LoginCredentialsEntity.LoginCredentialsResponseEntity;
import com.example.cidaasv2.Service.HelperForService.Headers.Headers;

import java.util.Dictionary;
import java.util.Map;

import timber.log.Timber;
import widas.raja.cidaasconsentv2.Domain.Service.Consent.ConsentService;
import widas.raja.cidaasconsentv2.Helper.ConsentURLHelper;
import widas.raja.cidaasconsentv2.data.Entity.ResumeConsent.ResumeConsentEntity;
import widas.raja.cidaasconsentv2.data.Entity.ResumeConsent.ResumeConsentResponseEntity;
import widas.raja.cidaasconsentv2.data.Entity.v1.ConsentDetailsV2RequestEntity;
import widas.raja.cidaasconsentv2.data.Entity.v1.ConsentEntity;
import widas.raja.cidaasconsentv2.data.Entity.v2.AcceptConsent.AcceptConsentV2Entity;
import widas.raja.cidaasconsentv2.data.Entity.v2.AcceptConsent.AcceptConsentV2ResponseEntity;
import widas.raja.cidaasconsentv2.data.Entity.v2.ConsentDetails.ConsentDetailsV2ResponseEntity;

public class ConsentController {

    private Context context;
    private String ConsentName="";
    private String ConsentVersion="";

    public static ConsentController shared;

    public ConsentController(Context contextFromCidaas) {

        context = contextFromCidaas;
        //Todo setValue for authenticationType

    }

    public static ConsentController getShared(Context contextFromCidaas) {
        try {

            if (shared == null) {
                shared = new ConsentController(contextFromCidaas);
            }
        } catch (Exception e) {
            Timber.i(e.getMessage());
        }
        return shared;
    }


    //Get Consent URL
    public void getConsentURL(@NonNull String consentName, @NonNull String consentVersion, @NonNull final Result<String> result) {

    }

    //Todo Log

    //-------------------------------------------------------------getConsentDetails----------------------------------------------------------------
    public void getConsentDetails(@NonNull final String consentName, @NonNull final Result<ConsentDetailsResultEntity> consentresult)
    {
        checkConsentDetailsProperties(consentName, consentresult);
    }

    private void checkConsentDetailsProperties(@NonNull final String consentName, @NonNull final Result<ConsentDetailsResultEntity> consentresult)
    {
        String methodName="ConsentController :checkConsentDetailsProperties()";
        try
        {
            if (consentName != null && !consentName.equals("")) {
                //Call getString Details
                serviceForConsentDetails(consentName,consentresult);
            } else {
                consentresult.failure(WebAuthError.getShared(context).propertyMissingException("Consent Name must not be null",methodName));
                return;
            }
        }
        catch (Exception e)
        {
            consentresult.failure(WebAuthError.getShared(context).methodException("Exception :"+methodName,WebAuthErrorCode.CONSENT_DETAILS_FAILURE,
                    e.getMessage()));
        }
    }

    private void serviceForConsentDetails(@NonNull final String consentName, @NonNull final Result<ConsentDetailsResultEntity> consentresult) {
        final String methodName="ConsentController :serviceForConsentDetails()";
        try {
        CidaasProperties.getShared(context).checkCidaasProperties(new Result<Dictionary<String, String>>() {
            @Override
            public void success(Dictionary<String, String> result) {
                String baseurl = result.get("DomainURL");
                String clientId = result.get("ClientId");

                String consentDetailsURL=ConsentURLHelper.getShared().getConsent_details(baseurl,consentName);

                //Headers generation
                Map<String, String> headers = Headers.getShared(context).getHeaders(null,false,null);

                //service call For consent details
                ConsentService.getShared(context).getConsentDetails(consentDetailsURL, headers, consentresult);

            }

            @Override
            public void failure(WebAuthError error) {
                consentresult.failure(error);
            }
        });

        } catch (Exception e) {
            consentresult.failure(WebAuthError.getShared(context).methodException("Exception :"+methodName, WebAuthErrorCode.CONSENT_DETAILS_FAILURE,e.getMessage()));
        }
    }



    //----------------------------------------------------Service call To AcceptConsent--------------------------------------------------------------
    public void acceptConsent(@NonNull final ConsentEntity consentEntity, final Result<LoginCredentialsResponseEntity> loginresult)
    {
      checkAcceptConsentProperties(consentEntity,loginresult);
    }

    private void checkAcceptConsentProperties(@NonNull final ConsentEntity consentEntity, final Result<LoginCredentialsResponseEntity> loginresult)
    {
        String methodName="ConsentController :checkAcceptConsentProperties()";
        try
        {
            if (consentEntity.getSub() != null && !consentEntity.getSub().equals("") && consentEntity.getConsentName() != null
                    && !consentEntity.getConsentName().equals("") && consentEntity.getConsentVersion() != null
                    && !consentEntity.getConsentVersion().equals("") && consentEntity.isAccepted()) {

                addAcceptConsentProperties(consentEntity, loginresult);
            }
            else {
                loginresult.failure(WebAuthError.getShared(context).propertyMissingException(
                        "Sub or ConsentName or ConsentVersion must not be null and isAccept must be true", methodName));
            }
        }
        catch (Exception e)
        {
            loginresult.failure(WebAuthError.getShared(context).methodException("Exception :"+methodName,WebAuthErrorCode.CONSENT_DETAILS_FAILURE,
                    e.getMessage()));
        }
    }

    private void serviceForAcceptConsent(@NonNull final ConsentManagementAcceptedRequestEntity consentEntity,
                                         @NonNull final Result<LoginCredentialsResponseEntity> loginResult) {
        final String methodName="ConsentController :serviceForConsentDetails()";
        try {
            CidaasProperties.getShared(context).checkCidaasProperties(new Result<Dictionary<String, String>>() {
                @Override
                public void success(Dictionary<String, String> result) {
                    String baseurl = result.get("DomainURL");
                    String clientId = result.get("ClientId");

                    consentEntity.setClient_id(clientId);

                    String acceptConsentURL=ConsentURLHelper.getShared().getAcceptConsent(baseurl);

                    serviceCallForAcceptConsent(acceptConsentURL,consentEntity,loginResult);
                }

                @Override
                public void failure(WebAuthError error) {
                    loginResult.failure(error);
                }
            });

        } catch (Exception e) {
            loginResult.failure(WebAuthError.getShared(context).methodException("Exception :"+methodName, WebAuthErrorCode.CONSENT_DETAILS_FAILURE,
                    e.getMessage()));
        }
    }


    private void serviceCallForAcceptConsent(final String acceptConsentURL,  final ConsentManagementAcceptedRequestEntity consentEntity,
                                             final Result<LoginCredentialsResponseEntity> loginresult)
    {
       final String methodName="ConsentController :serviceCallForAcceptConsent()";
       try {

           //Headers generation
           Map<String, String> headers = Headers.getShared(context).getHeaders(null,false,null);

           //service call For consent details
           ConsentService.getShared(context).acceptConsent(acceptConsentURL, consentEntity, headers, new Result<ConsentManagementAcceptResponseEntity>() {
               @Override
               public void success(ConsentManagementAcceptResponseEntity result) {

                //todo Call Resume call
               }

               @Override
               public void failure(WebAuthError error) {
                   loginresult.failure(error);
               }
           });
       }
       catch (Exception e)
       {
           loginresult.failure(WebAuthError.getShared(context).methodException("Exception :"+methodName,WebAuthErrorCode.ACCEPT_CONSENT_FAILURE,
                   e.getMessage()));
       }
    }

    //Todo Log

    private void addAcceptConsentProperties(final ConsentEntity consentEntity,final Result<LoginCredentialsResponseEntity> loginresult)
    {
        //For Naming and Client ID use seperate entity
        ConsentManagementAcceptedRequestEntity consentManagementAcceptedRequestEntity = new ConsentManagementAcceptedRequestEntity();
        consentManagementAcceptedRequestEntity.setAccepted(consentEntity.isAccepted());
        consentManagementAcceptedRequestEntity.setSub(consentEntity.getSub());
        consentManagementAcceptedRequestEntity.setName(consentEntity.getConsentName());
        consentManagementAcceptedRequestEntity.setTrackId(consentEntity.getTrackId());
        consentManagementAcceptedRequestEntity.setVersion(consentEntity.getConsentVersion());

        serviceForAcceptConsent(consentManagementAcceptedRequestEntity,loginresult);
    }


    //------------------------------------------------------Consent V2------------------------------------------------------------------------

    public void getConsentDetailsV2(@NonNull ConsentDetailsV2RequestEntity consentDetailsV2Entity, Result<ConsentDetailsV2ResponseEntity> consentDetails)
    {
         checkProperties(consentDetailsV2Entity, consentDetails);
    }

    private void checkProperties(ConsentDetailsV2RequestEntity consentDetailsV2RequestEntity, Result<ConsentDetailsV2ResponseEntity> consentDetails) {
       String methodName="ConsentController:checkProperties";
        try
        {
            if(!consentDetailsV2RequestEntity.getConsent_id().equals("") && consentDetailsV2RequestEntity.getConsent_version_id()!=null &&
            !consentDetailsV2RequestEntity.getConsent_version_id().equals("") && consentDetailsV2RequestEntity.getConsent_id()!=null)
            {
                if(consentDetailsV2RequestEntity.getSub()!=null && !consentDetailsV2RequestEntity.getSub().equals("") &&
                        consentDetailsV2RequestEntity.getTrack_id()!=null && !consentDetailsV2RequestEntity.getTrack_id().equals(""))
                {
                    addProperties(consentDetailsV2RequestEntity,consentDetails);
                }
                else
                {
                    consentDetails.failure(WebAuthError.getShared(context).propertyMissingException("Track id or sub must not be null",methodName));
                }
            }
            else
            {
                consentDetails.failure(WebAuthError.getShared(context).propertyMissingException("Consent id or Consent Version id must not be null",methodName));
            }
        }
        catch (Exception e)
        {
            consentDetails.failure(WebAuthError.getShared(context).methodException(methodName, WebAuthErrorCode.CONSENT_DETAILS_FAILURE,e.getMessage()));
        }
    }

    private void addProperties(final ConsentDetailsV2RequestEntity consentDetailsV2Entity, final Result<ConsentDetailsV2ResponseEntity> consentDetails) {
        String methodName="ConsentController:addProperties";
        try
        {
            CidaasProperties.getShared(context).checkCidaasProperties(new Result<Dictionary<String, String>>() {
                @Override
                public void success(Dictionary<String, String> result) {
                    if(consentDetailsV2Entity.getClient_id()==null || consentDetailsV2Entity.getClient_id().equals(""))
                    {
                        consentDetailsV2Entity.setClient_id(result.get("ClientId"));
                        serviceForGetConsentDetailsV2(result.get("DomainURL"),consentDetailsV2Entity,consentDetails);
                    }
                    else
                    {
                        serviceForGetConsentDetailsV2(result.get("DomainURL"),consentDetailsV2Entity,consentDetails);
                    }
                }

                @Override
                public void failure(WebAuthError error) {
                    consentDetails.failure(error);
                }
            });
        }
        catch (Exception e)
        {
            consentDetails.failure(WebAuthError.getShared(context).methodException(methodName,WebAuthErrorCode.CONSENT_DETAILS_FAILURE,e.getMessage()));
        }
    }

    private void serviceForGetConsentDetailsV2(String baseurl,ConsentDetailsV2RequestEntity consentDetailsV2RequestEntity, Result<ConsentDetailsV2ResponseEntity> consentDetails) {
     String methodName="ConsentController:serviceForGetConsentDetailsV2";
     try
     {
         String consentDetailsUrl= ConsentURLHelper.getShared().getConsentDetailsV2(baseurl);

         //headers Generation
         Map<String,String> headers= Headers.getShared(context).getHeaders(null,false, URLHelper.contentTypeJson);

         //Service
         ConsentService.getShared(context).getConsentDetailsV2(consentDetailsUrl,consentDetailsV2RequestEntity,headers,consentDetails);
     }
     catch (Exception e)
     {
         consentDetails.failure(WebAuthError.getShared(context).methodException(methodName,WebAuthErrorCode.CONSENT_DETAILS_FAILURE,e.getMessage()));
     }

    }

//-----------------------------------------------Accept Consent V2-------------------------------------------------------------------------

    public void acceptConsentV2(@NonNull AcceptConsentV2Entity acceptConsentV2Entity, Result<LoginCredentialsResponseEntity> consentDetails)
    {
        checkPropertiesForAcceptConsentV2(acceptConsentV2Entity, consentDetails);
    }

    private void checkPropertiesForAcceptConsentV2(AcceptConsentV2Entity acceptConsentV2Entity, Result<LoginCredentialsResponseEntity> consentDetails)
    {
        String methodName="ConsentController:checkPropertiesForAcceptConsentV2";
        try
        {
            if(!acceptConsentV2Entity.getConsent_id().equals("") && acceptConsentV2Entity.getConsent_version_id()!=null &&
                    !acceptConsentV2Entity.getConsent_version_id().equals("") && acceptConsentV2Entity.getConsent_id()!=null)
            {
                if(acceptConsentV2Entity.getSub()!=null && !acceptConsentV2Entity.getSub().equals("") &&
                        acceptConsentV2Entity.getTrackId()!=null && !acceptConsentV2Entity.getTrackId().equals(""))
                {
                    addPropertiesForAcceptConsentV2(acceptConsentV2Entity,consentDetails);
                }
                else
                {
                    consentDetails.failure(WebAuthError.getShared(context).propertyMissingException("Track id or sub must not be null",methodName));
                }
            }
            else
            {
                consentDetails.failure(WebAuthError.getShared(context).propertyMissingException("Consent id or Consent Version id must not be null",methodName));
            }
        }
        catch (Exception e)
        {
            consentDetails.failure(WebAuthError.getShared(context).methodException(methodName, WebAuthErrorCode.CONSENT_DETAILS_FAILURE,e.getMessage()));
        }
    }

    private void addPropertiesForAcceptConsentV2(final AcceptConsentV2Entity acceptConsentV2Entity, final Result<LoginCredentialsResponseEntity> consentDetails) {
        String methodName="ConsentController:addProperties";
        try
        {
            CidaasProperties.getShared(context).checkCidaasProperties(new Result<Dictionary<String, String>>() {
                @Override
                public void success(Dictionary<String, String> result) {
                    if(acceptConsentV2Entity.getClient_id()==null || acceptConsentV2Entity.getClient_id().equals(""))
                    {
                        acceptConsentV2Entity.setClient_id(result.get("ClientId"));
                        serviceForAcceptConsentV2(result.get("DomainURL"),acceptConsentV2Entity,consentDetails);
                    }
                    else
                    {
                        serviceForAcceptConsentV2(result.get("DomainURL"),acceptConsentV2Entity,consentDetails);
                    }
                }

                @Override
                public void failure(WebAuthError error) {
                    consentDetails.failure(error);
                }
            });
        }
        catch (Exception e)
        {
            consentDetails.failure(WebAuthError.getShared(context).methodException(methodName,WebAuthErrorCode.CONSENT_DETAILS_FAILURE,e.getMessage()));
        }
    }

    private void serviceForAcceptConsentV2(final String baseurl, final AcceptConsentV2Entity acceptConsentV2, final Result<LoginCredentialsResponseEntity> loginResult) {
        String methodName="ConsentController:serviceForAcceptConsentV2";
        try
        {
            String consentDetailsUrl= ConsentURLHelper.getShared().getAcceptConsentV2(baseurl);

            //headers Generation
            Map<String,String> headers= Headers.getShared(context).getHeaders(null,false, URLHelper.contentTypeJson);

            //Service
            ConsentService.getShared(context).acceptConsentV2(consentDetailsUrl, acceptConsentV2, headers, new Result<AcceptConsentV2ResponseEntity>() {
                @Override
                public void success(AcceptConsentV2ResponseEntity result) {
                    //todo call Resume Call
                    ResumeConsentEntity resumeConsentEntity=new ResumeConsentEntity(acceptConsentV2.getSub(),acceptConsentV2.getTrackId()
                            ,acceptConsentV2.getConsent_id(),acceptConsentV2.getConsent_version_id(),acceptConsentV2.getClient_id());


                    callResumeConsent(baseurl,resumeConsentEntity,loginResult);
                }

                @Override
                public void failure(WebAuthError error) {
                    loginResult.failure(error);
                }
            });
        }
        catch (Exception e)
        {
            loginResult.failure(WebAuthError.getShared(context).methodException(methodName,WebAuthErrorCode.CONSENT_DETAILS_FAILURE,e.getMessage()));
        }
    }

    private void callResumeConsent(String baseurl, ResumeConsentEntity resumeConsentEntity, final Result<LoginCredentialsResponseEntity > loginResult)
    {
        String methodName = "ConsentController:callResumeConsent";
        try {
            String resumeConsentUrl = ConsentURLHelper.getShared().getResumeConsentURL(baseurl);

            //headers Generation
            Map<String, String> headers = Headers.getShared(context).getHeaders(null, false, URLHelper.contentTypeJson);

            //Service
            ConsentService.getShared(context).resumeConsent(resumeConsentUrl, resumeConsentEntity, headers, new Result<ResumeConsentResponseEntity>() {
                @Override
                public void success(ResumeConsentResponseEntity resumeConsentresult) {
                    getTokenByCode(resumeConsentresult,loginResult);
                }

                @Override
                public void failure(WebAuthError error) {
                loginResult.failure(error);
                }
            });
        } catch (Exception e) {
            loginResult.failure(WebAuthError.getShared(context).methodException(methodName, WebAuthErrorCode.RESUME_CONSENT_FAILURE, e.getMessage()));
        }
    }

    public void getTokenByCode(ResumeConsentResponseEntity resumeConsentresult,final Result<LoginCredentialsResponseEntity > loginResult) {
        String methodName = "ConsentController:getTokenByCode()";
        try {
            AccessTokenController.getShared(context).getAccessTokenByCode(resumeConsentresult.getData().getCode(), new Result<AccessTokenEntity>() {
                @Override
                public void success(AccessTokenEntity accessTokenresult) {
                    LoginCredentialsResponseEntity loginCredentialsResponseEntity = new LoginCredentialsResponseEntity();
                    loginCredentialsResponseEntity.setData(accessTokenresult);
                    loginCredentialsResponseEntity.setStatus(200);
                    loginCredentialsResponseEntity.setSuccess(true);
                    loginResult.success(loginCredentialsResponseEntity);
                }

                @Override
                public void failure(WebAuthError error) {
                    loginResult.failure(error);
                }
            });
        }
        catch (Exception e)
        {
            loginResult.failure(WebAuthError.getShared(context).methodException(methodName, WebAuthErrorCode.RESUME_CONSENT_FAILURE, e.getMessage()));
        }
    }
}
