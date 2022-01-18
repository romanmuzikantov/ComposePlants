package com.example.soplant.commons

import android.util.Log
import com.amplifyframework.AmplifyException
import com.amplifyframework.kotlin.core.Amplify
import com.example.soplant.redux.confirmation.ConfirmationAction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object UserAttributes {
    suspend fun fetchUserAttributes(): Flow<Boolean> = flow {
        try {
            val result = Amplify.Auth.fetchUserAttributes()
            Log.i("AuthSession", "Successfully fetched user attributes")
            result.forEach { attr ->
                SharedPreferencesManager.shared().storeLoggedIn()
                when (attr.key.keyString) {
                    Constants.AuthSessionKeys.USER_ID -> {
                        SharedPreferencesManager.shared().storeUserId(attr.value)
                    }
                    Constants.AuthSessionKeys.USER_EMAIL -> {
                        SharedPreferencesManager.shared().storeUserEmail(attr.value)
                    }
                    Constants.AuthSessionKeys.USER_LOCATION -> {
                        SharedPreferencesManager.shared().storeUserLocation(attr.value)
                    }
                    Constants.AuthSessionKeys.USER_NAME -> {
                        SharedPreferencesManager.shared().storeUserName(attr.value)
                    }
                    Constants.AuthSessionKeys.USER_USERNAME -> {
                        SharedPreferencesManager.shared().storeUserUsername(attr.value)
                    }
                    Constants.AuthSessionKeys.USER_VERIFIED -> {
                        SharedPreferencesManager.shared().storeUserVerified(attr.value.equals("true"))
                    }
                    Constants.AuthSessionKeys.USER_PICTURE -> {
                        SharedPreferencesManager.shared().storePictureUrl(attr.value)
                    }
                    Constants.AuthSessionKeys.SOCIAL_IDENTITIES -> {
                        if (attr.value.contains("Google")) {
                            SharedPreferencesManager.shared().storeSocialMethod(Constants.SocialSignInMethod.GOOGLE)
                        } else if(attr.value.contains("Facebook")) {
                            SharedPreferencesManager.shared().storeSocialMethod(Constants.SocialSignInMethod.FACEBOOK)
                            val userId = "\\d{17}".toRegex().find(attr.value)
                            if (userId != null) {
                                SharedPreferencesManager.shared().storePictureUrl("https://graph.facebook.com/v12.0/${userId.value}/picture?type=large")
                            }
                        }
                    }
                }
            }
            emit(true)
        } catch (error: AmplifyException) {
            Log.i("AuthSession", "Failed to fetch auth session")
            emit(false)
        }
    }
}