package de.cidaas.cidaasv2.Service.Repository.Verification.TOTP;

import android.content.Context;

import com.example.cidaasv2.Service.Repository.Verification.TOTP.TOTPVerificationService;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;

import de.cidaas.cidaasv2.Controller.HelperClass;

import de.cidaas.sdk.android.entities.DeviceInfoEntity;













import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import timber.log.Timber;

public class TOTPVerificationServiceCallTest {

   /* CidaassdkService service;
    TOTPVerificationService totpVerificationService;
    DeviceInfoEntity deviceInfoEntity=new DeviceInfoEntity();
    Dictionary<String, String> savedProperties=new Hashtable<>();

    CountDownLatch latch=new CountDownLatch(1);




    Context context;

    @Before
    public void setUp() throws Exception{
        context= Mockito.mock(Context.class);
        service=new CidaassdkService();
        MockitoAnnotations.initMocks(this);
        totpVerificationService=new TOTPVerificationService(context);

        deviceInfoEntity.setDeviceId("DeviceID");
        deviceInfoEntity.setDeviceVersion("DeviceVersion");
        deviceInfoEntity.setDeviceModel("DeviceModel");
        deviceInfoEntity.setDeviceMake("DeviceMake");
        deviceInfoEntity.setPushNotificationId("PushNotificationId");


        savedProperties.put("Verifier", "codeVerifier");
        savedProperties.put("Challenge","codeChallenge");
        savedProperties.put("Method", "ChallengeMethod");

    }



    @Test
    public void testWebClient() throws  Exception{

        //  Whitebox.set
        try {
            Timber.e("Success");



            final MockResponse response = new MockResponse().setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "    \"qrCode\": \"556b95f8-9326-4250-a4ee-0e0d887f7a7d\""+
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL= server.url("").toString();
            server.url("/verification-srv/backupcode/callSetup");



            server.enqueue(response);
            Cidaas.baseurl=domainURL;


            Dictionary<String,String> loginProperties=new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID","ClientID");
            loginProperties.put("RedirectURL","RedirectURL");


            totpVerificationService.setupTOTP(loginProperties.get("DomainURL"), "inter","Code Challenge ",new SetupTOTPMFARequestEntity(),deviceInfoEntity ,new Result<SetupTOTPMFAResponseEntity>() {
                @Override
                public void success(SetupTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d",result.getData().getQrCode());

                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Cidaas developer",error.getErrorMessage());
                    latch.countDown();
                }
            });
            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        }
        catch (Exception e)
        {
            Assert.assertEquals(e.getMessage(),true,true);
            Assert.assertFalse(e.getMessage(),true);
        }

    }



    @Test
    public void testWebClientnull() throws  Exception{

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "    \"qrCode\": \"556b95f8-9326-4250-a4ee-0e0d887f7a7d\""+
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL= server.url("").toString();
            server.url("/authz-srv/authrequest/authz/generate");



            server.enqueue(response);
            Cidaas.baseurl=domainURL;


            Dictionary<String,String> loginProperties=new Hashtable<>();
            loginProperties.put("DomainURL",HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID","ClientID");
            loginProperties.put("RedirectURL","RedirectURL");


            totpVerificationService.setupTOTP(loginProperties.get("DomainURL"), "inter","Code Challenge ",new SetupTOTPMFARequestEntity(),deviceInfoEntity ,new Result<SetupTOTPMFAResponseEntity>() {
                @Override
                public void success(SetupTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d",result.getData().getQrCode());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Cidaas developer",error.getErrorMessage());
                    latch.countDown();
                }
            });
            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        }
        catch (Exception e)
        {
            Assert.assertEquals(e.getMessage(),true,true);
            Assert.assertFalse(e.getMessage(),true);
        }

    }


    @Test
    public void testWebClientFor202() throws  Exception{

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(202)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "        \"requestId_name\": \"Raja Developers\",\n" +
                            "        \"allowLoginWith\": [\n" +
                            "            \"EMAIL\",\n" +
                            "            \"MOBILE\",\n" +
                            "            \"USER_NAME\"\n" +
                            "        ]\n" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL= server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");



            server.enqueue(response);
            Cidaas.baseurl=domainURL;

            Dictionary<String,String> loginProperties=new Hashtable<>();
            loginProperties.put("DomainURL",HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID","ClientID");
            loginProperties.put("RedirectURL","RedirectURL");



            totpVerificationService.setupTOTP(loginProperties.get("DomainURL"), "inter","Code Challenge ",new SetupTOTPMFARequestEntity(),deviceInfoEntity ,new Result<SetupTOTPMFAResponseEntity>() {
                @Override
                public void success(SetupTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d",result.getData().getQrCode());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Service failure but successful response",error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        }
        catch (Exception e)
        {
            Assert.assertEquals(e.getMessage(),true,true);
            Assert.assertFalse(e.getMessage(),true);
        }

    }


    @Test
    public void testWebClientFor401() throws  Exception{

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "   \"error\": {\n" +
                            "                \"code\": 24008,\n" +
                            "                        \"moreInfo\": \"\",\n" +
                            "                        \"type\": \"LoginException\",\n" +
                            "                        \"status\": 400,\n" +
                            "                        \"referenceNumber\": \"1537337364806\",\n" +
                            "                        \"error\": \"Invalid requestId\"\n" +
                            "            }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL= server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");



            server.enqueue(response);
            Cidaas.baseurl=domainURL;

            Dictionary<String,String> loginProperties=new Hashtable<>();
            loginProperties.put("DomainURL",HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID","ClientID");
            loginProperties.put("RedirectURL","RedirectURL");


            totpVerificationService.setupTOTP(loginProperties.get("DomainURL"), "inter","Code Challenge ",new SetupTOTPMFARequestEntity(),deviceInfoEntity ,new Result<SetupTOTPMFAResponseEntity>() {
                @Override
                public void success(SetupTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d",result.getData().getQrCode());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId",error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        }
        catch (Exception e)
        {
            Assert.assertEquals(e.getMessage(),true,true);
            Assert.assertFalse(e.getMessage(),true);
        }

    }

    @Test
    public void testWebClientFaliureError() throws  Exception{

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "     \"error\": \"Invalid requestId\" \n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL= server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");



            server.enqueue(response);
            Cidaas.baseurl=domainURL;

            Dictionary<String,String> loginProperties=new Hashtable<>();
            loginProperties.put("DomainURL",HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID","ClientID");
            loginProperties.put("RedirectURL","RedirectURL");

            totpVerificationService.setupTOTP(loginProperties.get("DomainURL"), "inter","Code Challenge ",new SetupTOTPMFARequestEntity(),deviceInfoEntity ,new Result<SetupTOTPMFAResponseEntity>() {
                @Override
                public void success(SetupTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d",result.getData().getQrCode());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId",error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        }
        catch (Exception e)
        {
            Assert.assertEquals(e.getMessage(),true,true);
            Assert.assertFalse(e.getMessage(),true);
        }

    }


    @Test
    public void testWebClientFaliureException() throws  Exception{

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "     \"error\": \"Invalid requestId \n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL= server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");



            server.enqueue(response);
            Cidaas.baseurl=domainURL;

            Dictionary<String,String> loginProperties=new Hashtable<>();
            loginProperties.put("DomainURL",HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID","ClientID");
            loginProperties.put("RedirectURL","RedirectURL");

            totpVerificationService.setupTOTP(loginProperties.get("DomainURL"), "inter","Code Challenge ",new SetupTOTPMFARequestEntity(),deviceInfoEntity ,new Result<SetupTOTPMFAResponseEntity>() {
                @Override
                public void success(SetupTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d",result.getData().getQrCode());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId",error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        }
        catch (Exception e)
        {
            Assert.assertEquals(e.getMessage(),true,true);
            Assert.assertFalse(e.getMessage(),true);
        }

    }

*/


