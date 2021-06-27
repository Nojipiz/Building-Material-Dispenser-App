package com.ferropaz.materialDispenser.ui.viewmodel

import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferropaz.materialDispenser.R
import com.ferropaz.materialDispenser.data.model.ConcreteManager

class ConcreteViewModel : ViewModel() {

    val quantities = MutableLiveData<ArrayList<Double>>()
    val packageQuantities = MutableLiveData<ArrayList<Double>>()
    val concreteManager = ConcreteManager()

    init {
        quantities.value = arrayListOf(0.0,0.0,0.0,0.0)
        packageQuantities.value = arrayListOf(0.0,0.0,0.0)
    }

    fun calculate(squareMeter: Editable, buildingType: Int, strengthType: Int){
        val list = concreteManager.calculate(squareMeter, buildingType, strengthType)
        quantities.value = list[0]
        packageQuantities.value = list[1]
    }
}