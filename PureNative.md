# Native UI Integration

The steps here will guide you through setting up and managing authentication and authorization in your apps using cidaas SDK.

## Table of Contents

<!--ts-->
* [Getting RequestId](#getting-request-id)
* [Getting Tenant Information](#getting-tenant-info)
* [Getting Client Information](#get-client-info)
* [Login](#login)
    <!--ts-->
    * [Login with credentials](#login-with-credentials)
    <!--te-->
* [Registration](#registration)
    <!--ts-->
    * [Getting Registration Fields](#getting-registration-fields)
    * [Register user](#register-user)
    <!--te-->
* [De-duplication](#de-duplication)
    <!--ts-->
    * [Get Deduplication Details](#get-deduplication-details)
    * [Register user](#register-user-1)
    * [Login With Deduplication](#login-with-deduplication)
    <!--te-->
* [Account Verification](#account-verification)
    <!--ts-->
    * [Initiate Email verification](#initiate-email-verification)
    * [Initiate SMS verification](#initiate-sms-verification)
    * [Initiate IVR verification](#initiate-ivr-verification)
    * [Verify Account](#verify-account)
    <!--te-->
* [Forgot Password](#forgot-password)
    <!--ts-->
    * [Initiate Reset Password](#initiate-reset-password)
    * [Handle Reset Password](#handle-reset-password)
    * [Reset Password](#reset-password)
    <!--te-->
* [Consent](#consent-management)
     <!--ts-->
    * [Get Consent details](#getting-consent-details)
    * [Login after Consent](#login-after-consent)
    <!--te-->
* [Passwordless or Multifactor Authentication](/app/Passwordless.md)
<!--te-->



#### Initialisation

The first step of integrating cidaas sdk With Native UI Feature is the initialisation process.
```java

CidaasNative cidaasNative = CidaasNative.getInstance(your Activity Context);

or

CidaasNative cidaasNative =new CidaasNative(your Activity Context);

```


#### Getting Request Id

First, You have to  get RequestId and use this in your subsequent calls. Server provides a unique Id based on urls configured for your application. Henceforth, in all requests like login, registration, you have to pass requestId, which is utilized to identify your client between two consecutive independant calls. To get the requestId, call

****getRequestId()****.
```java

cidaasNative.getRequestId(new Result < AuthRequestResponseEntity > () {
  @Override
  public void success(AuthRequestResponseEntity result) {

    // Your success code here

  }

  @Override
  public void failure(WebAuthError error) {

    // Your failure code here

  }
});

```
**Response:**

```json
{
    "success":true,
    "status":200,
    "data": {
        "groupname":"default",
        "lang":"en-US,en;q=0.9,de-DE;q=0.8,de;q=0.7",
        "view_type":"login",
        "requestId":"45a921cf-ee26-46b0-9bf4-58636dced99f”
    }
}
```

#### Getting Tenant Info

Sometimes you may want to lookup different types of login available ('Email', 'Mobile', 'Username') for a particular tenant. To get the tenant information, call ****getTenantInfo()****.


```java

cidaasNative.getTenantInfo(new Result < TenantInfoEntity > () {

  @Override
  public void success(TenantInfoEntity result) {

    // Your success code here

  }

  @Override
  public void failure(WebAuthError error) {

    // Your failure code here

  }
});

```
**Response:**

```json
{
    "success": true,
    "status": 200,
    "data": {
        "tenant_name": "Cidaas Management",
        "allowLoginWith": [
            "EMAIL",
            "MOBILE",
            "USER_NAME"
        ]
    }
}
```

#### Get Client Info

Once you get tenant information, if you want to find client information you can call the following method. It contains client name, logo url specified for the client in the Admin's Apps section and details of what all social providers are configured for the App. To get the client information, call ****getClientInfo()****.

```java
cidaasNative.getClientInfo("your RequestId", new Result < ClientInfoEntity > () {
  @Override
  public void success(ClientInfoEntity result) {

    // Your success code here

  }

  @Override
  public void failure(WebAuthError error) {

    // Your failure code here

  }
});

```
**Response:**

```json
{
    "success": true,
    "status": 200,
    "data": {
        "passwordless_enabled": true,
        "logo_uri": "https://www.cidaas.com/wp-content/uploads/2018/02/logo-black.png",
        "login_providers": [
            "facebook",
            "linkedin"
        ],
        "policy_uri": "",
        "tos_uri": "",
        "client_name": "demo-app"
    }
}

```

#### Registration
#### Getting Registration Fields

Before registration, you may want to know what all are the fields that you must show to your user. For getting these fields, call  ****getRegistrationFields()****.

```java
cidaasNative.getRegistrationFields("Your_RequestId","Your_locale" ,new Result < RegistrationSetupResponseEntity > () {
  @Override
  public void success(RegistrationSetupResponseEntity result) {
    // your success code here

  }

  @Override
  public void failure(WebAuthError error) {
    // your failure code here

  }
```

**Response:**

```json
{
    "success": true,
    "status": 200,
    "data": [{
        "dataType": "EMAIL",
        "fieldGroup": "DEFAULT",
        "isGroupTitle": false,
        "fieldKey": "email",
        "fieldType": "SYSTEM",
        "order": 1,
        "readOnly": false,
        "required": true,
        "fieldDefinition": {},
        "localeText": {
            "locale": "en-us",
            "language": "en",
            "name": "Email",
            "verificationRequired": "Given Email is not verified.",
            "required": "Email is Required"
        }
    },
    {
        "dataType": "TEXT",
        "fieldGroup": "DEFAULT",
        "isGroupTitle": false,
        "fieldKey": "given_name",
        "fieldType": "SYSTEM",
        "order": 2,
        "readOnly": false,
        "required": true,
        "fieldDefinition": {
            "maxLength": 150
        },
        "localeText": {
            "maxLength": "Givenname cannot be more than 150 chars",
            "required": "Given Name is Required",
            "name": "Given Name",
            "language": "en",
            "locale": "en-us"
        }
    }]
}
```

#### Register user

 To register a new user, call ****registerUser()****.
```java

RegistrationEntity registrationEntity = new RegistrationEntity();

registrationEntity.setUsername("davidjhonson");
registrationEntity.setEmail("davidjhonson@gmail.com");
registrationEntity.setGiven_name("David");
registrationEntity.setFamily_name("Jhonson");
registrationEntity.setPassword("123456");
registrationEntity.setPassword_echo("123456");
registrationEntity.setMobile_number("+919876553230");
Date date = new Date();
date.setDate(27 / 12 / 1994);
registrationEntity.setBirthdate(date);
registrationEntity.setGender("Male");
registrationEntity.setWebsite("http://google.com");


RegistrationCustomFieldEntity registrationCustomFieldEntity = new RegistrationCustomFieldEntity();
registrationCustomFieldEntity.setKey("Pincode");
registrationCustomFieldEntity.setValue("123456");
registrationCustomFieldEntity.setDataType("String");
registrationCustomFieldEntity.setId("pincode");
registrationCustomFieldEntity.setInternal(true);

Dictionary < String, RegistrationCustomFieldEntity > customFileds = new Hashtable < > ();
customFileds.put(registrationCustomFieldEntity.getKey(), registrationCustomFieldEntity);
registrationEntity.setCustomFields(customFileds);

cidaasNative.registerUser("Your_requestId", registrationEntity, new Result < RegisterNewUserResponseEntity > () {

    @Override
    public void success(RegisterNewUserResponseEntity result) {
        //Your success code here
    }

    @Override
    public void failure(WebAuthError error) {
      //Your failure code here
    }
});

```

**Response:**

```json
{
    "success": true,
    "status": 200,
    "data": {
     	"track_id":"45a921cf-ee26-46b0-9bf4-58636dced99f",
        "sub": "7dfb2122-fa5e-4f7a-8494-dadac9b43f9d",
        "userStatus": "VERIFIED",
        "email_verified": false,
        "suggested_action": "DEDUPLICATION"
    }
}
```

After you get the successful response from the ****registerUser()****, You may get a suggested_action like ****"DEDUPLICATION"**** in the data of success respone. At that time, you have to follow the following steps

#### De-duplication
 User de-duplication is a process that eliminates redundant copies of user thus reducing storage overhead as well as other inefficiencies. This process can be triggered during registion itself by following next steps.

When a user is being registered system does a de-duplication check, to verify if that is already existing. System then shows the list of potential duplicate users whose data seems to match most of the information entered during this registration. System then gives an option to the user to use one of the found duplicate record or reject all of them and register this new values as a fresh user.

In order to implement above functionality few of the below methods have to be called.

#### Get Deduplication Details

To get the list of similar users, call ****getDeduplicationDetails()**** . If this method is used, system uses some heuristic algorithms and finds out any similar user exists in system and returns them.

> #### Note :- You can get the track id from thein the data of success respone of registerUser().

```java
 cidaasNative.getDeduplicationDetails("your_track_id", new Result < DeduplicationResponseEntity > () {
  @Override
  public void success(DeduplicationResponseEntity result) {
   //Your success code here.
  }

  @Override
  public void failure(WebAuthError error) {
   //Your failure code here
  }
 });
```


**Response:**

```json
{
    "success": true,
    "status": 200,
    "data": {
        "email": "xxx@gmail.com",
        "deduplicationList": [
        {
            "provider": "SELF",
            "sub": "39363935-4d04-4411-8606-6805c4e673b4",
            "email": "xxx********n2716@g***l.com",
            "emailName": "xxx********n2716",
            "firstname": "xxx",
            "lastname": "yyy",
            "displayName": "xxx yyy",
            "currentLocale": "IN",
            "country": "India",
            "region": "Delhi",
            "city": "Delhi",
            "zipcode": "110008"
        },
        {
            "provider": "SELF",
            "sub": "488b8128-5584-4c25-9776-6ed34c6e7017",
            "email": "xx****n21@g***l.com",
            "emailName": "xx****n21",
            "firstname": "xxx",
            "lastname": "yyy",
            "displayName": "xxx yyy",
            "currentLocale": "IN",
            "country": "India",
            "region": "Delhi",
            "city": "Delhi",
            "zipcode": "110008"
        }]
    }
}
```

#### Register User

While registering user, if system found similar users already registered,that list is shown to user. User can decide whether to use one of the existing logins, or choose to ignore all shown details. ****registerUser()**** method can be called to ignore shown result and register details in registration form as a new user.

```java
cidaasNative.registerUser("your track id", new Result < RegisterDeduplicationEntity > () {
 @Override
 public void success(RegisterDeduplicationEntity result) {
  //Your success code here
 }

 @Override
 public void failure(WebAuthError error) {
  //Your failure code here
 }
});
```

**Response:**

```java
{
    "success": true,
    "status": 200,
    "data": {
        "sub": "51701ec8-f2d7-4361-a727-f8df476a711a",
        "userStatus": "VERIFIED",
        "email_verified": false,
        "suggested_action": "LOGIN"
    }
}
```


#### Login With Deduplication

While registering user, if system found similar users already registered,that list is shown to user. User can decide whether to use one of the existing logins, or choose to ignore all shown details. ****loginWithDeduplication()**** method can be called to use one of those existing logins shown by the system. Note that, System will still use the secure authentication and verifications that were setup for earlier user, before login.

```java
cidaasNative.loginWithDeduplication("your_requestId","your_sub", "your_password", new Result < LoginCredentialsResponseEntity > () {
 @Override
 public void success(LoginCredentialsResponseEntity result) {
  //Your success code here
 }

 @Override
 public void failure(WebAuthError error) {
  //Your failure code here
 }
});
```

**Response:**

```json
{
    "success": true,
    "status": 200,
    "data": {
        "token_type": "Bearer",
        "expires_in": 86400,
        "access_token": "eyJhbGciOiJSUzI1NiIsImtpZCI6IjUxNWYxMGE5LTVmNDktNGZlYS04MGNlLTZmYTkzMzk2YjI4NyJ9*****",
        "session_state": "CNT7GGALeoKyTF6Og-cZHAuHUJBQ20M0jLL35oh3UGk.vcNxCNq4Y68",
        "viewtype": "login",
        "grant_type": "login"
    }
}
```
#### Account Verification

In order to avoid misuse of user registration functions, it is a good practise to include account verification along with it. Once registering is done, you can verify your account either by Email, SMS or IVR verification call. To do this, first you have to initiate the account verification. You can invoke any of the following as it suits your use case.

#### Initiate Email verification

This method is to be used when you want to receive a verification code via Email:

**initiateEmailVerification()**.

```java
cidaasNative.initiateEmailVerification("your_sub", "your_requestId", new Result < RegisterUserAccountInitiateResponseEntity > () {
@Override
public void success(RegisterUserAccountInitiateResponseEntity result) {
//your Success Code
}

@Override
public void failure(WebAuthError error) {
 //your failure code
}
});

```
#### Initiate SMS verification

If you would like to receive a verification code via SMS, call  **initiateSMSVerification()**.

```java



cidaasNative.initiateSMSVerification("Your_sub", "Your_requestId", new Result<RegisterUserAccountInitiateResponseEntity>() {

@Override
public void success(RegisterUserAccountInitiateResponseEntity result) {

//Your success code here

}


@Override
public void failure(WebAuthError error) {

//Your failure code here

}

});


```
#### Initiate IVR verification


In order to receive a verification code via IVR verification call, call **initiateIVRVerification()**.

```java

cidaasNative.initiateIVRVerification("your_sub", "your_requestId", new Result < RegisterUserAccountInitiateResponseEntity > () {

@Override
public void success(RegisterUserAccountInitiateResponseEntity result) {
//Your Success code here

}

@Override
public void failure(WebAuthError error) {

//Your failure code here

}
});

```
**Response:**

```json
{
    "success": true,
    "status": 200,
    "data": {
        "accvid":"353446"
    }
}
```

#### Verify Account

Once you received your verification code via any of the mediums like Email, SMS or IVR, you need to verify the code. For that verification, call **verifyAccount()**.
```java

cidaasNative.verifyAccount("your code", new Result < RegisterUserAccountVerifyResponseEntity > () {

@Override
public void success(RegisterUserAccountVerifyResponseEntity result) {

//your success code here

}

@Override
public void failure(WebAuthError error) {

//your failure code here

}
});

```
**Response:**

```json
{
    "success": true,
    "status": 200
}
```

#### Login
#### Login with credentials

To login with your username and password, call ****loginWithCredentials()****.
```java

LoginEntity loginEntity = new LogintEntity();

loginEntity.setUsername("davidjhonson@gmail.com");
loginEntity.setPassword("123456");
loginEntity.setUsername_type("email");

cidaasNative.loginWithCredentials("Your RequestId", loginEntity, new Result < LoginCredentialsResponseEntity > () {

@Override
public void success(LoginCredentialsResponseEntity result) {
  // Your success code here
}

@Override
public void failure(WebAuthError error) {
  // Your failure code here
}

```
**Response:**
```json
{
    "success": true,
    "status": 200,
    "data": {
        "token_type": "Bearer",
        "sub": "51701ec8-f2d7-4361-a727-f8df476a711a",
        "expires_in": 86400,
        "access_token": "eyJhbGciOiJSUzI1NiIsImtpZCI6IjUxNWYxMGE5LTV",
        "session_state": "CNT7TF6Og-cCNq4Y68",
        "viewtype": "login",
        "grant_type": "login"
    }
}
```

#### MFA List

To get the List of physical verifications configured by the user, call ****getMFAList()****.


```java
 cidaasNative.getMFAList("your_sub", new Result<MFAListResponseEntity>() {
         @Override
         public void success(MFAListResponseEntity result) {
             //Your Success Code here
         }

         @Override
         public void failure(WebAuthError error) {
 	    //Your Failure Code here
         }
     });

```
**Response:**
```json
{
    "success": true,
    "status": 200,
    "data":[  {
      "_id": "0fd78d48-f825-487f-823b-c71f05ced944",
      "verification_type": "PATTERN"
    },
     {
      "_id": "184ec81d-5bb3-466f-b10a-351f36b31fc4",
      "verification_type": "PUSH"
    }]
}
```

#### Set Remote Message

For device verification , we send one push notification to you For that you need to register your FCM Token(Firebase cloud Messaging) in your app in admin UI and
 For that you need to call, ****setFCMToken()****.
 ```java
  cidaasVerification.setFCMToken(refreshedToken);
  ```

  Then in the FirebaseMessagingService extented class, in the onMessageReceived() method,you need to set the remotemessage recieved here using ****setRemoteMessage()****.

   ```java
  cidaasVerification.setFCMToken(refreshedToken);
  ```



#### Forgot Password

There is an option to reset password if you forget password.

#### Initiate Reset Password

For resetting password, you will get a verification code either via Email or SMS. For email you need to call

****initiateResetPasswordByEmail()****.
```java
cidaasNative.initiateResetPasswordByEmail("Your_requestId", "your_email_id", new Result < ResetPasswordResponseEntity > () {
@Override
public void success(ResetPasswordResponseEntity result) {
   //Your success code here
}

@Override
public void failure(WebAuthError error) {
    //Your failure code here.
}
});

```
**Response:**

```json
{
    "success": true,
    "status": 200,
    "data": {
        "rprq": "f595edfb-754e-444c-ba01-6b69b89fb42a",
        "reset_initiated": true
    }
}

```

For resetting password, you will get a verification code either via Email or SMS. For email you need to call

****initiateResetPasswordBySMS()****.
```java
cidaasNative.initiateResetPasswordBySMS("Your_requestId", "your_mobile_number", new Result < ResetPasswordResponseEntity > () {
@Override
public void success(ResetPasswordResponseEntity result) {
   //Your success code here
}

@Override
public void failure(WebAuthError error) {
    //Your failure code here.
}
});

```
**Response:**

```json
{
    "success": true,
    "status": 200,
    "data": {
        "rprq": "f595edfb-754e-444c-ba01-6b69b89fb42a",
        "reset_initiated": true
    }
}

```
#### Handle Reset Password

Once verification code received, verify that code by calling ****handleResetPassword()****.
```java

cidaasNative.handleResetPassword("your verificaton code","your_rprq", new Result < ResetPasswordValidateCodeResponseEntity > () {

@Override
public void success(ResetPasswordValidateCodeResponseEntity result) {
    //Your suucees code here.
}

@Override
public void failure(WebAuthError error) {
    //your failure code here.
}
});

```
**Response:**

```json
{
    "success": true,
    "status": 200,
    "data": {
        "exchangeId": "1c4176bd-12b0-4672-b20c-9616e93457ed",
        "resetRequestId": "f595edfb-754e-444c-ba01-6b69b89fb42a"
    }
}
```

#### Reset Password

Once code is verified, reset your password with your new password. To reset your password, call ****resetPassword()****.

```java

ResetPasswordEntity resetPasswordEntity = new ResetPasswordEntity();
resetPasswordEntity.setPassword("yournewPassword");           								
resetPasswordEntity.setConfirmPassword("yourconfirmPassword");
resetPasswordEntity.setExchangeId("yourexchangeId");
resetPasswordEntity.setResetRequestId("yourresetRequestId");

cidaasNative.resetPassword(ResetPasswordEntity resetPasswordEntity, new Result < ResetNewPasswordResponseEntity > () {
@Override
public void success(ResetNewPasswordResponseEntity result) {
    //Your success code here.
}

@Override
public void failure(WebAuthError error) {
    //Your failure code here.
}
});

``
**Response:**

```json
{
    "success": true,
    "status": 200,
    "data": {
        "reseted":true
    }
}
```