    CidaassdkService service;
    TOTPVerificationService totpVerificationService;
    DeviceInfoEntity deviceInfoEntity = new DeviceInfoEntity();
    Dictionary<String, String> savedProperties = new Hashtable<>();

    CountDownLatch latch = new CountDownLatch(1);


    Context context;

    @Before
    public void setUp() throws Exception {
        context = Mockito.mock(Context.class);
        service = new CidaassdkService();
        MockitoAnnotations.initMocks(this);
        totpVerificationService = new TOTPVerificationService(context);

        deviceInfoEntity.setDeviceId("DeviceID");
        deviceInfoEntity.setDeviceVersion("DeviceVersion");
        deviceInfoEntity.setDeviceModel("DeviceModel");
        deviceInfoEntity.setDeviceMake("DeviceMake");
        deviceInfoEntity.setPushNotificationId("PushNotificationId");


        savedProperties.put("Verifier", "codeVerifier");
        savedProperties.put("Challenge", "codeChallenge");
        savedProperties.put("Method", "ChallengeMethod");

    }

//INITITATE

    @Test
    public void testWebClient() throws Exception {

        //  Whitebox.set
        try {
            Timber.e("Success");


            final MockResponse response = new MockResponse().setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "    \"statusId\": \"556b95f8-9326-4250-a4ee-0e0d887f7a7d\"" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/verification-srv/backupcode/callSetup");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;


            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.initiateTOTP(loginProperties.get("DomainURL"), "codeChallenge", new InitiateTOTPMFARequestEntity(), deviceInfoEntity, new Result<InitiateTOTPMFAResponseEntity>() {
                @Override
                public void success(InitiateTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getStatusId());

                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Cidaas developer", error.getErrorMessage());
                    latch.countDown();
                }
            });
            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testWebClinetDevicenull() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "    \"statusId\": \"556b95f8-9326-4250-a4ee-0e0d887f7a7d\"" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/authz-srv/authrequest/authz/generate");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;


            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.initiateTOTP(loginProperties.get("DomainURL"), "codeChallenge", new InitiateTOTPMFARequestEntity(), null, new Result<InitiateTOTPMFAResponseEntity>() {
                @Override
                public void success(InitiateTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getStatusId());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    // Assert.assertEquals("Cidaas developer",error.getErrorMessage());
                    latch.countDown();
                }
            });
            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testWebClientFor202() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(202)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "        \"requestId_name\": \"Raja Developers\",\n" +
                            "        \"allowLoginWith\": [\n" +
                            "            \"EMAIL\",\n" +
                            "            \"MOBILE\",\n" +
                            "            \"USER_NAME\"\n" +
                            "        ]\n" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.initiateTOTP(loginProperties.get("DomainURL"), "codeChallenge", new InitiateTOTPMFARequestEntity(), deviceInfoEntity, new Result<InitiateTOTPMFAResponseEntity>() {
                @Override
                public void success(InitiateTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getStatusId());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Service failure but successful response", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testWebClientFor401() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "   \"error\": {\n" +
                            "                \"code\": 24008,\n" +
                            "                        \"moreInfo\": \"\",\n" +
                            "                        \"type\": \"LoginException\",\n" +
                            "                        \"status\": 400,\n" +
                            "                        \"referenceNumber\": \"1537337364806\",\n" +
                            "                        \"error\": \"Invalid requestId\"\n" +
                            "            }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.initiateTOTP(loginProperties.get("DomainURL"), "codeChallenge", new InitiateTOTPMFARequestEntity(), deviceInfoEntity, new Result<InitiateTOTPMFAResponseEntity>() {
                @Override
                public void success(InitiateTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getStatusId());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }

    @Test
    public void testWebClientFaliureError() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "     \"error\": \"Invalid requestId\" \n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");

