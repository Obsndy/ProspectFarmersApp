package com.obsndy.prospectfarmersapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.obsndy.prospectfarmersapp.data.dao.FarmerDao
import com.obsndy.prospectfarmersapp.models.Farmer
import com.obsndy.prospectfarmersapp.state.FarmerState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val farmerDao: FarmerDao): ViewModel() {
    private val _farmers = farmerDao.getAll()
    val farmers: Flow<List<Farmer>> = _farmers.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    private val _farmerState = MutableStateFlow(FarmerState())
    val farmerState: StateFlow<FarmerState> = _farmerState

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _isMessage = MutableStateFlow(false)
    val isMessage: StateFlow<Boolean> = _isMessage



    fun deleteFarmer(farmer: Farmer) {
        viewModelScope.launch {
            farmerDao.delete(farmer)
        }
    }

    fun updateFarmer() {
        viewModelScope.launch {
            if (_farmerState.value.name.isNotEmpty() && _farmerState.value.phoneNumber.isNotEmpty() && _farmerState.value.location.isNotEmpty()) {
                farmerDao.update(_farmerState.value.toFarmer())
            } else {
                _isMessage.value = true
                _message.value = "Some fields are empty"
            }
        }
    }

    fun insertFarmer() {
        viewModelScope.launch {
            if (_farmerState.value.name.isNotEmpty() && _farmerState.value.phoneNumber.isNotEmpty() && _farmerState.value.location.isNotEmpty()) {
                farmerDao.insert(_farmerState.value.toFarmer())
            } else {
                _isMessage.value = true
                _message.value = "Some fields are empty"
            }
        }
    }
}