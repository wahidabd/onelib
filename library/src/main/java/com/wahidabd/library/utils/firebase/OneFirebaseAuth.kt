package com.wahidabd.library.utils.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.wahidabd.library.data.Resource


/**
 * Created by wahid on 5/19/2024.
 * Github github.com/wahidabd.
 */


abstract class OneFirebaseAuth {
    protected abstract val auth: FirebaseAuth

    /**
     * This function allows a user to sign in with their email and password.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @param eventListener A callback function that handles the authentication result. It provides a `Resource` object which can be in one of three states: loading, success, or failure.
     *
     * The `eventListener` will be invoked with:
     * - `Resource.loading()` initially, indicating the authentication process has started.
     * - `Resource.success(FirebaseUser?)` if the authentication is successful, providing the authenticated `FirebaseUser`.
     * - `Resource.fail(String)` if the authentication fails, providing an error message.
     *
     * Example usage:
     * ```
     * ```
     */
    fun signIn(
        email: String,
        password: String,
        eventListener: ((data: Resource<FirebaseUser?>) -> Unit)
    ) {
        eventListener.invoke(Resource.loading())
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                eventListener.invoke(
                    Resource.success(it.user)
                )
            }
            .addOnFailureListener {
                eventListener.invoke(Resource.fail(it.localizedMessage))
            }
    }


    /**
     * This function allows a user to sign up with their email and password.
     *
     * @param email The email address of the user.
     * @param password The password of the user.
     * @param sendVerification A boolean indicating whether to send a verification email after successful sign-up. Default is `false`.
     * @param eventListener A callback function that handles the sign-up result. It provides a `Resource` object which can be in one of three states: loading, success, or failure.
     *
     * The `eventListener` will be invoked with:
     * - `Resource.loading()` initially, indicating the sign-up process has started.
     * - `Resource.success(FirebaseUser?)` if the sign-up is successful, providing the created `FirebaseUser`.
     * - `Resource.fail(String)` if the sign-up fails, providing an error message.
     *
     * Example usage:
     * ```
     * ```
     */
    fun signUp(
        email: String,
        password: String,
        sendVerification: Boolean = false,
        eventListener: ((data: Resource<FirebaseUser?>) -> Unit)
    ) {
        eventListener.invoke(Resource.loading())
        auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                if (sendVerification) it.user?.sendEmailVerification()
                eventListener.invoke(Resource.success(it.user))
            }
            .addOnFailureListener {
                eventListener.invoke(Resource.fail(it.localizedMessage))
            }
    }

    /**
     * This function signs the user out of the application.
     *
     * @param eventListener A callback function that handles the sign-out result. It provides a `Resource` object which can be in one of two states: success or failure.
     *
     * The `eventListener` will be invoked with:
     * - `Resource.success(true)` when the sign-out is successful.
     * - `Resource.fail(String)` if the sign-out process encounters an error. (Note: In this implementation, it always succeeds.)
     *
     * Example usage:
     * ```
     * ```
     */
    fun signOut(eventListener: ((data: Resource<Boolean>) -> Unit)) {
        auth.signOut()
        eventListener.invoke(Resource.success(true))
    }

    /**
     * This function initiates a password reset for the user with the specified email address.
     *
     * @param email The email address of the user who wants to reset their password.
     * @param eventListener A callback function that handles the result of the password reset request. It provides a `Resource` object which can be in one of three states: loading, success, or failure.
     *
     * The `eventListener` will be invoked with:
     * - `Resource.loading()` initially, indicating the password reset process has started.
     * - `Resource.success(true)` if the password reset email is sent successfully.
     * - `Resource.fail(String)` if the password reset request fails, providing an error message.
     *
     * Example usage:
     * ```
     * ```
     */
    fun resetPassword(email: String, eventListener: ((data: Resource<Boolean>) -> Unit)) {
        eventListener.invoke(Resource.loading())
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener {
                eventListener.invoke(
                    Resource.success(true)
                )
            }
            .addOnFailureListener {
                eventListener.invoke(Resource.fail(it.localizedMessage))
            }
    }
}