            totpVerificationService.initiateTOTP(loginProperties.get("DomainURL"), "codeChallenge", new InitiateTOTPMFARequestEntity(), deviceInfoEntity, new Result<InitiateTOTPMFAResponseEntity>() {
                @Override
                public void success(InitiateTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getStatusId());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testWebClientFaliureException() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "     \"error\": \"Invalid requestId \n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");

            totpVerificationService.initiateTOTP(loginProperties.get("DomainURL"), "codeChallenge", new InitiateTOTPMFARequestEntity(), deviceInfoEntity, new Result<InitiateTOTPMFAResponseEntity>() {
                @Override
                public void success(InitiateTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getStatusId());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }

    ///SETUP

    @Test
    public void testSetup() throws Exception {

        //  Whitebox.set
        try {
            Timber.e("Success");


            final MockResponse response = new MockResponse().setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "    \"st\": \"556b95f8-9326-4250-a4ee-0e0d887f7a7d\"" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/verification-srv/backupcode/callSetup");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;


            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.setupTOTP(loginProperties.get("DomainURL"), "AccessToken", new SetupTOTPMFARequestEntity(), deviceInfoEntity, new Result<SetupTOTPMFAResponseEntity>() {
                @Override
                public void success(SetupTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSt());

                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Cidaas developer", error.getErrorMessage());
                    latch.countDown();
                }
            });
            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testSetupDevicenull() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "    \"st\": \"556b95f8-9326-4250-a4ee-0e0d887f7a7d\"" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/authz-srv/authrequest/authz/generate");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;


            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.setupTOTP(loginProperties.get("DomainURL"), "AccessToken", new SetupTOTPMFARequestEntity(), null, new Result<SetupTOTPMFAResponseEntity>() {
                @Override
                public void success(SetupTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSt());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    // Assert.assertEquals("Cidaas developer",error.getErrorMessage());
                    latch.countDown();
                }
            });
            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testSetupFor202() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(202)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "        \"requestId_name\": \"Raja Developers\",\n" +
                            "        \"allowLoginWith\": [\n" +
                            "            \"EMAIL\",\n" +
                            "            \"MOBILE\",\n" +
                            "            \"USER_NAME\"\n" +
                            "        ]\n" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.setupTOTP(loginProperties.get("DomainURL"), "AccessToken", new SetupTOTPMFARequestEntity(), deviceInfoEntity, new Result<SetupTOTPMFAResponseEntity>() {
                @Override
                public void success(SetupTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSt());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Service failure but successful response", error.getErrorMessage());
                    latch.countDown();
                }
            });
            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testSetupFor401() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "   \"error\": {\n" +
                            "                \"code\": 24008,\n" +
                            "                        \"moreInfo\": \"\",\n" +
                            "                        \"type\": \"LoginException\",\n" +
                            "                        \"status\": 400,\n" +
                            "                        \"referenceNumber\": \"1537337364806\",\n" +
                            "                        \"error\": \"Invalid requestId\"\n" +
                            "            }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.setupTOTP(loginProperties.get("DomainURL"), "AccessToken", new SetupTOTPMFARequestEntity(), deviceInfoEntity, new Result<SetupTOTPMFAResponseEntity>() {
                @Override
                public void success(SetupTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSt());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }

