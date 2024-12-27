package com.obsndy.prospectfarmersapp.ui.screens.login

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel: ViewModel() {

    private val firebaseUser = FirebaseAuth.getInstance().currentUser

    private val _isSignedIn = MutableStateFlow(false)
    val isSignedIn: StateFlow<Boolean> = _isSignedIn

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isMessage = MutableStateFlow(false)
    val isMessage: StateFlow<Boolean> = _isMessage

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    init {
        _isSignedIn.value = firebaseUser != null
    }

    fun setLoadingStatus(newStatus: Boolean){
        _isLoading.value = newStatus
    }

    fun setMessageStatus(newStatus: Boolean){
        _isMessage.value = newStatus
    }

    fun setMessage(newMessage: String){
        _message.value = newMessage
    }

    fun onMessageReported(){
        _isMessage.value = false
    }

}