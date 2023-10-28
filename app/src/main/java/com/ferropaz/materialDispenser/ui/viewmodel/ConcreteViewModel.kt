package com.ferropaz.materialDispenser.ui.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferropaz.materialDispenser.R
import com.ferropaz.materialDispenser.data.model.ConcreteManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ConcreteViewModel : ViewModel() {

    val quantities = MutableLiveData<List<Double>>()
    val packageQuantities = MutableLiveData<List<Double>>()

    private val concreteManager = lazy { ConcreteManager() }

    init {
        quantities.value = arrayListOf(0.0,0.0,0.0,0.0)
        packageQuantities.value = arrayListOf(0.0,0.0,0.0)
    }

    fun calculate(squareMeter: Editable, buildingType: Int, strengthType: Int){
        CoroutineScope(Dispatchers.Default).launch {
            val list = concreteManager.value.calculate(squareMeter, buildingType, strengthType)
            quantities.postValue(list[0])
            packageQuantities.postValue(list[1])
        }
    }
}