    @Test
    public void testSetupFaliureError() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "     \"error\": \"Invalid requestId\" \n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.setupTOTP(loginProperties.get("DomainURL"), "AccessToken", new SetupTOTPMFARequestEntity(), deviceInfoEntity, new Result<SetupTOTPMFAResponseEntity>() {
                @Override
                public void success(SetupTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSt());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testSetupFaliureException() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "     \"error\": \"Invalid requestId \n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.setupTOTP(loginProperties.get("DomainURL"), "AccessToken", new SetupTOTPMFARequestEntity(), deviceInfoEntity, new Result<SetupTOTPMFAResponseEntity>() {
                @Override
                public void success(SetupTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSt());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    //SCANNED
    @Test
    public void testScanned() throws Exception {

        //  Whitebox.set
        try {
            Timber.e("Success");


            final MockResponse response = new MockResponse().setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "    \"userDeviceId\": \"556b95f8-9326-4250-a4ee-0e0d887f7a7d\"" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/verification-srv/backupcode/callSetup");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;


            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            ScannedRequestEntity scannedRequestEntity = new ScannedRequestEntity();
            scannedRequestEntity.setClient_id("client");
            scannedRequestEntity.setStatusId("sid");
            scannedRequestEntity.setUsage_pass("upass");
            scannedRequestEntity.setDeviceInfo(new DeviceInfoEntity());

            totpVerificationService.scannedTOTP(loginProperties.get("DomainURL"), scannedRequestEntity, deviceInfoEntity, new Result<ScannedResponseEntity>() {
                @Override
                public void success(ScannedResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getUserDeviceId());

                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Cidaas developer", error.getErrorMessage());
                    latch.countDown();
                }
            });

            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testScannedDevicenull() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "    \"statusId\": \"556b95f8-9326-4250-a4ee-0e0d887f7a7d\"" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/authz-srv/authrequest/authz/generate");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;


            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            ScannedRequestEntity scannedRequestEntity = new ScannedRequestEntity();
            scannedRequestEntity.setClient_id("client");
            scannedRequestEntity.setStatusId("sid");
            scannedRequestEntity.setUsage_pass("upass");
            scannedRequestEntity.setDeviceInfo(new DeviceInfoEntity());


            totpVerificationService.scannedTOTP(loginProperties.get("DomainURL"), scannedRequestEntity, null, new Result<ScannedResponseEntity>() {
                @Override
                public void success(ScannedResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getUserDeviceId());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    // Assert.assertEquals("Cidaas developer",error.getErrorMessage());
                    latch.countDown();
                }
            });
            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testScannedFor202() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(202)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "        \"requestId_name\": \"Raja Developers\",\n" +
                            "        \"allowLoginWith\": [\n" +
                            "            \"EMAIL\",\n" +
                            "            \"MOBILE\",\n" +
                            "            \"USER_NAME\"\n" +
                            "        ]\n" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            ScannedRequestEntity scannedRequestEntity = new ScannedRequestEntity();
            scannedRequestEntity.setClient_id("client");
            scannedRequestEntity.setStatusId("sid");
            scannedRequestEntity.setUsage_pass("upass");
            scannedRequestEntity.setDeviceInfo(new DeviceInfoEntity());


            totpVerificationService.scannedTOTP(loginProperties.get("DomainURL"), scannedRequestEntity, deviceInfoEntity, new Result<ScannedResponseEntity>() {
                @Override
                public void success(ScannedResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getUserDeviceId());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Service failure but successful response", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testScannedFor401() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "   \"error\": {\n" +
                            "                \"code\": 24008,\n" +
                            "                        \"moreInfo\": \"\",\n" +
                            "                        \"type\": \"LoginException\",\n" +
                            "                        \"status\": 400,\n" +
                            "                        \"referenceNumber\": \"1537337364806\",\n" +
                            "                        \"error\": \"Invalid requestId\"\n" +
                            "            }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            ScannedRequestEntity scannedRequestEntity = new ScannedRequestEntity();
            scannedRequestEntity.setClient_id("client");
            scannedRequestEntity.setStatusId("sid");
            scannedRequestEntity.setUsage_pass("upass");
            scannedRequestEntity.setDeviceInfo(new DeviceInfoEntity());


            totpVerificationService.scannedTOTP(loginProperties.get("DomainURL"), scannedRequestEntity, deviceInfoEntity, new Result<ScannedResponseEntity>() {
                @Override
                public void success(ScannedResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getUserDeviceId());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }

    @Test
    public void testScannedFaliureError() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "     \"error\": \"Invalid requestId\" \n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            ScannedRequestEntity scannedRequestEntity = new ScannedRequestEntity();
            scannedRequestEntity.setClient_id("client");
            scannedRequestEntity.setStatusId("sid");
            scannedRequestEntity.setUsage_pass("upass");
            scannedRequestEntity.setDeviceInfo(new DeviceInfoEntity());

            totpVerificationService.scannedTOTP(loginProperties.get("DomainURL"), scannedRequestEntity, deviceInfoEntity, new Result<ScannedResponseEntity>() {
                @Override
                public void success(ScannedResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getUserDeviceId());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testScannedFaliureException() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "     \"error\": \"Invalid requestId \n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            ScannedRequestEntity scannedRequestEntity = new ScannedRequestEntity();
            scannedRequestEntity.setClient_id("client");
            scannedRequestEntity.setStatusId("sid");
            scannedRequestEntity.setUsage_pass("upass");
            scannedRequestEntity.setDeviceInfo(new DeviceInfoEntity());

            totpVerificationService.scannedTOTP(loginProperties.get("DomainURL"), scannedRequestEntity, deviceInfoEntity, new Result<ScannedResponseEntity>() {
                @Override
                public void success(ScannedResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getUserDeviceId());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    //ENROLL
    @Test
    public void testEnroll() throws Exception {

        //  Whitebox.set
        try {
            Timber.e("Success");


            final MockResponse response = new MockResponse().setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "    \"sub\": \"556b95f8-9326-4250-a4ee-0e0d887f7a7d\"" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/verification-srv/backupcode/callSetup");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;


            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.enrollTOTP(loginProperties.get("DomainURL"), "accs", new EnrollTOTPMFARequestEntity(), deviceInfoEntity, new Result<EnrollTOTPMFAResponseEntity>() {
                @Override
                public void success(EnrollTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSub());

                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Cidaas developer", error.getErrorMessage());
                    latch.countDown();
                }
            });
   /* @Test
    public void testSetupTOTP() throws Exception {

        iVRVerificationService.setupTOTP("baseurl", "accessToken", null,new Result<SetupTOTPMFAResponseEntity>() {
            @Override
            public void success(SetupTOTPMFAResponseEntity result) {

            }

            @Override
            public void failure(WebAuthError error) {

            }
        });
    }

    @Test
    public void testEnrollTOTP() throws Exception {

        iVRVerificationService.enrollTOTP("baseurl", "accessToken", new EnrollTOTPMFARequestEntity(), null,new Result<EnrollTOTPMFAResponseEntity>() {
            @Override
            public void success(EnrollTOTPMFAResponseEntity result) {

            }

            @Override
            public void failure(WebAuthError error) {

            }
        });
    }

    @Test
    public void testInitiateTOTP() throws Exception {

        iVRVerificationService.initiateTOTP("baseurl", new InitiateTOTPMFARequestEntity(), null,new Result<InitiateTOTPMFAResponseEntity>() {
            @Override
            public void success(InitiateTOTPMFAResponseEntity result) {

            }

            @Override
            public void failure(WebAuthError error) {

            }
        });
    }

    @Test
    public void testAuthenticateTOTP() throws Exception {

        iVRVerificationService.authenticateTOTP("baseurl", new AuthenticateTOTPRequestEntity(), null,new Result<AuthenticateTOTPResponseEntity>() {
            @Override
            public void success(AuthenticateTOTPResponseEntity result) {

            }

            @Override
            public void failure(WebAuthError error) {

            }
        });
    }*/
            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testEnrollDevicenull() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "    \"statusId\": \"556b95f8-9326-4250-a4ee-0e0d887f7a7d\"" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/authz-srv/authrequest/authz/generate");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;


            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.enrollTOTP(loginProperties.get("DomainURL"), "accs", new EnrollTOTPMFARequestEntity(), null, new Result<EnrollTOTPMFAResponseEntity>() {
                @Override
                public void success(EnrollTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSub());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    // Assert.assertEquals("Cidaas developer",error.getErrorMessage());
                    latch.countDown();
                }
            });
            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testEnrollFor202() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(202)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "        \"requestId_name\": \"Raja Developers\",\n" +
                            "        \"allowLoginWith\": [\n" +
                            "            \"EMAIL\",\n" +
                            "            \"MOBILE\",\n" +
                            "            \"USER_NAME\"\n" +
                            "        ]\n" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.enrollTOTP(loginProperties.get("DomainURL"), "accs", new EnrollTOTPMFARequestEntity(), deviceInfoEntity, new Result<EnrollTOTPMFAResponseEntity>() {
                @Override
                public void success(EnrollTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSub());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Service failure but successful response", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testEnrollFor401() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "   \"error\": {\n" +
                            "                \"code\": 24008,\n" +
                            "                        \"moreInfo\": \"\",\n" +
                            "                        \"type\": \"LoginException\",\n" +
                            "                        \"status\": 400,\n" +
                            "                        \"referenceNumber\": \"1537337364806\",\n" +
                            "                        \"error\": \"Invalid requestId\"\n" +
                            "            }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.enrollTOTP(loginProperties.get("DomainURL"), "accs", new EnrollTOTPMFARequestEntity(), deviceInfoEntity, new Result<EnrollTOTPMFAResponseEntity>() {
                @Override
                public void success(EnrollTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSub());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }

    @Test
    public void testEnrollFaliureError() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "     \"error\": \"Invalid requestId\" \n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.enrollTOTP(loginProperties.get("DomainURL"), "accs", new EnrollTOTPMFARequestEntity(), deviceInfoEntity, new Result<EnrollTOTPMFAResponseEntity>() {
                @Override
                public void success(EnrollTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSub());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testEnrollFaliureException() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "     \"error\": \"Invalid requestId \n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.enrollTOTP(loginProperties.get("DomainURL"), "accs", new EnrollTOTPMFARequestEntity(), deviceInfoEntity, new Result<EnrollTOTPMFAResponseEntity>() {
                @Override
                public void success(EnrollTOTPMFAResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSub());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }

    //AUTHENTICATE
    @Test
    public void testAuthenticate() throws Exception {

        //  Whitebox.set
        try {
            Timber.e("Success");


            final MockResponse response = new MockResponse().setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "    \"sub\": \"556b95f8-9326-4250-a4ee-0e0d887f7a7d\"" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/verification-srv/backupcode/callSetup");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;


            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.authenticateTOTP(loginProperties.get("DomainURL"), new AuthenticateTOTPRequestEntity(), deviceInfoEntity, new Result<AuthenticateTOTPResponseEntity>() {
                @Override
                public void success(AuthenticateTOTPResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSub());

                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Cidaas developer", error.getErrorMessage());
                    latch.countDown();
                }
            });
            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testAuthenticateDevicenull() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(200)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "    \"sub\": \"556b95f8-9326-4250-a4ee-0e0d887f7a7d\"" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/authz-srv/authrequest/authz/generate");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;


            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.authenticateTOTP(loginProperties.get("DomainURL"), new AuthenticateTOTPRequestEntity(), null, new Result<AuthenticateTOTPResponseEntity>() {
                @Override
                public void success(AuthenticateTOTPResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSub());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    // Assert.assertEquals("Cidaas developer",error.getErrorMessage());
                    latch.countDown();
                }
            });
            latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testAuthenticateFor202() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(202)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": true,\n" +
                            "    \"status\": 200,\n" +
                            "    \"data\": {\n" +
                            "        \"requestId_name\": \"Raja Developers\",\n" +
                            "        \"allowLoginWith\": [\n" +
                            "            \"EMAIL\",\n" +
                            "            \"MOBILE\",\n" +
                            "            \"USER_NAME\"\n" +
                            "        ]\n" +
                            "    }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.authenticateTOTP(loginProperties.get("DomainURL"), new AuthenticateTOTPRequestEntity(), deviceInfoEntity, new Result<AuthenticateTOTPResponseEntity>() {
                @Override
                public void success(AuthenticateTOTPResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSub());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Service failure but successful response", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testAuthenticateFor401() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "   \"error\": {\n" +
                            "                \"code\": 24008,\n" +
                            "                        \"moreInfo\": \"\",\n" +
                            "                        \"type\": \"LoginException\",\n" +
                            "                        \"status\": 400,\n" +
                            "                        \"referenceNumber\": \"1537337364806\",\n" +
                            "                        \"error\": \"Invalid requestId\"\n" +
                            "            }\n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");


            totpVerificationService.authenticateTOTP(loginProperties.get("DomainURL"), new AuthenticateTOTPRequestEntity(), deviceInfoEntity, new Result<AuthenticateTOTPResponseEntity>() {
                @Override
                public void success(AuthenticateTOTPResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSub());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }

    @Test
    public void testAuthenticateFaliureError() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "     \"error\": \"Invalid requestId\" \n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");

            totpVerificationService.authenticateTOTP(loginProperties.get("DomainURL"), new AuthenticateTOTPRequestEntity(), deviceInfoEntity, new Result<AuthenticateTOTPResponseEntity>() {
                @Override
                public void success(AuthenticateTOTPResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSub());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


    @Test
    public void testAuthenticateFaliureException() throws Exception {

        try {
            Timber.e("Success");

            final MockResponse response = new MockResponse().setResponseCode(401)
                    .addHeader("Content-Type", "application/json; charset=utf-8")
                    .setBody("{\n" +
                            "    \"success\": false,\n" +
                            "    \"status\": 401,\n" +
                            "     \"error\": \"Invalid requestId \n" +
                            "}");

            MockWebServer server = new MockWebServer();
            String domainURL = server.url("").toString();
            server.url("/public-srv/requestIdinfo/basic");


            server.enqueue(response);
            Cidaas.baseurl = domainURL;

            Dictionary<String, String> loginProperties = new Hashtable<>();
            loginProperties.put("DomainURL", HelperClass.removeLastChar(Cidaas.baseurl));
            loginProperties.put("ClientID", "ClientID");
            loginProperties.put("RedirectURL", "RedirectURL");

            totpVerificationService.authenticateTOTP(loginProperties.get("DomainURL"), new AuthenticateTOTPRequestEntity(), deviceInfoEntity, new Result<AuthenticateTOTPResponseEntity>() {
                @Override
                public void success(AuthenticateTOTPResponseEntity result) {

                    Assert.assertEquals("556b95f8-9326-4250-a4ee-0e0d887f7a7d", result.getData().getSub());
                    latch.countDown();

                }

                @Override
                public void failure(WebAuthError error) {
                    Assert.assertEquals("Invalid requestId", error.getErrorMessage());
                    latch.countDown();
                }
            });
            //latch.await();
            //Thread.sleep(3000);
            Timber.e("Success");

        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), true, true);
            Assert.assertFalse(e.getMessage(), true);
        }

    }


}
